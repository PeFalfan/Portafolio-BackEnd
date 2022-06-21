package cl.medvet.medvetbackend.repository;

import cl.medvet.medvetbackend.models.BookTimeModel;

import java.util.List;

public interface IBookTimeRepository {

    public List<BookTimeModel> getAllBookTimes();

    public List<BookTimeModel> getAllBookTimesByClient(String rutClient);

}
