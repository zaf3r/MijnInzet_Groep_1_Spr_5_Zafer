package makeitwork.mijninzet.model.dto;

import makeitwork.mijninzet.controller.AbstractController;


import java.time.LocalDate;

public class TaskDTO extends AbstractController {

    private int Userid;

    private String id;

    private String titel;

    private String locatie;

    private String beschrijvingLang;

    private LocalDate startDatum;

    private int uren;

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int userid) {
        Userid = userid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getBeschrijvingLang() {
        return beschrijvingLang;
    }

    public void setBeschrijvingLang(String beschrijvingLang) {
        this.beschrijvingLang = beschrijvingLang;
    }

    public LocalDate getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(LocalDate startDatum) {
        this.startDatum = startDatum;
    }

    public int getUren() {
        return uren;
    }

    public void setUren(int uren) {
        this.uren = uren;
    }
}
