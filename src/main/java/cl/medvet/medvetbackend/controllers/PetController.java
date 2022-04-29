package cl.medvet.medvetbackend.controllers;

import cl.medvet.medvetbackend.models.PetModel;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.services.impl.PetServiceImpl;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class PetController {

    //Create a service for the pet
    PetServiceImpl petService = new PetServiceImpl();

    @PostMapping(value = "/createPet")
    public @ResponseBody ResponseModel createPet(@RequestBody PetModel pet){

        ResponseModel response = new ResponseModel();

        try {

            response = petService.createPet(pet);

        } catch (Exception e) {
            System.out.println("Error al crear mascota...");
            e.printStackTrace();
        }

        return response;

    }

    @PutMapping(value = "/updatePet")
    public @ResponseBody ResponseModel updatePet(@RequestBody PetModel pet) {

        ResponseModel response = new ResponseModel();

        try {

            response = petService.editPet(pet);

        } catch (Exception e) {
            System.out.println("Error al editar cliente...");
            e.printStackTrace();
        }

        return response;
    }

    @GetMapping(value = "/getPetsByRut{rutOwner}")
    public @ResponseBody ResponseModel getPetsByOwnerRut(@RequestParam(value = "rutOwner" ) String rutOwner) {

        ResponseModel response = new ResponseModel();

        try {
            response = petService.getPetsByOwner(rutOwner);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al consultar servicio...");
        }

        return response;
    }

    @DeleteMapping(value = "/deletePetById{idPet}")
    public @ResponseBody ResponseModel deletePet(@RequestParam(value = "idPet") int idPet) {

        ResponseModel response = new ResponseModel();

        try {

            response = petService.deletePet(idPet);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error al eliminar mascota...");
        }
        return response;
    }

}
