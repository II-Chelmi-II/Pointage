package model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Pointage {
    private Employe employe;
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private boolean estJourFerie;
    private boolean estNuit;
}
