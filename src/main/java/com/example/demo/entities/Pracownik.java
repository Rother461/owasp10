package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Pracownik {

    @JsonProperty("pracownik")
    private Dane pracownik;

    private static class Dane {
        @JsonProperty("imie")
        private String imie;

        @JsonProperty("nazwisko")
        private String nazwisko;

        @JsonProperty("stanowisko")
        private String stanowisko;

        @JsonProperty("dzial")
        private String dzial;

        @JsonProperty("email")
        private String email;

        @JsonProperty("telefon")
        private String telefon;
    }
}
