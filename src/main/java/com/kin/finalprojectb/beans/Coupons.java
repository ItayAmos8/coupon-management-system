package com.kin.finalprojectb.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Itay Amos 313348104
//Dotan Hazut 315779926
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Coupons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;


    @Column(name = "Category_ID")
    @Enumerated(EnumType.ORDINAL)
    public Category category;

    @Column(name = "title")
    public String title;

    @Column(name = "description")
    public String description;

    @Column(name = "start_date")
    public Date startDate;

    @Column(name = "end_date")
    public Date endDate;

    @Column(name = "amount")
    public int amount;

    @Column(name = "price")
    public double price;

    @Column(name = "image")
    public String image;


    //many coupons for each company , many-to-one relationship
    @ManyToOne
    @JoinColumn(name = "company_id",referencedColumnName = "id")
    @JsonBackReference
    public Companies companies ;


    //the relationship between customer and coupons, customer_vs_coupons table many to many
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "customers_vs_coupons",
            joinColumns =
                    { @JoinColumn(name = "coupon_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "customer_id", referencedColumnName = "id") })

    public List<Customers> customers = new ArrayList<>();

}
