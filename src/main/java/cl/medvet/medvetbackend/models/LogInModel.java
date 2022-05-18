package cl.medvet.medvetbackend.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class LogInModel implements Serializable {

    private static final long serialVersionUID = -6606973339679501306L;

    private String email;
    private String password;

    public LogInModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LogInModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
