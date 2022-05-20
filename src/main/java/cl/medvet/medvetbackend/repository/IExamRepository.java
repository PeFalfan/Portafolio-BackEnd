package cl.medvet.medvetbackend.repository;

import cl.medvet.medvetbackend.models.ExamModel;

import java.util.List;

public interface IExamRepository {

    public List<ExamModel> getExamsByPet(int idPet);

    public int uploadExam(ExamModel exam);
}
