package model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Calendrier {
    private List<LocalDate> joursTravailles;
    private List<JourFerie> joursFeries;

    public boolean estJourFerie(LocalDate date) {
        return joursFeries.stream().anyMatch(jf -> jf.getDate().equals(date));
    }
}
