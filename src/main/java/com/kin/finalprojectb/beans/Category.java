//Itay Amos 313348104
//Dotan Hazut 315779926
package com.kin.finalprojectb.beans;
import jakarta.persistence.Id;
public enum Category {
    food,
    clothes,
    vacation,
    travel;
    @Id
    public int id;
}
