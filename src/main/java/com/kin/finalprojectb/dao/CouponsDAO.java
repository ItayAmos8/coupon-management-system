//Itay Amos 313348104
//Dotan Hazut 315779926
package com.kin.finalprojectb.dao;

import com.kin.finalprojectb.beans.Category;
import com.kin.finalprojectb.beans.Companies;
import com.kin.finalprojectb.beans.Coupons;
import com.kin.finalprojectb.beans.Customers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CouponsDAO extends JpaRepository<Coupons,Integer> {

    Coupons getCouponsById(int couponID);

    boolean existsByTitle(String title);

    boolean existsByCompaniesAndTitle(Companies companies,String title);
    ArrayList<Coupons> findCouponsByCustomers(Customers customers);


    ArrayList<Coupons> findCouponsByCustomersAndPriceBefore(Customers customers, double maxPrice);
    ArrayList<Coupons> findCouponsByCustomersAndCategory(Customers customers, Category category);
    ArrayList<Coupons> findCouponsByCompaniesAndPriceBefore(Companies companies, double maxPrice);
    ArrayList<Coupons> findCouponsByCompaniesAndCategory(Companies companies, Category category);
    void deleteById(int id);
    boolean existsByCompanies(Integer id);
    @Modifying
    @Transactional
    @Query("DELETE  from Coupons c where c.endDate < current_date()")//custom query to delete expired coupons
    void deleteCouponByEndDateBefore();


}
