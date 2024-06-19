package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pointage {
    private Employe employe;
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private boolean estJourFerie;
    private boolean estNuit;

    public int getHeuresTravaillees() {
        Duration dureeTravail = Duration.between(heureDebut, heureFin);
        return (int) dureeTravail.toHours();
    }
}
