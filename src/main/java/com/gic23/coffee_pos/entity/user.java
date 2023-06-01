package com.gic23.coffee_pos.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String uid;

    private String firstname;
    private String lastname;
    private Date date_of_birth;

    @Column(unique = true)
    private String username;

    private String password;
    private Integer roleid;
    private Integer genderid;
    private String avatar;

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    @JsonIgnoreProperties("users")
    @ManyToOne
    @JoinColumn(name = "roleid", referencedColumnName = "id", insertable = false, updatable = false)
    private role roles;

    @JsonIgnoreProperties("users")
    @ManyToOne
    @JoinColumn(name = "genderid", referencedColumnName = "id", insertable = false, updatable = false)
    private gender gender;

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<user_history_recode> history;

    @JsonIgnoreProperties({ "cashier", "invoiceDetails" })
    @OneToMany(mappedBy = "cashier", cascade = CascadeType.ALL)
    private List<invoice> invoices;

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setPassword(String password) {
        this.password = password;
    }
}
