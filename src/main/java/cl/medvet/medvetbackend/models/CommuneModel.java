package cl.medvet.medvetbackend.models;

import java.io.Serializable;

public class CommuneModel implements Serializable {

    private String cutComuna;
    private String nameCommune;
    private String cutProv;

    public String getCutComuna() {
        return cutComuna;
    }

    public void setCutComuna(String cutComuna) {
        this.cutComuna = cutComuna;
    }

    public String getNameCommune() {
        return nameCommune;
    }

    public void setNameCommune(String nameCommune) {
        this.nameCommune = nameCommune;
    }

    public String getCutProv() {
        return cutProv;
    }

    public void setCutProv(String cutProv) {
        this.cutProv = cutProv;
    }
}
