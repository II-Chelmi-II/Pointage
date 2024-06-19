package model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class JourFerie {
    private LocalDate date;

    public JourFerie(LocalDate date) {
        this.date = date;
    }
}
