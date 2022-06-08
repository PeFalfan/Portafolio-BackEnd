package cl.medvet.medvetbackend.services.impl;

import cl.medvet.medvetbackend.models.PetModel;
import cl.medvet.medvetbackend.models.PrescriptionModel;
import cl.medvet.medvetbackend.models.PrescriptionsToDownload;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.repository.impl.PetRepositoryImpl;
import cl.medvet.medvetbackend.services.IPetService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDType3Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PetServiceImpl implements IPetService {

    // Object to connect to the petRepo
    @Autowired
    PetRepositoryImpl petRepo = new PetRepositoryImpl();

    @Override
    public ResponseModel editPet(PetModel pet) {

        ResponseModel response = new ResponseModel();

        int res = 0;

        try {

            res = petRepo.editPet(pet);

            response.setData(res);
            if (res == 1) {
                response.setMessageResponse("Mascota editada correctamente...");
                response.setError(null);
            } else if (res == 0) {
                response.setError("Error al editar la mascota...");
                response.setMessageResponse("Mascota no encontrada...");
            } else {
                response.setError("Error al editar la mascota...");
                response.setMessageResponse("Mascota no editada...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessageResponse("Error de servicio al actualizar mascota...");
            response.setError(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public ResponseModel createPet(PetModel pet) {

        ResponseModel response = new ResponseModel();

        int resp = 0;

        try {

            resp = petRepo.createPet(pet);

            response.setData(resp);

            if (resp == 1) {

                response.setMessageResponse("Mascota creada correctamente... ");
                response.setError(null);

            } else {

                response.setError("Error al crear mascota... ");
                response.setMessageResponse("mascota ya registrado... ");

            }

        } catch (Exception e) {

            e.printStackTrace();
            response.setMessageResponse("Error al crear la mascota...");
            response.setError(e.getMessage());

        }

        return response;

    }

    @Override
    public ResponseModel deletePet(int idPet) {

        ResponseModel response = new ResponseModel();

        try {

            int resp = petRepo.deletePet(idPet);

            response.setData(resp);

            if (resp == 1) {

                response.setMessageResponse("Mascota eliminada correctamente...");
                response.setError(null);

            } else if (resp == 0) {

                response.setError("Mascota no encontrada en la BD...");
                response.setMessageResponse("Mascota no encontrada...");
            }

        } catch (Exception e) {

            e.printStackTrace();
            response.setData(null);
            response.setMessageResponse("Error al intentar eliminar mascota...");
            response.setError(e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseModel getPetsByOwner(String rut) {

        ResponseModel response = new ResponseModel();

        try {

            response.setData(petRepo.getPetsByOwner(rut));
            response.setMessageResponse("Mascotas obtenidas correctamente...");
            response.setError(null);

        } catch (Exception e) {
            e.printStackTrace();
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error de servicio al consultar mascotas...");
        }

        return response;
    }

    public ResponseModel getRecipeById(int idPet){
        ResponseModel response = new ResponseModel();

        try{

            response.setData(petRepo.getRecipeById(idPet));
            response.setMessageResponse("Servicio consultado correctamente");
            response.setError(null);

        }catch (Exception e){
            response.setError(e.getMessage());
            response.setMessageResponse("Error al consultar receta");
            response.setData(null);
        }

        return response;
    }

    public ResponseModel generatePrescription(int idPet){
        ResponseModel response = new ResponseModel();
        try{

            List<PrescriptionModel> prescriptions = new ArrayList<>();

            prescriptions = (petRepo.getRecipeById(idPet));

            PrescriptionsToDownload prescriptionsToDownload = new PrescriptionsToDownload();

            List<PrescriptionsToDownload> listToDownload = new ArrayList<>();

            for (PrescriptionModel prescription: prescriptions ) {

                // create the document
                PDDocument pdfDocument = new PDDocument();

                PDPage myPage = new PDPage();

                try {

                    PDPageContentStream content = new PDPageContentStream(pdfDocument, myPage);

                    content.beginText();

                    PDFont font = new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN);

                    content.setFont(font, 12);
                    content.setLeading(14.5f);

                    content.newLineAtOffset(25, 700);

                    String line1 = "Clinica Veterinaria MEDVET ** Estilos y formato de documento a definir **";
                    content.showText(line1);
                    content.newLine();

                    String line2 = "Nombre Paciente: " + prescription.getNamePet();
                    content.showText(line2);
                    content.newLine();

                    String line3 = "Nombre propietario: " + prescription.getNameOwner();
                    content.showText(line3);
                    content.newLine();

                    String line4 = "Rut Propietario: " + prescription.getRutOwner();
                    content.showText(line4);
                    content.newLine();

                    String line5 = "Descripción: " + prescription.getRecipeDesc();
                    content.showText(line5);
                    content.newLine();

                    String line5b = "Nombre Veterinario: " + prescription.getNameVet();
                    content.showText(line5b);
                    content.newLine();

                    String line6 = "Fecha emisión: " + prescription.getDate();
                    content.showText(line6);
                    content.newLine();

                    content.endText();

                    content.close();

                    String name = (prescription.getDate() + ".pdf").replace('/','-');

                    pdfDocument.addPage(myPage);
                    pdfDocument.save("src/main/resources/temporal/" + name);

                    // now encodeit and send it in the response

                    byte[] inFileBytes = Files.readAllBytes(Paths.get("src/main/resources/temporal/"+name));
                    String encoded = java.util.Base64.getEncoder().encodeToString(inFileBytes);

                    listToDownload.add(new PrescriptionsToDownload(prescription.getIdRecipe(),encoded, name ));

                } catch (Exception e){
                    e.printStackTrace();
                }

            }

            response.setData(listToDownload);
            if (listToDownload.size() > 0){
                response.setMessageResponse("Se cargan Prescripciones correctamente");
                response.setError(null);
            }else {
                response.setMessageResponse("No se encuentran prescripciones para descargar");
                response.setError(null);
            }

        }catch (Exception e){
            e.printStackTrace();
            response.setData(null);
            response.setMessageResponse("Se cargan Prescripciones correctamente");
            response.setError(e.getMessage());
        }

        return response;
    }
}
