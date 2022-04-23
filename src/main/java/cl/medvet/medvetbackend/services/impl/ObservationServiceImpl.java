package cl.medvet.medvetbackend.services.impl;

import cl.medvet.medvetbackend.models.ObservationModel;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.repository.impl.ObservationRepositoryImpl;
import cl.medvet.medvetbackend.services.IObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObservationServiceImpl implements IObservationService {

    // object to connect to the observationRepo

    @Autowired
    ObservationRepositoryImpl obsRepo = new ObservationRepositoryImpl();

    @Override
    public ResponseModel createObservation(ObservationModel obs) {
        ResponseModel response = new ResponseModel();
        int res = 0;
        try{
            res = obsRepo.createObservation(obs);

            response.setData(res);

            if (res == 1){
                response.setMessageResponse("Observacion agregada correctamente...");
                response.setError(null);
            } else {
                response.setError("Error al crear Observacion...");
                response.setMessageResponse("Error al intentar insertar datos...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessageResponse("Error al intentar insertar datos...");
            response.setError(e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseModel editObservation(ObservationModel obs) {
        ResponseModel response = new ResponseModel();
        int res = 0;
        try{
            res = obsRepo.editObservation(obs);
            response.setData(res);
            if (res == 1) {
                response.setMessageResponse("Observacion editada correctamente...");
                response.setError(null);
            } else if (res == 0) {
                response.setMessageResponse("Error al editar la observacion...");
                response.setError("Observacion No encontrada...");
            } else {
                response.setMessageResponse("Error al actualizar la observacion...");
                response.setError("Observacion no editada...");
            }
        }catch (Exception e) {
            e.printStackTrace();
            response.setMessageResponse("Error de servicio al actualizar Observacion");
            response.setError(e.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseModel deleteObservation(int idObs) {
        ResponseModel response = new ResponseModel();
        try{
            int resp = obsRepo.deleteObservation(idObs);
            response.setData(resp);
            if(resp == 1){
                response.setMessageResponse("Observacion eliminada correctamente...");
                response.setError(null);
            }else{
                response.setError("Error al eliminar observacion");
                response.setMessageResponse("Error al eliminar observacion...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setData(null);
            response.setMessageResponse("Error al eliminar observacion");
            response.setError(e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseModel getAllObservationsByPet(int idPet) {
        ResponseModel response = new ResponseModel();
        try{
            response.setData(obsRepo.getAllObservationsByPet(idPet));
            response.setMessageResponse("Observaciones cargadas correctamente");
            response.setError(null);
        } catch (Exception e) {
            e.printStackTrace();
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error al obtener las observaciones...");
        }
        return response;
    }

    @Override
    public ResponseModel getAllObservationsByEmployee(String rutEmployee) {
        ResponseModel response = new ResponseModel();
        try{
            response.setData(obsRepo.getAllObservationsByEmployee(rutEmployee));
            response.setMessageResponse("Observaciones cargadas correctamente");
            response.setError(null);
        } catch (Exception e) {
            e.printStackTrace();
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error al obtener las observaciones...");
        }
        return response;
    }
}
