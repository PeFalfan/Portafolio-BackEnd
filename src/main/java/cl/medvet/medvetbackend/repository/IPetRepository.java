package cl.medvet.medvetbackend.repository;

import cl.medvet.medvetbackend.models.PetModel;

import java.util.List;

public interface IPetRepository {

    public int editPet(PetModel pet);

    public int createPet(PetModel pet);

    public int deletePet(int idPet);

    public List<PetModel> getPetsByOwner(String rut);

}
