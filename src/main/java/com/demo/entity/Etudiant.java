package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Etudiants")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "First_name")
    private String firstName;

    @Column(name = "Last_name")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private int phone;

    @Column(name = "NIC")
    private int nic;

    @Column(name = "level")
    private int level;

    @Column(name = "Speciality")
    private String speciality;

    public Etudiant(String firstName, String lastName, String email, int phone, int nic, int level, String speciality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.nic = nic;
        this.level = level;
        this.speciality = speciality;
    }
   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_JOIN", nullable=true)
    private Etud_Form etud_form;*/
   @ManyToMany(fetch = FetchType.LAZY,
           cascade = {
                   CascadeType.PERSIST,
                   CascadeType.MERGE
           })
   @JoinTable(name = "etudiant_formation",
           joinColumns = { @JoinColumn(name = "etudiant_id") },
           inverseJoinColumns = { @JoinColumn(name = "formation_id") })
   private Set<Formation> formations = new HashSet<>();
}
