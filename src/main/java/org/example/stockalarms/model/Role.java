package org.example.stockalarms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public enum Role {
    USER,ADMIN;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

}
