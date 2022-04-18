package cl.medvet.medvetbackend.services;

import cl.medvet.medvetbackend.models.ClientModel;
import cl.medvet.medvetbackend.models.PetModel;
import cl.medvet.medvetbackend.models.ResponseModel;

public interface IClientService {

    public ResponseModel getClients();

    public ResponseModel getClientByRut(String rut);

    public ResponseModel deleteClient(String rut);

    public ResponseModel editClient(ClientModel client);

    public ResponseModel createClient(ClientModel client, PetModel pet);

    public ResponseModel recoverPassword(String email);
}
