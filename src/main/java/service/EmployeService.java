package service;

import model.Employe;
import model.Pointage;
import model.Calendrier;
import java.time.LocalDate;
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

        int heuresParJour = 8;
        boolean estHeuresSupp = heuresTravaillees > heuresParJour;

        // Calcul des heures normales
        salaire += Math.min(heuresTravaillees, heuresParJour) * tauxHoraire;

        // Calcul des heures supplémentaires
        if (estHeuresSupp) {
            int heuresSupplementaires = heuresTravaillees - heuresParJour;
            if (heuresSupplementaires <= 4) {
                salaire += heuresSupplementaires * tauxHoraire * 1.3;
            } else {
                salaire += 4 * tauxHoraire * 1.3;
                salaire += (heuresSupplementaires - 4) * tauxHoraire * 1.5;
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
