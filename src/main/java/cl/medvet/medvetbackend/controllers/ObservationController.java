package cl.medvet.medvetbackend.controllers;

import cl.medvet.medvetbackend.models.ObservationModel;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.services.impl.ObservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class ObservationController {

    @Autowired
    ObservationServiceImpl observationService = new ObservationServiceImpl();

    // Endpoint for creating an observation
    @PostMapping(value = "/createObservation")
    public @ResponseBody ResponseModel createObservation(@RequestBody ObservationModel observation){
        ResponseModel response = new ResponseModel();
        try{
            response = observationService.createObservation(observation);
        }catch (Exception e){
            System.out.printf("Error al crear la observacion...");
            e.printStackTrace();
        }
        return response;
    }

    // Endpoint for edit an observation
    @PutMapping(value = "/updateObservation")
    public @ResponseBody ResponseModel editObservation(@RequestBody ObservationModel obs) {
        ResponseModel response = new ResponseModel();
        try{
            response = observationService.editObservation(obs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    // Endpoint to delete an observation
    @DeleteMapping(value = "/deleteObservationById{idObs}")
    public @ResponseBody ResponseModel deleteObservation(@RequestParam(value = "idObs") int idObs) {
        ResponseModel response = new ResponseModel();
        try{
            response = observationService.deleteObservation(idObs);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error al eliminar observacion");
        }
        return response;
    }

    // Endpoint to get Observations by idpet
    @GetMapping(value = "/getObservationsByPet{idPet}")
    public @ResponseBody ResponseModel getObservationsByIdPet(@RequestParam(value = "idPet") int idPet) {
        ResponseModel response = new ResponseModel();
        try{
            response = observationService.getAllObservationsByPet(idPet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    // Enpoint to get Observations by rut Employee
    @GetMapping(value = "/getObservationsByEmployee{rutEmployee}")
    public @ResponseBody ResponseModel getObservationsByEmployee(@RequestParam(value = "rutEmployee") String rutEmployee) {
        ResponseModel response = new ResponseModel();
        try{
            response = observationService.getAllObservationsByEmployee(rutEmployee);
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
