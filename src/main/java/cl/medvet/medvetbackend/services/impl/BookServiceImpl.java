package cl.medvet.medvetbackend.services.impl;

import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.repository.impl.BookTimeRepositoryImpl;
import cl.medvet.medvetbackend.services.IBookTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements IBookTimeService {

    private final BookTimeRepositoryImpl bookTimeRepo = new BookTimeRepositoryImpl();

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

    @Override
    public ResponseModel getAllBookedTimesByPet(int idPet) {

        ResponseModel response = new ResponseModel();

        try {
            response.setData(bookTimeRepo.getAllBookTimesByPet(idPet));
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
