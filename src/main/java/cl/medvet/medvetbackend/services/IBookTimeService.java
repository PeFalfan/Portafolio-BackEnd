package cl.medvet.medvetbackend.services;

import cl.medvet.medvetbackend.models.ResponseModel;

public interface IBookTimeService {

    public ResponseModel getAllBookTimes();

    public ResponseModel getAllBookedTimesByClient(String rutClient);

}
