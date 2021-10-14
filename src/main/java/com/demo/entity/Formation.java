package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "formations")
@NoArgsConstructor
@AllArgsConstructor


public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")

    private String name;

    @Column(name="date_debut")
    private Date dateDebut;

    @Column(name="date_fin")
    private String DateFin;

    @ManyToOne(optional=false)
    @JoinColumn(name="id_formateur")
    private Formateur formateur;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "formations")
    private Set<Etudiant> etudiants = new HashSet<>();

   /* public Formation(String name, Date dateDebut, String dateFin) {
        this.name = name;
        this.dateDebut = dateDebut;
       this.DateFin = dateFin;

    }*/


}
