package com.example.spring_batch.models;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Tiempo {
    private String localidad;
    private LocalDate fecha;
    private int temperatura;
}
