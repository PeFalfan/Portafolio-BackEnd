package cl.medvet.medvetbackend.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class ObservationModel implements Serializable {

    private static final long serialVersionUID = 1910214044825511548L;

    private int observationId;
    private String observationDetail;
    private String employeeRut;
    private int petId;

    public ObservationModel() {
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

    public String getEmployeeRut() {
        return employeeRut;
    }

    public void setEmployeeRut(String idEmployee) {
        this.employeeRut = idEmployee;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }
}
