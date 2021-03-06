package cl.medvet.medvetbackend.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class PetModel implements Serializable {

    private static final long serialVersionUID = -6623852257182079873L;
    private int idPet;
    private String speciesPet;
    private String breedPet;
    private String agePet;
    private String weightPet;
    private String sexPet;
    private String observationPet;
    private String image;
    private String namePet;
    private String rutOwner;

    public PetModel() {
    }

    public String getSexPet() {
        return sexPet;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setSexPet(String sexPet) {
        this.sexPet = sexPet;
    }

    public String getNamePet() {
        return namePet;
    }

    public void setNamePet(String namePet) {
        this.namePet = namePet;
    }

    public int getIdPet() {
        return idPet;
    }

    public void setIdPet(int idPet) {
        this.idPet = idPet;
    }

    public String getSpeciesPet() {
        return speciesPet;
    }

    public void setSpeciesPet(String speciesPet) {
        this.speciesPet = speciesPet;
    }

    public String getBreedPet() {
        return breedPet;
    }

    public void setBreedPet(String breedPet) {
        this.breedPet = breedPet;
    }

    public String getAgePet() {
        return agePet;
    }

    public void setAgePet(String agePet) {
        this.agePet = agePet;
    }

    public String getWeightPet() {
        return weightPet;
    }

    public void setWeightPet(String weightPet) {
        this.weightPet = weightPet;
    }

    public String getObservationPet() {
        return observationPet;
    }

    public void setObservationPet(String observationPet) {
        this.observationPet = observationPet;
    }

    public String getRutOwner() {
        return rutOwner;
    }

    public void setRutOwner(String rutOwner) {
        this.rutOwner = rutOwner;
    }
}
