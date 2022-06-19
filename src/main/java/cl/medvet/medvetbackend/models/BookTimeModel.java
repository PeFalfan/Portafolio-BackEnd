package cl.medvet.medvetbackend.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class BookTimeModel implements Serializable {


    private static final long serialVersionUID = 4408558015954944634L;
    private int id;
    private String date;
    private String time;
    private String reservationDate;
    private String rutEmployee;
    private int idState;
    private int idPet;

    public BookTimeModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getIdPet() {
        return idPet;
    }

    public void setIdPet(int idPet) {
        this.idPet = idPet;
    }

    public String getRutEmployee() {
        return rutEmployee;
    }

    public void setRutEmployee(String rutEmployee) {
        this.rutEmployee = rutEmployee;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }
}
