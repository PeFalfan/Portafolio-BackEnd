package cl.medvet.medvetbackend.services.impl;

import cl.medvet.medvetbackend.models.ExamModel;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.repository.impl.ExamRepositoryImpl;
import cl.medvet.medvetbackend.services.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements IExamService {

    @Autowired
    ExamRepositoryImpl examRepo = new ExamRepositoryImpl();


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
}
