package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Pointage {
    private Employe employe;
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private boolean estJourFerie;
    private boolean estNuit;

    public int getHeuresTravaillees() {
        return (int) java.time.Duration.between(heureDebut, heureFin).toHours();
    }
}
