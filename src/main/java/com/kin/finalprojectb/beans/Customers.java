//Itay Amos 313348104
//Dotan Hazut 315779926
package com.kin.finalprojectb.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String FirstName;
    public String LastName;
    public String email;
    public String password;

    //many customer to many coupons
    @ManyToMany(mappedBy = "customers")
    @JsonIgnore
    public List<Coupons> coupons = new ArrayList<>();
}

