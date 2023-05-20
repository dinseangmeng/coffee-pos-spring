package com.gic23.coffee_pos.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class user_history_recode {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Integer userid;
    private Integer statusid;

    @CreationTimestamp
    private Date created;

    @JsonIgnoreProperties("history")
    @ManyToOne
    @JoinColumn(name = "userid",referencedColumnName = "id",insertable = false,updatable = false)
    private user user;


    @JsonIgnoreProperties("history")
    @ManyToOne
    @JoinColumn(name = "statusid",referencedColumnName = "id",insertable = false,updatable = false)
    private user_history_status status;


}
