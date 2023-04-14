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
public class Companies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String name;

    public String email;

    public String password;

    //coupon list for each company, one-to-many relationship

    @OneToMany(mappedBy = "companies", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Coupons> coupons = new ArrayList<>();

    public String getName() {
        return this.name;
    }

}
