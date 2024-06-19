package model;

public enum CategorieEnum {
    CADRE_SUPERIEUR("Cadre Supérieur", 0, 0, 0),
    NORMAL("Normal", 40, 100000, 0),
    GARDIEN("Gardien", 56, 110000, 0),
    CHAUFFEUR("Chauffeur", 40, 0, 0);

    private final String nom;
    private final int heuresNormalesParSemaine;
    private final double salaireParSemaine;
    private final double montantIndemnite;

    CategorieEnum(String nom, int heuresNormalesParSemaine, double salaireParSemaine, double montantIndemnite) {
        this.nom = nom;
        this.heuresNormalesParSemaine = heuresNormalesParSemaine;
        this.salaireParSemaine = salaireParSemaine;
        this.montantIndemnite = montantIndemnite;
    }

    public String getNom() {
        return nom;
    }

    public int getHeuresNormalesParSemaine() {
        return heuresNormalesParSemaine;
    }

    public double getSalaireParSemaine() {
        return salaireParSemaine;
    }

    public double getMontantIndemnite() {
        return montantIndemnite;
    }
}
