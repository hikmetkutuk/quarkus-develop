package com.develop.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Laptop extends PanacheEntity {
    String name;
    String brand;
    String processor;
    int ram;
    int storage;
}
