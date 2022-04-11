package cl.medvet.medvetbackend.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class EmployeeModel {
    private String rutEmployee;
    private String dvRut;
    private String nameEmployee;
    private String emailEmployee;
    private String speciality;
    private int typeEmployee;
    private String password;

    public EmployeeModel(String rutEmployee, String dvRut, String nameEmployee, String emailEmployee, String speciality, int typeEmployee, String password) {
        this.rutEmployee = rutEmployee;
        this.dvRut = dvRut;
        this.nameEmployee = nameEmployee;
        this.emailEmployee = emailEmployee;
        this.speciality = speciality;
        this.typeEmployee = typeEmployee;
        this.password = password;
    }

    public EmployeeModel() {
    }

    public String getRutEmployee() {
        return rutEmployee;
    }

    public void setRutEmployee(String rutEmployee) {
        this.rutEmployee = rutEmployee;
    }

    public String getDvRut() {
        return dvRut;
    }

    public void setDvRut(String dvRut) {
        this.dvRut = dvRut;
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
