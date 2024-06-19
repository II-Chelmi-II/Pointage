package service;

import model.Calendrier;
import model.JourFerie;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendrierService {

    public Calendrier creerCalendrierDeJuin() {
        Calendrier calendrier = new Calendrier();
        LocalDate debutJuin = LocalDate.of(2024, 6, 1);
        LocalDate finJuin = LocalDate.of(2024, 6, 30);

        List<LocalDate> joursTravailles = new ArrayList<>();
        for (LocalDate date = debutJuin; date.isBefore(finJuin.plusDays(1)); date = date.plusDays(1)) {
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                joursTravailles.add(date);
            }
        }
        calendrier.setJoursTravailles(joursTravailles);

        List<JourFerie> joursFeries = new ArrayList<>();
        joursFeries.add(new JourFerie(LocalDate.of(2024, 6, 17)));
        joursFeries.add(new JourFerie(LocalDate.of(2024, 6, 25)));
        joursFeries.add(new JourFerie(LocalDate.of(2024, 6, 26)));
        calendrier.setJoursFeries(joursFeries);

        return calendrier;
    }

    public boolean estJourTravaille(LocalDate date, Calendrier calendrier) {
        return calendrier.estJourTravaille(date);
    }

    public boolean estJourFerie(LocalDate date, Calendrier calendrier) {
        return calendrier.estJourFerie(date);
    }
}
