package com.gic23.coffee_pos.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String uid;

    private String firstname;
    private String lastname;
    private Date date_of_birth;
    private String username;
    private String password;
    private Integer roleid;
    private Integer genderid;

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

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setPassword(String password) {
        this.password = password;
    }
}
