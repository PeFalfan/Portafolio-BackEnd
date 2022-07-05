package cl.medvet.medvetbackend.services.impl;

import cl.medvet.medvetbackend.models.*;
import cl.medvet.medvetbackend.repository.impl.ClientRepositoryImpl;
import cl.medvet.medvetbackend.repository.impl.ExamRepositoryImpl;
import cl.medvet.medvetbackend.services.IExamService;
import cl.medvet.medvetbackend.util.EmailCommunication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ExamServiceImpl implements IExamService {

    @Autowired
    ExamRepositoryImpl examRepo = new ExamRepositoryImpl();

    @Autowired
    ClientRepositoryImpl clientRepo = new ClientRepositoryImpl();

    PetServiceImpl petService = new PetServiceImpl();


    @Override
    public ResponseModel getExamsByPet(int idPet) {

        ResponseModel response = new ResponseModel();

        try {

            response.setData(examRepo.getExamsByPet(idPet));
            response.setError(null);
            response.setMessageResponse("Examenes consultados correctamente");

        } catch (Exception e) {
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error al consultar examenes");
        }

        return response;
    }

    @Override
    public ResponseModel uploadExam(ExamModel exam) {
        ResponseModel response = new ResponseModel();

        try {
            response.setData(examRepo.uploadExam(exam));
            response.setError(null);
            response.setMessageResponse("Examen subido correctamente.");
        } catch (Exception e){
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error al subir examen");
        }
        return response;
    }

    public ResponseModel uploadPrescription(PrescriptionModel prescription) {
        ResponseModel response = new ResponseModel();

        try{
            response.setData(examRepo.uploadPrescription(prescription));
            response.setMessageResponse("Prescripción agregada correctamente.");
            response.setError(null);
        } catch (SQLException e) {

            response.setData(null);
            response.setMessageResponse("Prescripción no agregada correctamente.");
            response.setError(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    public  ResponseModel requestExamsResults(int idPet){
        ResponseModel response = new ResponseModel();

        try{

            ClientModel client = clientRepo.getClientByPet(idPet);

            List<ExamModel> documents = examRepo.getExamsByPet(idPet);

            int res = sendExams(documents, client);

            response.setData(res);
            if (response.getData() == null){
                response.setMessageResponse("No se encuentran examenes cargados para la mascota ID°" + idPet +".");
            } else {
                response.setMessageResponse("Documentos cargados correctamente.");
            }
            response.setError(null);
        }catch (Exception e){
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error al realizar llamado al servicio");
        }

        return response;
    }

    public int sendExams(List<ExamModel> exams, ClientModel client){
        int res = 0;

        List<String> paths = new ArrayList<>();

        for (ExamModel doc : exams) {

            String pName = "src/main/resources/temporal/"+doc.getNameExam()+".pdf";

            paths.add(pName);

            File file = new File(pName);

            try (FileOutputStream fos = new FileOutputStream(file)){

                byte[] decoder = Base64.getDecoder().decode(doc.getExamResult());

                fos.write((decoder));

            } catch (Exception e){
                e.printStackTrace();
            }

        }

        // ahora podemos iterar sobre los usuarios, y a quienes sean tipo 2, se les enviará el correo, con el archivo.

        String bodyMessage = "Estimado  " + client.getClientName() + "\n" +
                "\n Se adjuntan los examenes solicitados! " + "\n" +
                " Saludos!";

        res = EmailCommunication.sendMail(client.getClientEmail(), "Material solicitado", bodyMessage, paths);

        return res;
    }

    public  ResponseModel requestPresc(int idPet){
        ResponseModel response = new ResponseModel();

        try{

            ClientModel client = clientRepo.getClientByPet(idPet);

            List<PrescriptionsToDownload> documents = (List<PrescriptionsToDownload>) petService.generatePrescription(idPet).getData();

            int res = sendPrescriptions(documents, client);

            response.setData(res);
            if (response.getData() == null){
                response.setMessageResponse("No se encuentran prescripciones cargados para la mascota ID°" + idPet +".");
            } else {
                response.setMessageResponse("Documentos cargados correctamente.");
            }
            response.setError(null);
        }catch (Exception e){
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error al realizar llamado al servicio");
        }

        return response;
    }

    public int sendPrescriptions(List<PrescriptionsToDownload> presc, ClientModel client){
        int res = 0;

        List<String> paths = new ArrayList<>();

        for (PrescriptionsToDownload doc : presc) {

            String pName = "src/main/resources/temporal/"+doc.getTitle();

            paths.add(pName);

            File file = new File(pName);

            try (FileOutputStream fos = new FileOutputStream(file)){

                byte[] decoder = Base64.getDecoder().decode(doc.getPrestcription());

                fos.write((decoder));

            } catch (Exception e){
                e.printStackTrace();
            }

        }

        // ahora podemos iterar sobre los usuarios, y a quienes sean tipo 2, se les enviará el correo, con el archivo.

        String bodyMessage = "Estimado  " + client.getClientName() + "\n" +
                "\n Se adjuntan las preescripciones solicitadas! " + "\n" +
                " Saludos!";

        res = EmailCommunication.sendMail(client.getClientEmail(), "Material solicitado", bodyMessage, paths);

        return res;
    }

    public ResponseModel loadPrescriptions(int idPet){

        ResponseModel resp = new ResponseModel();

        try{
            resp.setData(examRepo.getPrescriptions(idPet));
            resp.setError(null);
            resp.setMessageResponse("Prescripciones cargadas correctamente!");
        }catch (Exception e){
            resp.setData(null);
            resp.setError(e.getMessage());
            resp.setMessageResponse("Error al momento de cargar prescripciones!");
            e.printStackTrace();
        }

        return resp;
    }
}
