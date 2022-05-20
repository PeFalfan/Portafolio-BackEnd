package cl.medvet.medvetbackend.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class ExamModel implements Serializable {

    private static final long serialVersionUID = 7885157731500285777L;
    private int idExam;
    private String examResult;
    private int idPet;

    public ExamModel() {
    }

    public int getIdPet() {
        return idPet;
    }

    public void setIdPet(int idPet) {
        this.idPet = idPet;
    }

    public int getIdExam() {
        return idExam;
    }

    public void setIdExam(int idExam) {
        this.idExam = idExam;
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult;
    }
}
