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
import jakarta.persistence.OneToOne;
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
public class invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String invoiceCode;

    private Double totalPrice;
    private Double discount;
    private Double exchangeRate;
    private Integer cashierId;
    private Integer tableId;

    @JsonIgnoreProperties("invoices")
    @ManyToOne
    @JoinColumn(name = "cashierId", referencedColumnName = "id", insertable = false, updatable = false)
    private user cashier;

    @JsonIgnoreProperties("invoices")
    @ManyToOne
    @JoinColumn(name = "tableId", referencedColumnName = "id", insertable = false, updatable = false)
    private tables tables;

    @JsonIgnoreProperties("invoice")
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<invoice_detail> invoiceDetails;

    @JsonIgnoreProperties("invoice")
    @OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL)
    private reciept receipt;

    @CreationTimestamp
    private LocalDate created;

    @UpdateTimestamp
    private LocalDate updated;
}
