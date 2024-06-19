package model;

import lombok.Data;

@Data
public class Categorie {
    private CategorieEnum type;

    public Categorie(CategorieEnum type) {
        this.type = type;
    }
}
