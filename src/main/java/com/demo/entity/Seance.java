package com.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Seances")
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="id_formateur")
    private Formateur formateur;

    @ManyToOne(optional=false)
    @JoinColumn(name="id_salle")
    private Salle salle;
}
