package cl.medvet.medvetbackend.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class RecipeModel implements Serializable {

    private static final long serialVersionUID = 5771212765416077308L;

    private int idRecipe;
    private String namePet;
    private String nameOwner;
    private String nameVet;
    private String recipeDesc;

    public RecipeModel() {
    }

    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    public String getNamePet() {
        return namePet;
    }

    public void setNamePet(String namePet) {
        this.namePet = namePet;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getNameVet() {
        return nameVet;
    }

    public void setNameVet(String nameVet) {
        this.nameVet = nameVet;
    }

    public String getRecipeDesc() {
        return recipeDesc;
    }

    public void setRecipeDesc(String recipeDesc) {
        this.recipeDesc = recipeDesc;
    }
}
