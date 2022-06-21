package cl.medvet.medvetbackend.controllers;

import cl.medvet.medvetbackend.models.ClientModel;
import cl.medvet.medvetbackend.models.ClientPetModel;
import cl.medvet.medvetbackend.models.LogInModel;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.services.impl.ClientServiceImpl;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class ClientController {

    // create clientServiceImpl
    ClientServiceImpl clientService = new ClientServiceImpl();

    // Endpoint to get all Clients
    @GetMapping(value = "/getClients")
    public @ResponseBody ResponseModel getAllClients(){
        ResponseModel response = new ResponseModel();
        try {
            response = clientService.getClients();
        } catch (Exception e) {
            System.out.println("Error al consultar Servicio...");
            e.printStackTrace();
        }
        return response;
    }

    // Endpoint to get all communes
    @GetMapping(value = "/getCommunes")
    public @ResponseBody ResponseModel getCommunes(){
        ResponseModel respuesta = new ResponseModel();
        try{
            respuesta = clientService.getCommunes();
            respuesta.setError(null);
            respuesta.setMessageResponse("Comunas cargadas correctamente");
        }catch (Exception e){
            System.out.println(e.getMessage());
            respuesta.setData(null);
            respuesta.setMessageResponse("Error al cargar comunas");
            respuesta.setError(e.getMessage());
        }

        return respuesta;
    }

    // Endpoint to get a client by rut
    @GetMapping(value = "/getClientByRut{rutClient}")
    public @ResponseBody ResponseModel getClientByRut(@RequestParam(value = "rutClient" ) String rutClient ) {

        ResponseModel response = new ResponseModel();

        try {

            response = clientService.getClientByRut(rutClient);

        } catch (Exception e) {

            System.out.println("Error al consultar el servicio...");
            e.printStackTrace();

        }

        return response;
    }

    // Endpoint to get a client by rut
    @PostMapping(value = "/logInClient")
    public @ResponseBody ResponseModel logInClient(@RequestBody LogInModel usr ) {

        ResponseModel response = new ResponseModel();

        try {

            response = clientService.logIn(usr);

        } catch (Exception e) {

            System.out.println("Error al consultar el servicio...");
            e.printStackTrace();

        }

        return response;
    }

    // Endpoint to delete a client
    @DeleteMapping(value = "/deleteClient{rutClient}")
    public @ResponseBody ResponseModel deleteClient(@RequestParam(value = "rutClient") String clientRut) {

        ResponseModel response = new ResponseModel();

        try {

            response = clientService.deleteClient(clientRut);

        } catch (Exception e) {
            System.out.println("Error al eliminar cliente...");
            e.printStackTrace();

        }

        return response;
    }

    // Endpoint to update a client
    @PutMapping(value = "/updateClient")
    public @ResponseBody ResponseModel updateClient(@RequestBody ClientModel client) {

        ResponseModel response = new ResponseModel();

        try {

            response = clientService.editClient(client);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return response;

    }

    // Endpoint to create a client

    @PostMapping(value = "/createClient")
    public @ResponseBody ResponseModel createClient(@RequestBody ClientPetModel clientPet) {

        ResponseModel response = new ResponseModel();

        try {

            response = clientService.createClient(clientPet.getClient(), clientPet.getPet());

        } catch (Exception e) {

            System.out.println("Error al crear al cliente...");
            e.printStackTrace();

        }

        return response;

    }

    // Endpoint to recover password
    @GetMapping(value = "/recoverPassword{email}")
    public @ResponseBody ResponseModel recoverPassword(@RequestParam(value = "email") String email) {

        ResponseModel response = new ResponseModel();

        try {

            response = clientService.recoverPassword(email);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}