package test;

import model.Calendrier;
import org.junit.jupiter.api.Test;
import service.CalendrierService;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CalendrierServiceTest {

    @Test
    public void testCreerCalendrierDeJuin() {
        CalendrierService calendrierService = new CalendrierService();
        Calendrier calendrier = calendrierService.creerCalendrierDeJuin();

        // Vérifier les jours ouvrables
        assertFalse(calendrier.estJourFerie(LocalDate.of(2024, 6, 1))); // Mercredi
        assertFalse(calendrier.estJourFerie(LocalDate.of(2024, 6, 2))); // Jeudi
        assertFalse(calendrier.estJourFerie(LocalDate.of(2024, 6, 3))); // Vendredi
        assertFalse(calendrier.estJourFerie(LocalDate.of(2024, 6, 4))); // Samedi
        assertFalse(calendrier.estJourFerie(LocalDate.of(2024, 6, 9))); // Jeudi
        assertFalse(calendrier.estJourFerie(LocalDate.of(2024, 6, 10))); // Vendredi
        assertFalse(calendrier.estJourFerie(LocalDate.of(2024, 6, 11))); // Samedi
        assertFalse(calendrier.estJourFerie(LocalDate.of(2024, 6, 15))); // Mercredi
        assertFalse(calendrier.estJourFerie(LocalDate.of(2024, 6, 16))); // Jeudi
        assertFalse(calendrier.estJourFerie(LocalDate.of(2024, 6, 22))); // Mercredi
        assertFalse(calendrier.estJourFerie(LocalDate.of(2024, 6, 23))); // Jeudi
        assertFalse(calendrier.estJourFerie(LocalDate.of(2024, 6, 29))); // Mercredi
        assertFalse(calendrier.estJourFerie(LocalDate.of(2024, 6, 30))); // Jeudi

        // Vérifier les jours fériés
        assertTrue(calendrier.estJourFerie(LocalDate.of(2024, 6, 17))); // Lundi
        assertTrue(calendrier.estJourFerie(LocalDate.of(2024, 6, 25))); // Mardi
        assertTrue(calendrier.estJourFerie(LocalDate.of(2024, 6, 26))); // Mercredi
    }
}
