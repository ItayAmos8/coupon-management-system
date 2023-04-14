//Itay Amos 313348104
//Dotan Hazut 315779926
package com.kin.finalprojectb.dao;

import com.kin.finalprojectb.beans.Coupons;
import com.kin.finalprojectb.beans.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerDAO extends JpaRepository<Customers,Integer> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email,String password);

    void deleteById(int id);

    Customers getCustomersById(int id);

    Optional<Customers> findCustomersByEmailAndPassword(String email, String password);

    Optional<Customers> getCustomersByIdIsNotAndEmail(int id,String email);
}
