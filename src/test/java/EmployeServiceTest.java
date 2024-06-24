import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.EmployeService;
import service.CalendrierService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EmployeServiceTest {

    @Test
    public void testCalculerSalaireBrutAvecHeuresNormales() {
        EmployeService employeService = new EmployeService();
        CalendrierService calendrierService = new CalendrierService();
        Calendrier calendrier = calendrierService.creerCalendrierDeJuin();

        Employe employe = new Employe();
        employe.setNom("Rakoto");
        employe.setPrenom("Jean");
        employe.setNumeroMatricule("12345");
        employe.setCategorie(new Categorie(CategorieEnum.NORMAL));
        employe.setSalaire(new Salaire());

        List<Pointage> pointages = new ArrayList<>();
        pointages.add(new Pointage(employe, LocalDate.of(2024, 6, 3), LocalTime.of(8, 0), LocalTime.of(16, 0), false, false)); // 8h Lundi

        double salaireBrut = employeService.calculerSalaireBrut(employe, pointages, calendrier);
        double salaireNormal = 100000.0 / 40 * 8; // 8h à taux normal

        Assertions.assertEquals(salaireNormal, salaireBrut, 0.01);
    }

    @Test
    public void testCalculerSalaireBrutAvecHeuresSupplementaires() {
        EmployeService employeService = new EmployeService();
        CalendrierService calendrierService = new CalendrierService();
        Calendrier calendrier = calendrierService.creerCalendrierDeJuin();

        Employe employe = new Employe();
        employe.setNom("Rakoto");
        employe.setPrenom("Jean");
        employe.setNumeroMatricule("12345");
        employe.setCategorie(new Categorie(CategorieEnum.NORMAL));
        employe.setSalaire(new Salaire());

        List<Pointage> pointages = new ArrayList<>();
        pointages.add(new Pointage(employe, LocalDate.of(2024, 6, 3), LocalTime.of(8, 0), LocalTime.of(18, 0), false, false)); // 10h Lundi

        double salaireBrut = employeService.calculerSalaireBrut(employe, pointages, calendrier);
        double salaireNormal = 100000.0 / 40 * 8; // 8h à taux normal
        double salaireHS = 2 * (100000.0 / 40) * 1.3; // 2h à 130%

        Assertions.assertEquals(salaireNormal + salaireHS, salaireBrut, 0.01);
    }

    @Test
    public void testCalculerSalaireBrutAvecJourFerie() {
        EmployeService employeService = new EmployeService();
        CalendrierService calendrierService = new CalendrierService();
        Calendrier calendrier = calendrierService.creerCalendrierDeJuin();

        Employe employe = new Employe();
        employe.setNom("Rakoto");
        employe.setPrenom("Jean");
        employe.setNumeroMatricule("12345");
        employe.setCategorie(new Categorie(CategorieEnum.NORMAL));
        employe.setSalaire(new Salaire());

        List<Pointage> pointages = new ArrayList<>();
        pointages.add(new Pointage(employe, LocalDate.of(2024, 6, 17), LocalTime.of(8, 0), LocalTime.of(16, 0), false, false)); // 8h Jour Férié

        double salaireBrut = employeService.calculerSalaireBrut(employe, pointages, calendrier);
        double salaireNormal = 100000.0 / 40 * 8; // 8h à taux normal
        double salaireFerie = 8 * (100000.0 / 40) * 1.5; // 8h à 150%

        Assertions.assertEquals(salaireNormal + salaireFerie, salaireBrut, 0.01);
    }

    @Test
    public void testCalculerSalaireBrutAvecDimanche() {
        EmployeService employeService = new EmployeService();
        CalendrierService calendrierService = new CalendrierService();
        Calendrier calendrier = calendrierService.creerCalendrierDeJuin();

        Employe employe = new Employe();
        employe.setNom("Rakoto");
        employe.setPrenom("Jean");
        employe.setNumeroMatricule("12345");
        employe.setCategorie(new Categorie(CategorieEnum.NORMAL));
        employe.setSalaire(new Salaire());

        List<Pointage> pointages = new ArrayList<>();
        pointages.add(new Pointage(employe, LocalDate.of(2024, 6, 2), LocalTime.of(8, 0), LocalTime.of(16, 0), false, false)); // 8h Dimanche

        double salaireBrut = employeService.calculerSalaireBrut(employe, pointages, calendrier);
        double salaireNormal = 100000.0 / 40 * 8; // 8h à taux normal
        double salaireDimanche = 8 * (100000.0 / 40) * 1.4; // 8h à 140%

        Assertions.assertEquals(salaireNormal + salaireDimanche, salaireBrut, 0.01);
    }
}
