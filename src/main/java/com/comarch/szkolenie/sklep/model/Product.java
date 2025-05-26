package com.comarch.szkolenie.sklep.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private String description;
    private double prize;
    private LocalDate expirationDate;
    private int quantity;

    @Override
    public String toString() {
        return new StringBuilder().append("ID produktu: ")
                .append(this.getId())
                .append(" Nazwa produktu: ")
                .append(this.getName())
                .append(" Opis produktu: ")
                .append(this.getDescription())
                .append(" Data ważności: ")
                .append(this.getExpirationDate())
                .append(" Liczba dostępnych sztuk: ")
                .append(this.getQuantity())
                .toString();

    }
}




