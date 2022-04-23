package cl.medvet.medvetbackend.models;

import java.io.Serializable;

public class ObservationModel implements Serializable {

    private static final long serialVersionUID = 1910214044825511548L;

    private int observationId;
    private String observationDetail;
    private String idEmployee;
    private int petId;

    public ObservationModel() {
    }

    public ObservationModel(int observationId, String observationDetail, String idEmployee, int petId) {
        this.observationId = observationId;
        this.observationDetail = observationDetail;
        this.idEmployee = idEmployee;
        this.petId = petId;
    }

    public int getObservationId() {
        return observationId;
    }

    public void setObservationId(int observationId) {
        this.observationId = observationId;
    }

    public String getObservationDetail() {
        return observationDetail;
    }

    public void setObservationDetail(String observationDetail) {
        this.observationDetail = observationDetail;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }
}
