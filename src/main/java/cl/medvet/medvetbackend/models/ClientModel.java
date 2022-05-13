package cl.medvet.medvetbackend.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class ClientModel implements Serializable {

    private static final long serialVersionUID = 8598244563809148177L;
    private String clientRut;
    private String clientName;
    private String clientLastNames;
    private String clientPhone;
    private String clientEmail;
    private String clientEmailRecovery;
    private String clientPassword;
    private AddressModel clientAddress;

    public ClientModel() {
    }

    public String getClientEmailRecovery() {
        return clientEmailRecovery;
    }

    public void setClientEmailRecovery(String clientEmailRecovery) {
        this.clientEmailRecovery = clientEmailRecovery;
    }

    public String getClientRut() {
        return clientRut;
    }

    public void setClientRut(String clientRut) {
        this.clientRut = clientRut;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLastNames() {
        return clientLastNames;
    }

    public void setClientLastNames(String clientLastNames) {
        this.clientLastNames = clientLastNames;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    public AddressModel getClientAddress() { return clientAddress; }

    public void setClientAddress(AddressModel clientAddress) { this.clientAddress = clientAddress; }
}

