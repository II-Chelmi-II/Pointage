package service;

import model.Employe;
import model.Pointage;
import model.Calendrier;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EmployeService {

    public double calculerSalaireBrut(Employe employe, List<Pointage> pointages, Calendrier calendrier) {
        double salaireBrut = 0;
        for (Pointage pointage : pointages) {
            salaireBrut += calculerSalairePourPointage(employe, pointage, calendrier);
        }
        return salaireBrut;
    }

    private double calculerSalairePourPointage(Employe employe, Pointage pointage, Calendrier calendrier) {
        double salaire = 0;
        int heuresTravaillees = pointage.getHeuresTravaillees();
        int heuresNormales = employe.getCategorie().getType().getHeuresNormalesParSemaine();
        double tauxHoraire = employe.getCategorie().getType().getSalaireParSemaine() / heuresNormales;

        // Calcul des heures normales
        if (heuresTravaillees <= heuresNormales) {
            salaire += heuresTravaillees * tauxHoraire;
        } else {
            salaire += heuresNormales * tauxHoraire;
        }

        // Calcul des heures supplémentaires
        if (heuresTravaillees > heuresNormales) {
            int heuresSupplementaires = Math.min(heuresTravaillees - heuresNormales, 20);
            if (heuresSupplementaires <= 8) {
                salaire += heuresSupplementaires * tauxHoraire * 1.3;
            } else {
                salaire += 8 * tauxHoraire * 1.3;
                salaire += (heuresSupplementaires - 8) * tauxHoraire * 1.5;
            }
        }

        // Calcul des heures majorées
        if (calendrier.estJourFerie(pointage.getDate())) {
            salaire += heuresTravaillees * tauxHoraire * 1.5;
        } else if (pointage.getDate().getDayOfWeek().name().equals("SUNDAY")) {
            salaire += heuresTravaillees * tauxHoraire * 1.4;
        } else if (pointage.getHeureDebut().getHour() >= 22 || pointage.getHeureDebut().getHour() < 6) {
            salaire += heuresTravaillees * tauxHoraire * 1.3;
        }

        return salaire;
    }

    public double calculerSalaireNet(double salaireBrut) {
        return salaireBrut * 0.8;
    }
}
