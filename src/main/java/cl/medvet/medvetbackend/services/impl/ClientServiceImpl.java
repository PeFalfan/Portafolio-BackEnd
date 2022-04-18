package cl.medvet.medvetbackend.services.impl;

import cl.medvet.medvetbackend.models.ClientModel;
import cl.medvet.medvetbackend.models.PetModel;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.repository.impl.ClientRepositoryImpl;
import cl.medvet.medvetbackend.services.IClientService;
import cl.medvet.medvetbackend.util.EmailCommunication;
import cl.medvet.medvetbackend.util.PasswordEncryption;
import cl.medvet.medvetbackend.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements IClientService {
    PasswordEncryption pe = new PasswordEncryption();

    @Autowired
    ClientRepositoryImpl clientRepo = new ClientRepositoryImpl();

    @Override
    public ResponseModel getClients() {

        ResponseModel response = new ResponseModel();

        try {

            response.setData(clientRepo.getAllCLients());
            response.setMessageResponse("Clientes consultados correctamente...");
            response.setError(null);

        } catch (Exception e) {
            response.setData(null);
            response.setMessageResponse("Error de servicio al consultar clientes...");
            response.setError(e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public ResponseModel getClientByRut(String rut) {

        ResponseModel response = new ResponseModel();

        try {

            response.setData(clientRepo.getClientByRut(rut));

            if ( response.getData() != null ) {

                response.setMessageResponse("Cliente encontrado...");
                response.setError(null);

            } else {
                response.setMessageResponse("Cliente no encontrado...");
                response.setError(null);
            }
        } catch (Exception e) {

            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error al cargar cliente...");
            e.printStackTrace();

        }

        return response;
    }

    @Override
    public ResponseModel deleteClient(String rut) {

        ResponseModel response = new ResponseModel();

        try {

            int resp = (clientRepo.deleteClient(rut));
            response.setData(resp);

            if (resp == 1) {
                response.setMessageResponse("Cliente eliminado correctamente...");
                response.setError(null);
            } else {

                response.setError("Cliente no encontrado en la BD...");
                response.setMessageResponse("Cliente no encontrad...");

            }

        } catch (Exception e) {

            e.printStackTrace();
            response.setError(e.getMessage());
            response.setMessageResponse("Error al intentar eliminar al empleado...");
            response.setData(null);

        }

        return response;
    }

    @Override
    public ResponseModel editClient(ClientModel client) {
        ResponseModel response = new ResponseModel();
        int res = 0;

        try {

            res = clientRepo.editClient(client);

            response.setData(res);
            if (res == 1) {
                response.setMessageResponse("cliente editado correctamente");
                response.setError(null);
            }else {
                response.setError("Error al intentar editar al cliente...");
                response.setMessageResponse("Error al intentar editar al cliente...");
            }

        }catch (Exception e){
            e.printStackTrace();
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error en servicio al actualizar cliente...");
        }
        return response;
    }

    @Override
    public ResponseModel createClient(ClientModel client, PetModel pet) {

        ResponseModel response = new ResponseModel();

        int resp = 0;

        try {

            resp = clientRepo.createAddress(client.getClientAddress());

            if (resp == 1 ) {

                resp = clientRepo.getIdAddress(client.getClientAddress());

                client.getClientAddress().setIdAddress(resp);

                // password encoding:
                client.setClientPassword(pe.encode(client.getClientPassword()));

                resp = clientRepo.createClient(client, pet);

            }

            response.setData(resp);
            if (resp == 1) {
                response.setMessageResponse("cliente creado correctamente... ");
                response.setError(null);
            } else {
                response.setError("Error al crear cliente... ");
                response.setMessageResponse("cliente ya registrado... ");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setError(e.getMessage());
            response.setMessageResponse("Error al crear cliente... ");
        }

        return response;
    }

    @Override
    public ResponseModel recoverPassword(String email) {

        ResponseModel response = new ResponseModel();



        String newPass = "";

        int state = 0;

        int emailStatus = 0;

        // first we get the Client actual data:

        try {
            ClientModel client = clientRepo.getClientByEmail(email);

            if (client == null) {
                response.setData(null);
                response.setMessageResponse("Correo no se encuentra registrado ...");
                response.setError("Correo no registrado.");
            } else {

                // now we generate the new password
                newPass = PasswordGenerator.getAlphaNumericString();

                // we encripted and set it back to the client
                newPass = pe.encode(newPass);

                client.setClientPassword(newPass);

                // now we can update the client in the db:

                state = clientRepo.editClient(client);

                if (state == 1) {

                    String bodyMessage = "Buenas tardes " + client.getClientName() + "!\n" +
                            "Esperando te encuentres bien, te dejamos aqui tu nueva clave de ingreso a nuestra plataforma!\n" +
                            "Clave: " + pe.decode(client.getClientPassword()) +"\n" +
                            "Te esperamos!\n" +
                            "Equipo MEDVET.";

                    emailStatus = EmailCommunication.sendMail(email, "Recuperacion de contraseña",bodyMessage);

                }

            }

            response.setData(emailStatus);
            if (emailStatus == 1) {
                response.setMessageResponse("Correo enviado correctamente!");
                response.setError(null);
            } else {
                response.setError("Error al enviar correo");
                response.setMessageResponse("Error al enviar correo...");
            }
        } catch (Exception e) {
            response.setError(e.getMessage());
            response.setMessageResponse("Error al enviar correo...");
            e.printStackTrace();
        }


        return response;
    }

}
