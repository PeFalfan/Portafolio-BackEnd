package cl.medvet.medvetbackend.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class ExamModel implements Serializable {

    private static final long serialVersionUID = 7885157731500285777L;
    private int idExam;
    private String bodyExam;

    public ExamModel() {
    }

    public int getIdExam() {
        return idExam;
    }

    public void setIdExam(int idExam) {
        this.idExam = idExam;
    }

    public String getBodyExam() {
        return bodyExam;
    }

    public void setBodyExam(String bodyExam) {
        this.bodyExam = bodyExam;
    }
}
