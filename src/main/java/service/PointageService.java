package service;

import model.Calendrier;
import model.Employe;
import model.Pointage;

import java.time.LocalDate;
import java.time.LocalTime;

public class PointageService {

    public Pointage creerPointage(Employe employe, LocalDate date, LocalTime heureDebut, LocalTime heureFin, Calendrier calendrier) {
        boolean estJourFerie = calendrier.estJourFerie(date);
        boolean estNuit = heureDebut.isAfter(LocalTime.of(20, 0)) || heureFin.isBefore(LocalTime.of(6, 0));

        return new Pointage(employe, date, heureDebut, heureFin, estJourFerie, estNuit);
    }

    public void modifierPointage(Pointage pointage, LocalTime heureDebut, LocalTime heureFin, Calendrier calendrier) {
        boolean estJourFerie = calendrier.estJourFerie(pointage.getDate());
        boolean estNuit = heureDebut.isAfter(LocalTime.of(20, 0)) || heureFin.isBefore(LocalTime.of(6, 0));

        pointage.setHeureDebut(heureDebut);
        pointage.setHeureFin(heureFin);
        pointage.setEstJourFerie(estJourFerie);
        pointage.setEstNuit(estNuit);
    }
}
