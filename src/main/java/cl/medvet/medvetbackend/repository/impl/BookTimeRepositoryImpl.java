package cl.medvet.medvetbackend.repository.impl;

import cl.medvet.medvetbackend.models.BookTimeModel;
import cl.medvet.medvetbackend.repository.IBookTimeRepository;
import cl.medvet.medvetbackend.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookTimeRepositoryImpl implements IBookTimeRepository {

    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getConnection();
    }

    @Override
    public List<BookTimeModel> getAllBookTimes() {

        List<BookTimeModel> times = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM reservacion")) {
            while (rs.next()) {
                times.add(mapBooking(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return times;
    }

    @Override
    public List<BookTimeModel> getAllBookTimesByPet(int idPet) {

        List<BookTimeModel> times = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM reservacion WHERE id_mascota = " + idPet)) {
            while (rs.next()) {
                times.add(mapBooking(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return times;
    }

    public BookTimeModel mapBooking(ResultSet rs) throws SQLException {
        BookTimeModel time = new BookTimeModel();

        time.setId(rs.getInt("id_reserva"));
        time.setDate(rs.getString("fecha_reserva"));
        time.setTime(rs.getString("hora_horario"));
        time.setReservationDate(rs.getString("fecha_al_reservar"));
        time.setRutEmployee(rs.getString("EMPLEADO_rut_empleado"));
        time.setIdState(rs.getInt("ESTADO_id_estado"));
        time.setIdPet(rs.getInt("id_mascota"));

        return time;
    }
}
