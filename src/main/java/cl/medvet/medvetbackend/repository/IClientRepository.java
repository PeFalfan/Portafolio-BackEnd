package cl.medvet.medvetbackend.repository;

import cl.medvet.medvetbackend.models.ClientModel;
import cl.medvet.medvetbackend.models.PetModel;

import java.util.List;

public interface IClientRepository {

    public List<ClientModel> getAllCLients();

    public ClientModel getClientByRut(String rut);

    public int deleteClient(String rut);

    public int editClient(ClientModel client);

    public int createClient(ClientModel client, PetModel pet);

}
