import model.Calendrier;
import model.Employe;
import model.Pointage;
import model.Categorie;
import model.CategorieEnum;
import model.JourFerie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.PointageService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class PointageServiceTest {

    @Test
    public void testCreerPointageJourNormal() {
        PointageService pointageService = new PointageService();
        Calendrier calendrier = new Calendrier();
        calendrier.setJoursTravailles(Arrays.asList(
                LocalDate.of(2024, 6, 3),
                LocalDate.of(2024, 6, 4),
                LocalDate.of(2024, 6, 5)
        ));
        calendrier.setJoursFeries(Arrays.asList(
                new JourFerie(LocalDate.of(2024, 6, 17))
        ));

        Employe employe = new Employe();
        employe.setCategorie(new Categorie(CategorieEnum.NORMAL));

        LocalDate date = LocalDate.of(2024, 6, 3); // Un jour normal
        LocalTime heureDebut = LocalTime.of(8, 0);
        LocalTime heureFin = LocalTime.of(16, 0);

        Pointage pointage = pointageService.creerPointage(employe, date, heureDebut, heureFin, calendrier);

        Assertions.assertFalse(pointage.isEstJourFerie());
        Assertions.assertFalse(pointage.isEstNuit());
    }

    @Test
    public void testCreerPointageJourFerie() {
        PointageService pointageService = new PointageService();
        Calendrier calendrier = new Calendrier();
        calendrier.setJoursTravailles(Arrays.asList(
                LocalDate.of(2024, 6, 3),
                LocalDate.of(2024, 6, 4),
                LocalDate.of(2024, 6, 5)
        ));
        calendrier.setJoursFeries(Arrays.asList(
                new JourFerie(LocalDate.of(2024, 6, 17))
        ));

        Employe employe = new Employe();
        employe.setCategorie(new Categorie(CategorieEnum.NORMAL));

        LocalDate date = LocalDate.of(2024, 6, 17); // Un jour férié
        LocalTime heureDebut = LocalTime.of(8, 0);
        LocalTime heureFin = LocalTime.of(16, 0);

        Pointage pointage = pointageService.creerPointage(employe, date, heureDebut, heureFin, calendrier);

        Assertions.assertTrue(pointage.isEstJourFerie());
        Assertions.assertFalse(pointage.isEstNuit());
    }

    @Test
    public void testCreerPointageNuit() {
        PointageService pointageService = new PointageService();
        Calendrier calendrier = new Calendrier();
        calendrier.setJoursTravailles(Arrays.asList(
                LocalDate.of(2024, 6, 3),
                LocalDate.of(2024, 6, 4),
                LocalDate.of(2024, 6, 5)
        ));
        calendrier.setJoursFeries(Arrays.asList(
                new JourFerie(LocalDate.of(2024, 6, 17))
        ));

        Employe employe = new Employe();
        employe.setCategorie(new Categorie(CategorieEnum.NORMAL));

        LocalDate date = LocalDate.of(2024, 6, 3); // Un jour normal
        LocalTime heureDebut = LocalTime.of(22, 0);
        LocalTime heureFin = LocalTime.of(6, 0);

        Pointage pointage = pointageService.creerPointage(employe, date, heureDebut, heureFin, calendrier);

        Assertions.assertFalse(pointage.isEstJourFerie());
        Assertions.assertTrue(pointage.isEstNuit());
    }

    @Test
    public void testModifierPointage() {
        PointageService pointageService = new PointageService();
        Calendrier calendrier = new Calendrier();
        calendrier.setJoursTravailles(Arrays.asList(
                LocalDate.of(2024, 6, 3),
                LocalDate.of(2024, 6, 4),
                LocalDate.of(2024, 6, 5)
        ));
        calendrier.setJoursFeries(Arrays.asList(
                new JourFerie(LocalDate.of(2024, 6, 17))
        ));

        Employe employe = new Employe();
        employe.setCategorie(new Categorie(CategorieEnum.NORMAL));

        LocalDate date = LocalDate.of(2024, 6, 3); // Un jour normal
        LocalTime heureDebut = LocalTime.of(8, 0);
        LocalTime heureFin = LocalTime.of(16, 0);

        Pointage pointage = new Pointage(employe, date, heureDebut, heureFin, false, false);

        LocalTime nouvelleHeureDebut = LocalTime.of(22, 0);
        LocalTime nouvelleHeureFin = LocalTime.of(6, 0);

        pointageService.modifierPointage(pointage, nouvelleHeureDebut, nouvelleHeureFin, calendrier);

        Assertions.assertEquals(nouvelleHeureDebut, pointage.getHeureDebut());
        Assertions.assertEquals(nouvelleHeureFin, pointage.getHeureFin());
        Assertions.assertFalse(pointage.isEstJourFerie());
        Assertions.assertTrue(pointage.isEstNuit());
    }

    @Test
    public void testModifierPointageJourFerie() {
        PointageService pointageService = new PointageService();
        Calendrier calendrier = new Calendrier();
        calendrier.setJoursTravailles(Arrays.asList(
                LocalDate.of(2024, 6, 3),
                LocalDate.of(2024, 6, 4),
                LocalDate.of(2024, 6, 5)
        ));
        calendrier.setJoursFeries(Arrays.asList(
                new JourFerie(LocalDate.of(2024, 6, 17))
        ));

        Employe employe = new Employe();
        employe.setCategorie(new Categorie(CategorieEnum.NORMAL));

        LocalDate date = LocalDate.of(2024, 6, 17); // Un jour férié
        LocalTime heureDebut = LocalTime.of(8, 0);
        LocalTime heureFin = LocalTime.of(16, 0);

        Pointage pointage = new Pointage(employe, date, heureDebut, heureFin, true, false);

        LocalTime nouvelleHeureDebut = LocalTime.of(22, 0);
        LocalTime nouvelleHeureFin = LocalTime.of(6, 0);

        pointageService.modifierPointage(pointage, nouvelleHeureDebut, nouvelleHeureFin, calendrier);

        Assertions.assertEquals(nouvelleHeureDebut, pointage.getHeureDebut());
        Assertions.assertEquals(nouvelleHeureFin, pointage.getHeureFin());
        Assertions.assertTrue(pointage.isEstJourFerie());
        Assertions.assertTrue(pointage.isEstNuit());
    }
}
