package cl.medvet.medvetbackend.services.impl;

import cl.medvet.medvetbackend.models.PetModel;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.repository.impl.PetRepositoryImpl;
import cl.medvet.medvetbackend.services.IPetService;
import org.springframework.beans.factory.annotation.Autowired;

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

    public ResponseModel getRecipeById(int idRecipe){
        ResponseModel response = new ResponseModel();

        try{

            response.setData(petRepo.getRecipeById(idRecipe));

        }catch (Exception e){
            response.setError(e.getMessage());
            response.setMessageResponse("Error al consultar receta");
            response.setData(null);
        }

        return response;
    }
}
