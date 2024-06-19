package model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Employe {
    private String nom;
    private String prenom;
    private String numeroMatricule;
    private LocalDate dateNaissance;
    private LocalDate dateEmbauche;
    private LocalDate dateFinContrat;
    private Categorie categorie;
    private Salaire salaire;
}
