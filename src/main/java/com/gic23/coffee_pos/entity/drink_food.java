package com.gic23.coffee_pos.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class drink_food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String code;

    private String name;
    private String noted;
    private Integer categoryId;
    private Integer typeId;

    @JsonIgnoreProperties({ "drinks", "categorys", "topping" })
    @ManyToOne
    @JoinColumn(name = "typeId", referencedColumnName = "id", insertable = false, updatable = false)
    private type type;

    @JsonIgnoreProperties("drinkFood")
    @OneToMany(mappedBy = "drinkFood", cascade = CascadeType.ALL)
    private List<drink_food_size> sizes;

    @JsonIgnoreProperties("drinkFood")
    @OneToMany(mappedBy = "drinkFood", cascade = CascadeType.ALL)
    private List<drink_food_topping> topping;

    @JsonIgnoreProperties("product")
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<invoice_detail> orders;

    @CreationTimestamp
    private LocalDate created;

    @UpdateTimestamp
    private LocalDate updated;
}
