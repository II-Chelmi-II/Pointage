package model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Calendrier {
    private List<LocalDate> joursTravailles;
    private List<JourFerie> joursFeries;

    public boolean estJourTravaille(LocalDate date) {
        for (JourFerie jourFerie : joursFeries) {
            if (jourFerie.getDate().equals(date)) {
                return false;
            }
        }
        return joursTravailles.contains(date);
    }

    public boolean estJourFerie(LocalDate date) {
        for (JourFerie jourFerie : joursFeries) {
            if (jourFerie.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }
}
