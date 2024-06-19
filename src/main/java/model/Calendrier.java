package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calendrier {
    private List<LocalDate> joursTravailles;
    private List<JourFerie> joursFeries;

    public boolean estJourFerie(LocalDate date) {
        return joursFeries.stream().anyMatch(jf -> jf.getDate().equals(date));
    }

    public boolean estJourTravaille(LocalDate date) {
        return joursTravailles.contains(date);
    }
}
