package com.gic23.coffee_pos.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
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
public class drink_food_size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer sizeId;
    private Integer productId;
    private Double price;

    @JsonIgnoreProperties("products")
    @ManyToOne
    @JoinColumn(name = "sizeId", referencedColumnName = "id", insertable = false, updatable = false)
    private size size;

    @JsonIgnoreProperties("sizes")
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false)
    private drink_food drinkFood;

    @JsonIgnoreProperties("size")
    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL)
    private List<invoice_detail> invoiceDetails;

    @CreationTimestamp
    private LocalDate created;

    @UpdateTimestamp
    private LocalDate updated;
}
