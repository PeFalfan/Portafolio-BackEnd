package cl.medvet.medvetbackend.repository;

import cl.medvet.medvetbackend.models.ObservationModel;

import java.util.List;

public interface IObservationRepository {

    public int createObservation(ObservationModel obs);

    public int editObservation(ObservationModel obs);

    public int deleteObservation(int idObs);

    public List<ObservationModel> getAllObservationsByPet(int idPet);

    public List<ObservationModel> getAllObservationsByEmployee(String rutEmployee);

}
