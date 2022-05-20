package cl.medvet.medvetbackend.repository.impl;

import cl.medvet.medvetbackend.models.PetModel;
import cl.medvet.medvetbackend.models.RecipeModel;
import cl.medvet.medvetbackend.repository.IPetRepository;
import cl.medvet.medvetbackend.util.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PetRepositoryImpl implements IPetRepository {

    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getConnection();
    }

    // Method to create a pet
    @Override
    public int createPet(PetModel pet) {

        int resPet = 0;
        String query = "INSERT INTO mascota VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmtPet = getConnection()
                .prepareStatement(query)) {

            stmtPet.setString(1,pet.getSpeciesPet());
            stmtPet.setString(2, pet.getBreedPet());
            stmtPet.setString(3,pet.getAgePet());
            stmtPet.setString(4, pet.getWeightPet());
            stmtPet.setString(5, pet.getSexPet());
            stmtPet.setString(6, pet.getObservationPet());
            stmtPet.setString(7, pet.getImage());
            stmtPet.setString(8, pet.getNamePet());
            stmtPet.setString(9, pet.getRutOwner());

            resPet = stmtPet.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return resPet;

    }

    // Method to edit a pet
    @Override
    public int editPet(PetModel pet) {

        int res = 0;

        String query = "UPDATE mascota SET especie_mascota = ?, raza = ?, edad_mascota = ?, peso_mascota = ?, sexo_mascota = ?, Observaciones = ?, foto = ?, nombre_mascota = ?, CLIENTE_rut_cliente = ? " +
                "WHERE id_mascota = ?";

        try ( PreparedStatement stmt = getConnection().
                prepareStatement(query)) {
            stmt.setString(1, pet.getSpeciesPet());
            stmt.setString(2, pet.getBreedPet());
            stmt.setString(3, pet.getAgePet());
            stmt.setString(4, pet.getWeightPet());
            stmt.setString(5, pet.getSexPet());
            stmt.setString(6, pet.getObservationPet());
            stmt.setString(7, pet.getImage());
            stmt.setString(8, pet.getNamePet());
            stmt.setString(9, pet.getRutOwner());
            stmt.setInt(10, pet.getIdPet());

            res = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    // Method to delete a pet
    @Override
    public int deletePet(int idPet) {

        int res = 0;

        try ( PreparedStatement stmt = getConnection().
                prepareStatement("DELETE FROM mascota WHERE id_mascota = ?")) {

            stmt.setInt(1, idPet);

            res = stmt.executeUpdate();

        } catch ( SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    // Method to Get all pets of a client
    @Override
    public List<PetModel> getPetsByOwner(String rut) {

        List<PetModel> pets = new ArrayList<>();

        try (PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM mascota WHERE CLIENTE_rut_cliente = ?")) {

            stmt.setString(1, rut);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                pets.add(mapPet(rs));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pets;
    }

    // Method to map a pet
    private PetModel mapPet(ResultSet rs) throws SQLException {

        PetModel pet = new PetModel();

        pet.setIdPet(rs.getInt("id_mascota"));
        pet.setSpeciesPet(rs.getString("especie_mascota"));
        pet.setBreedPet(rs.getString("raza"));
        pet.setAgePet(rs.getString("edad_mascota"));
        pet.setWeightPet(rs.getString("peso_mascota"));
        pet.setSexPet(rs.getString("sexo_mascota"));
        pet.setObservationPet(rs.getString("Observaciones"));
        pet.setImage(rs.getString("foto"));
        pet.setNamePet(rs.getString("nombre_mascota"));
        pet.setRutOwner(rs.getString("CLIENTE_rut_cliente"));

        return pet;

    }

    public RecipeModel getRecipeById(int idRecipe){

        RecipeModel recipe = new RecipeModel();

        try(PreparedStatement stmt = getConnection()
                .prepareStatement("SELECT id_receta, nombre_paciente, nombre_responsable, receta_descrip, nombre_veterinario \n" +
                        "FROM receta\n" +
                        "WHERE id_receta = ?;")) {
            stmt.setInt(1, idRecipe);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                recipe = mapRecipe(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipe;
    }

    private RecipeModel mapRecipe(ResultSet rs) throws SQLException {

        RecipeModel recipe = new RecipeModel();

        recipe.setIdRecipe(rs.getInt("id_receta"));
        recipe.setNamePet(rs.getString("nombre_paciente"));
        recipe.setNameOwner(rs.getString("nombre_responsable"));
        recipe.setRecipeDesc(rs.getString("receta_descrip"));
        recipe.setNameVet(rs.getString("nombre_veterinario"));

        return recipe;
    }

}
