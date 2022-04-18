package cl.medvet.medvetbackend.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class AddressModel implements Serializable {

    private static final long serialVersionUID = 5092567777822212835L;
    private int idAddress;
    private String nameAddress;
    private String foreanIdComune;

    public AddressModel() {
    }

    public AddressModel(int idAddress, String nameAddress, String foreanIdComune) {
        this.idAddress = idAddress;
        this.nameAddress = nameAddress;
        this.foreanIdComune = foreanIdComune;
    }

    public String getForeanIdComune() {
        return foreanIdComune;
    }

    public void setForeanIdComune(String foreanIdComune) {
        this.foreanIdComune = foreanIdComune;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public String getNameAddress() {
        return nameAddress;
    }

    public void setNameAddress(String nameAddress) {
        this.nameAddress = nameAddress;
    }
}
