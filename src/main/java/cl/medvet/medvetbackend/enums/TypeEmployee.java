package cl.medvet.medvetbackend.enums;

public enum TypeEmployee {
    ADMINISTRADOR("Administrador"),
    VETERINARIO("Veterinario"),
    RECEPCIONISTA("Recepcionista");

    public final String value;

    private TypeEmployee(String value) {
        this.value = value;
    }
}
