package cl.medvet.medvetbackend.repository.impl;

import cl.medvet.medvetbackend.models.ExamModel;
import cl.medvet.medvetbackend.models.PrescriptionModel;
import cl.medvet.medvetbackend.repository.IExamRepository;
import cl.medvet.medvetbackend.util.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExamRepositoryImpl implements IExamRepository {

    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getConnection();
    }

    // Method to load all exams from a pet
    @Override
    public List<ExamModel> getExamsByPet(int idPet) {

        List<ExamModel> exams = new ArrayList<>();

        try (PreparedStatement stmt = getConnection()
                .prepareStatement("SELECT * FROM examen WHERE MASCOTA_id_mascota = ?")) {
            stmt.setInt(1, idPet);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                exams.add(mapExam(rs));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return exams;
    }

    public ExamModel mapExam(ResultSet rs) throws SQLException{
        ExamModel exam = new ExamModel();

        exam.setIdExam(rs.getInt("id_examen"));
        exam.setNameExam(rs.getString("examen_nombre"));
        exam.setExamResult(rs.getString("examen_resultado"));
        exam.setIdPet(rs.getInt("MASCOTA_id_mascota"));

        return exam;
    }

    @Override
    public int uploadExam(ExamModel exam) {
        int resp = 0;
        String query = "INSERT INTO examen VALUES (0, ?, ? , ?)";

        try (PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setString(1, exam.getNameExam());
            stmt.setString(2, exam.getExamResult());
            stmt.setInt(3, exam.getIdPet());

            resp = stmt.executeUpdate();
        } catch ( SQLException e) {
            e.printStackTrace();
        }
        return resp;
    }

    public int uploadPrescription(PrescriptionModel prescription) throws SQLException{
        int res = 0;
        String query = "INSERT INTO receta VALUES (0, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setString(1, prescription.getNameOwner());
            stmt.setString(2, prescription.getRutOwner());
            stmt.setString(3, prescription.getNameVet());
            stmt.setString(4, prescription.getNamePet());
            stmt.setString(5, prescription.getRecipeDesc());
            stmt.setInt(6, prescription.getIdPet());
            stmt.setString(7, prescription.getDate());

            res = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public List<PrescriptionModel> getPrescriptions(int idPet) {

        List<PrescriptionModel> prescriptions = new ArrayList<>();

        try (PreparedStatement stmt = getConnection()
                .prepareStatement("SELECT * FROM receta WHERE mascota_id_mascota = ?")) {
            stmt.setInt(1, idPet);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                prescriptions.add(mapPrescription(rs));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return prescriptions;
    }

    public PrescriptionModel mapPrescription(ResultSet rs) throws SQLException{
        PrescriptionModel presc = new PrescriptionModel();

        presc.setIdRecipe(rs.getInt("id_receta"));
        presc.setNameOwner(rs.getString("nombre_responsable"));
        presc.setRutOwner(rs.getString("rut_responsable"));
        presc.setNameVet(rs.getString("nombre_veterinario"));
        presc.setNamePet(rs.getString("nombre_veterinario"));
        presc.setRecipeDesc(rs.getString("receta_descrip"));
        presc.setIdPet(rs.getInt("mascota_id_mascota"));
        presc.setDate(rs.getString("fecha"));

        return presc;
    }
}
