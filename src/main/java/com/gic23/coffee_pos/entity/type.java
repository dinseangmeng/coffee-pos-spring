package com.gic23.coffee_pos.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
public class type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    // @JsonIgnoreProperties("type")
    // @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    // private List<category> categorys;

    // @JsonIgnoreProperties("type")
    // @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    // private List<drink_food> drinks;

    // @JsonIgnoreProperties("type")
    // @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    // private List<topping> topping;

    @CreationTimestamp
    private LocalDate created;

    @UpdateTimestamp
    private LocalDate updated;
}
