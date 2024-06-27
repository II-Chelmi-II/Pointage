package service;

import model.*;

import java.util.List;

public class EmployeService {

    public Salaire calculerSalaire(Employe employe, List<Pointage> pointages, Calendrier calendrier) {
        double salaireBrut = 0;

        for (Pointage pointage : pointages) {
            salaireBrut += calculerSalairePourPointage(employe, pointage, calendrier);
        }

        Salaire salaire = new Salaire(salaireBrut);
        salaire.calculerSalaireNet(); // Calcul du salaire net

        return salaire;
    }

    private double calculerSalairePourPointage(Employe employe, Pointage pointage, Calendrier calendrier) {
        double salaire = 0;
        int heuresTravaillees = pointage.getHeuresTravaillees();
        double tauxHoraire = employe.getCategorie().getType().getSalaireParSemaine() / employe.getCategorie().getType().getHeuresNormalesParSemaine();

        // Calcul des heures normales
        salaire += heuresTravaillees * tauxHoraire;

        // Calcul des heures supplémentaires
        if (!employe.getCategorie().getType().equals(CategorieEnum.CADRE_SUPERIEUR)) {
            int heuresNormalesParSemaine = employe.getCategorie().getType().getHeuresNormalesParSemaine();
            int heuresSupplementaires = Math.max(heuresTravaillees - heuresNormalesParSemaine, 0);
            int heuresSupplementairesPrisesEnCompte = Math.min(heuresSupplementaires, 20); // Limite à 20h max
            int heuresSupplementairesRestantes = heuresSupplementaires - heuresSupplementairesPrisesEnCompte;

            if (heuresSupplementairesPrisesEnCompte > 0) {
                salaire += heuresSupplementairesPrisesEnCompte * tauxHoraire * 1.3; // 130% pour les premières 8h supplémentaires
            }

            if (heuresSupplementairesRestantes > 0) {
                salaire += heuresSupplementairesRestantes * tauxHoraire * 1.5; // 150% pour les suivantes
            }
        }

        // Calcul des majorations pour jours fériés, travail de nuit, etc.
        if (calendrier.estJourFerie(pointage.getDate())) {
            salaire += heuresTravaillees * tauxHoraire * 1.5; // 150% pour les jours fériés
        } else if (pointage.getHeureDebut().getHour() >= 22 || pointage.getHeureDebut().getHour() < 6) {
            salaire += heuresTravaillees * tauxHoraire * 1.3; // 130% pour le travail de nuit
        } else if (pointage.getDate().getDayOfWeek().name().equals("SUNDAY")) {
            salaire += heuresTravaillees * tauxHoraire * 1.4; // 140% pour le dimanche
        }

        return salaire;
    }
}
