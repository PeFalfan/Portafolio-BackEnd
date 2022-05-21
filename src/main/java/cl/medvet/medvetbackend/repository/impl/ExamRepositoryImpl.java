package cl.medvet.medvetbackend.repository.impl;

import cl.medvet.medvetbackend.models.ExamModel;
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
}
