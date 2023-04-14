//Itay Amos 313348104
//Dotan Hazut 315779926
package com.kin.finalprojectb.dao;

import com.kin.finalprojectb.beans.Companies;
import com.kin.finalprojectb.beans.Coupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompaniesDAO extends JpaRepository<Companies, Integer> {

    boolean existsByEmailAndPassword(String email,String password);
    boolean existsByEmailOrName(String email, String name);
    Optional<Companies> getCompaniesByCouponsAndId(Coupons coupons,int id);
    void deleteById(int id);
    Companies getCompaniesById(int id);
}
