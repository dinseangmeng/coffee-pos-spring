package com.gic23.coffee_pos.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class invoice_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer productId;
    private Double unitPrice;
    private Integer quantity;
    private Double discount;
    private Integer invoiceId;
    private Double surgarRate;
    private Integer toppingId;

    private Integer zoneId;
    private Integer sizeId;

    @JsonIgnoreProperties({ "orders", "sizes", "topping" })
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false)
    private drink_food product;

    @JsonIgnoreProperties("invoiceDetails")
    @ManyToOne
    @JoinColumn(name = "invoiceId", referencedColumnName = "id", insertable = false, updatable = false)
    private invoice invoice;

    @JsonIgnoreProperties({ "invoiceDetails", "drinkFood" })
    @ManyToOne
    @JoinColumn(name = "toppingId", referencedColumnName = "id", insertable = false, updatable = false)
    private drink_food_topping topping;

    @JsonIgnoreProperties({ "invoiceDetails", "drinkFood" })
    @ManyToOne
    @JoinColumn(name = "sizeId", referencedColumnName = "id", insertable = false, updatable = false)
    private drink_food_size size;

    @JsonIgnoreProperties("invoiceDetails")
    @ManyToOne
    @JoinColumn(name = "zoneId", referencedColumnName = "id", insertable = false, updatable = false)
    private zone zone;

    @CreationTimestamp
    private LocalDate created;

    @UpdateTimestamp
    private LocalDate updated;
}
