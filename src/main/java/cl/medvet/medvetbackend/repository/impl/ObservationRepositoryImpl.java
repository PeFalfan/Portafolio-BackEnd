package cl.medvet.medvetbackend.repository.impl;

import cl.medvet.medvetbackend.models.ObservationModel;
import cl.medvet.medvetbackend.repository.IObservationRepository;
import cl.medvet.medvetbackend.util.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ObservationRepositoryImpl implements IObservationRepository {

    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getConnection();
    }

    @Override
    public int createObservation(ObservationModel obs) {
        int res = 0;
        String query = "INSERT INTO observacion VALUES (0, ?,?,?)";

        try (PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setString(1,obs.getObservationDetail());
            stmt.setString(2, obs.getEmployeeRut());
            stmt.setInt(3, obs.getPetId());

            res = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int editObservation(ObservationModel obs) {
        int res = 0;
        String query = "UPDATE observacion SET observacion = ?, rut_veterinario = ? " +
                "WHERE id_obs = ?";
        try (PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setString(1,obs.getObservationDetail());
            stmt.setString(2,obs.getEmployeeRut());
            stmt.setInt(3,obs.getObservationId());

            res = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int deleteObservation(int idObs) {
        int res = 0;
        try (PreparedStatement stmt = getConnection()
                .prepareStatement("DELETE FROM observacion WHERE id_obs = ?")) {
            stmt.setInt(1, idObs);

            res = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public List<ObservationModel> getAllObservationsByPet(int idPet) {
        List<ObservationModel> observations = new ArrayList<>();
        try(PreparedStatement stmt = getConnection()
                .prepareStatement("SELECT * FROM observacion WHERE  MASCOTA_id_mascota = ?")) {
            stmt.setInt(1,idPet);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                observations.add(mapObservation(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observations;
    }

    @Override
    public List<ObservationModel> getAllObservationsByEmployee(String rutEmployee) {
        List<ObservationModel> observations = new ArrayList<>();
        try(PreparedStatement stmt = getConnection()
                .prepareStatement("SELECT * FROM observacion WHERE  rut_veterinario = ?")) {
            stmt.setString(1,rutEmployee);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                observations.add(mapObservation(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observations;
    }

    private ObservationModel mapObservation(ResultSet rs) throws SQLException {
        ObservationModel obs = new ObservationModel();

        obs.setObservationId(rs.getInt("id_obs"));
        obs.setObservationDetail(rs.getString("observacion"));
        obs.setEmployeeRut(rs.getString("rut_veterinario"));
        obs.setPetId(rs.getInt("MASCOTA_id_mascota"));

        return obs;
    }
}
