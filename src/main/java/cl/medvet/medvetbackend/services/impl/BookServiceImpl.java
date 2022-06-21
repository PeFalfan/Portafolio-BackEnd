package cl.medvet.medvetbackend.services.impl;

import cl.medvet.medvetbackend.models.BookTimeModel;
import cl.medvet.medvetbackend.models.ClientModel;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.repository.impl.BookTimeRepositoryImpl;
import cl.medvet.medvetbackend.repository.impl.ClientRepositoryImpl;
import cl.medvet.medvetbackend.services.IBookTimeService;
import cl.medvet.medvetbackend.util.EmailCommunication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements IBookTimeService {

    private final BookTimeRepositoryImpl bookTimeRepo = new BookTimeRepositoryImpl();

    private final ClientRepositoryImpl clientRepo = new ClientRepositoryImpl();

    @Override
    public ResponseModel getAllBookTimes() {

        ResponseModel response = new ResponseModel();

        try {
            response.setData(bookTimeRepo.getAllBookTimes());
            response.setMessageResponse("Consulta correcta al servicio");
            response.setError(null);
        }catch (Exception e){
            response.setData(null);
            response.setMessageResponse("Error al consultar Horarios de atencion");
            response.setError(e.getMessage());
        }
        return response;
    }

    public ResponseModel bookATime(BookTimeModel bookTime){
        ResponseModel response = new ResponseModel();
        int respFromBooking = 0;
        int emailStatus = 0;
        ClientModel client = new ClientModel();

        try{

            respFromBooking = bookTimeRepo.bookATime(bookTime);

            if (respFromBooking == 1){

                response.setMessageResponse("Se reservó la hora correctamente ");

                client = clientRepo.getClientByRut(bookTime.getRutClient());

                // this can be improved, we can use a template to complete the emails in this kind of uses.
                String bodyMessage = "Buenas estimad@: " + client.getClientName() + " \n " +
                        "En el presente correo, se listará el detalle de tu reserva realizada en MEDVET! \n" +
                        "Te esperamos el día " + bookTime.getDate() + " a las " + bookTime.getTime() + " \n" +
                        "Recuerda que debes traer tu carnet de identidad. \n" +
                        "Te esperamos! \n \n " +
                        "Equipo MEDVET";

                emailStatus = EmailCommunication.sendMail(client.getClientEmail(), "Reserva de Hora", bodyMessage);

                if (emailStatus == 1){
                    response.setMessageResponse(response.getMessageResponse() + "Y Correo enviado correctamente!");
                } else {
                    response.setMessageResponse(response.getMessageResponse() + "Y Error al enviar correo!");
                }

            }

            response.setData(respFromBooking);
            response.setError(null);

        } catch (Exception e){
            response.setData(null);
            response.setMessageResponse("Error al realizar reserva");
            response.setError(e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseModel getAllBookedTimesByClient(String rutClient) {

        ResponseModel response = new ResponseModel();

        try {
            response.setData(bookTimeRepo.getAllBookTimesByClient(rutClient));
            response.setMessageResponse("Consulta correcta al servicio");
            response.setError(null);
        }catch (Exception e){
            response.setData(null);
            response.setMessageResponse("Error al consultar Horarios de atencion");
            response.setError(e.getMessage());
        }

        return response;
    }
}
