package cl.medvet.medvetbackend.models;

import java.io.Serializable;

public class ClientPetModel implements Serializable {

    private static final long serialVersionUID = -3557466930377109140L;
    private ClientModel client;
    private PetModel pet;

    public ClientPetModel(ClientModel client, PetModel pet) {
        this.client = client;
        this.pet = pet;
    }

    public ClientPetModel() {
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }

    public PetModel getPet() {
        return pet;
    }

    public void setPet(PetModel pet) {
        this.pet = pet;
    }
}
