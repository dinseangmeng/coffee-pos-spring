package com.gic23.coffee_pos.entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class user_history_status {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String status;

    @JsonIgnoreProperties("status")
    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<user_history_recode> history;

}
