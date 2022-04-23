package cl.medvet.medvetbackend.services;

import cl.medvet.medvetbackend.models.ObservationModel;
import cl.medvet.medvetbackend.models.ResponseModel;

public interface IObservationService {

    public ResponseModel createObservation(ObservationModel obs);

    public ResponseModel editObservation(ObservationModel obs);

    public ResponseModel deleteObservation(int idObs);

    public ResponseModel getAllObservationsByPet(int idPet);

    public ResponseModel getAllObservationsByEmployee(String rutEmployee);
}
