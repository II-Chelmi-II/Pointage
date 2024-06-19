package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
