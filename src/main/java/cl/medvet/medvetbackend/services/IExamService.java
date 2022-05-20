package cl.medvet.medvetbackend.services;

import cl.medvet.medvetbackend.models.ExamModel;
import cl.medvet.medvetbackend.models.ResponseModel;


public interface IExamService {

    public ResponseModel getExamsByPet(int idPet);

    public ResponseModel uploadExam(ExamModel exam);
}
