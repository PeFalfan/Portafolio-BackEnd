package cl.medvet.medvetbackend.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class EmployeeModel implements Serializable {

    private static final long serialVersionUID = 230857303795703086L;
    private String rutEmployee;
    private String nameEmployee;
    private String emailEmployee;
    private String speciality;
    private int typeEmployee;
    private String password;
    private String statusEmployee;

    public EmployeeModel() {
    }

    public String getStatusEmployee() {
        return statusEmployee;
    }

    public void setStatusEmployee(String statusEmployee) {
        this.statusEmployee = statusEmployee;
    }

    public String getRutEmployee() {
        return rutEmployee;
    }

    public void setRutEmployee(String rutEmployee) {
        this.rutEmployee = rutEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getEmailEmployee() {
        return emailEmployee;
    }

    public void setEmailEmployee(String emailEmployee) {
        this.emailEmployee = emailEmployee;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getTypeEmployee() {
        return typeEmployee;
    }

    public void setTypeEmployee(int typeEmployee) {
        this.typeEmployee = typeEmployee;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
