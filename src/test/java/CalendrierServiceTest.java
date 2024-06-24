import model.Calendrier;
import model.JourFerie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.CalendrierService;

import java.time.LocalDate;
import java.util.List;

public class CalendrierServiceTest {

    @Test
    public void testCreerCalendrierDeJuin() {
        CalendrierService calendrierService = new CalendrierService();
        Calendrier calendrier = calendrierService.creerCalendrierDeJuin();

        // Vérifier les jours travaillés
        List<LocalDate> joursTravailles = calendrier.getJoursTravailles();
        Assertions.assertFalse(joursTravailles.contains(LocalDate.of(2024, 6, 1))); // samedi
        Assertions.assertTrue(joursTravailles.contains(LocalDate.of(2024, 6, 3))); // lundi
        Assertions.assertFalse(joursTravailles.contains(LocalDate.of(2024, 6, 2))); // dimanche

        // Vérifier les jours fériés
        List<JourFerie> joursFeries = calendrier.getJoursFeries();
        Assertions.assertTrue(joursFeries.contains(new JourFerie(LocalDate.of(2024, 6, 17))));
        Assertions.assertTrue(joursFeries.contains(new JourFerie(LocalDate.of(2024, 6, 25))));
        Assertions.assertTrue(joursFeries.contains(new JourFerie(LocalDate.of(2024, 6, 26))));
    }

    @Test
    public void testEstJourTravaille() {
        CalendrierService calendrierService = new CalendrierService();
        Calendrier calendrier = calendrierService.creerCalendrierDeJuin();

        // Vérifier qu'un jour travaillé est correctement identifié
        Assertions.assertTrue(calendrierService.estJourTravaille(LocalDate.of(2024, 6, 3), calendrier)); // lundi

        // Vérifier qu'un jour non travaillé (weekend) est correctement identifié
        Assertions.assertFalse(calendrierService.estJourTravaille(LocalDate.of(2024, 6, 1), calendrier)); // samedi

        // Vérifier qu'un jour férié n'est pas considéré comme jour travaillé
        Assertions.assertFalse(calendrierService.estJourTravaille(LocalDate.of(2024, 6, 17), calendrier)); // jour férié
    }

    @Test
    public void testEstJourFerie() {
        CalendrierService calendrierService = new CalendrierService();
        Calendrier calendrier = calendrierService.creerCalendrierDeJuin();

        // Vérifier qu'un jour férié est correctement identifié
        Assertions.assertTrue(calendrierService.estJourFerie(LocalDate.of(2024, 6, 17), calendrier)); // jour férié

        // Vérifier qu'un jour non férié est correctement identifié
        Assertions.assertFalse(calendrierService.estJourFerie(LocalDate.of(2024, 6, 3), calendrier)); // lundi non férié
    }
}
