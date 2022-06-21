package cl.medvet.medvetbackend.repository.impl;

import cl.medvet.medvetbackend.models.BookTimeModel;
import cl.medvet.medvetbackend.repository.IBookTimeRepository;
import cl.medvet.medvetbackend.util.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
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
    public List<BookTimeModel> getAllBookTimesByClient(String rutClient) {

        List<BookTimeModel> times = new ArrayList<>();

        try (PreparedStatement stmt = getConnection()
                .prepareStatement("SELECT * FROM reservacion WHERE rut_cliente = ? ")) {

            stmt.setString(1, rutClient);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                times.add(mapBooking(rs));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return times;
    }

    public int bookATime(BookTimeModel bookTime){
        int res = 0;

        String query = "UPDATE reservacion SET fecha_al_reservar = ?, ESTADO_id_estado = ?, rut_cliente = ? WHERE id_reserva = ?";

        try (PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setString(1, bookTime.getReservationDate());
            stmt.setInt(2, bookTime.getIdState());
            stmt.setString(3, bookTime.getRutClient());
            stmt.setInt(4, bookTime.getId());

            res = stmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return res;
    }

    public BookTimeModel mapBooking(ResultSet rs) throws SQLException {
        BookTimeModel time = new BookTimeModel();

        time.setId(rs.getInt("id_reserva"));
        time.setDate(rs.getString("fecha_reserva"));
        time.setTime(rs.getString("hora_horario"));
        time.setReservationDate(rs.getString("fecha_al_reservar"));
        time.setRutEmployee(rs.getString("EMPLEADO_rut_empleado"));
        time.setIdState(rs.getInt("ESTADO_id_estado"));
        time.setRutClient(rs.getString("rut_cliente"));

        return time;
    }
}
