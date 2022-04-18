package cl.medvet.medvetbackend.services;

import cl.medvet.medvetbackend.models.PetModel;
import cl.medvet.medvetbackend.models.ResponseModel;

public interface IPetService {

    public ResponseModel editPet(PetModel pet);

    public ResponseModel createPet(PetModel pet);

    public ResponseModel deletePet(int idPet);

    public ResponseModel getPetsByOwner(String rut);
}
