package cl.medvet.medvetbackend.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.util.List;

@EntityScan
public class PrescriptionsToDownload implements Serializable {


    private static final long serialVersionUID = 2517750677250717645L;
    private int idPrescription;
    private String title;
    private String prestcription;

    public PrescriptionsToDownload() {
    }

    public PrescriptionsToDownload(int idPrescription, String prestcription, String title) {
        this.idPrescription = idPrescription;
        this.prestcription = prestcription;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIdPrescription() {
        return idPrescription;
    }

    public void setIdPrescription(int idPrescription) {
        this.idPrescription = idPrescription;
    }

    public String getPrestcription() {
        return prestcription;
    }

    public void setPrestcription(String prestcription) {
        this.prestcription = prestcription;
    }
}
