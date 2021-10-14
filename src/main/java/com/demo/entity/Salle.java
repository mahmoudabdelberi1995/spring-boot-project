package com.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Salles")

public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Block")
    private String block;

    @Column(name = "Capacity")
    private int capacity;

    public Salle() {
    }

    public Salle(Long id, String name, String block,int capacity) {
        this.id = id;
        this.name = name;
        this.block = block;
        this.capacity=capacity;
    }
}
