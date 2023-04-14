//Itay Amos 313348104
//Dotan Hazut 315779926
package com.kin.finalprojectb.services;

import com.kin.finalprojectb.beans.Companies;
import com.kin.finalprojectb.beans.Coupons;
import com.kin.finalprojectb.beans.Customers;
import com.kin.finalprojectb.dao.CompaniesDAO;
import com.kin.finalprojectb.dao.CouponsDAO;
import com.kin.finalprojectb.dao.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class AdminServices extends ClientServices {
    @Autowired
    CouponsDAO couponsDAO;
    @Autowired
    CompaniesDAO companiesDAO;
    @Autowired
    CustomerDAO customerDAO;

    private final String email;
    private final String password;

    public AdminServices() {
        email = "admin@admin.com";
        password = "admin";
    }

    //LOGIN
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public String createCompany(Companies company) {
        if (!isExistByName(company.getEmail(), company.getName())) {
            companiesDAO.save(company);
            return "The company [" + company.getId() + "] is created";
        }
        return "the company exists by email or name";
    }


    public boolean isCustomerExistByEmail(String email) {
        return customerDAO.existsByEmail(email);
    }

    public boolean isExistByName(String email, String name) {
        return companiesDAO.existsByEmailOrName(email, name);
    }

    public String deleteCompany(int companyID) {
        try {
            Companies company = getOneCompany(companyID).get();
            ArrayList<Coupons> couponsArrayList = new ArrayList<>(company.getCoupons());
            for (Coupons coupon : couponsArrayList) {
                coupon.getCustomers().clear();//delete the customer coupons list by coupon
                couponsDAO.delete(coupon);//delete the coupon from the DB
            }
            couponsArrayList.clear();
            companiesDAO.deleteById(companyID);//delete the company from the DB
            return "The company [" + companyID + "] is deleted";
        } catch (NoSuchElementException | EmptyResultDataAccessException e) {
            System.out.println("The company with this ID: " + companyID + " is not exist");
        }
        return "The company is not exist";
    }

    public List<Companies> getAllCompanies() {
        return companiesDAO.findAll();
    }

    public Optional<Companies> getOneCompany(int id) {
        return companiesDAO.findById(id);
    }

    public String createCustomer(Customers customer) {
        if (!isCustomerExistByEmail(customer.getEmail())) {//check if the customer is already exists in the DB
            customerDAO.save(customer);
            return "The customer [" + customer.getId() + "] is created";
        }
        return "The customer exist by email";
    }

    public List<Customers> getAllCustomers() {
        return customerDAO.findAll();
    }

    public String updateCustomer(Customers customer) {
        if (customerDAO.existsById(customer.getId())) {
            if(customerDAO.getCustomersByIdIsNotAndEmail(customer.getId(),customer.getEmail()).isEmpty()) {
                //if the customer exists, update it
                customerDAO.save(customer);
                return "The Customer [" + customer.getId() + "] is updated";
            }
        }
        return "The update didn't succeed";

    }

    public String deleteCustomer(int id) {
        try {
            Customers customer = customerDAO.getCustomersById(id);
            List<Coupons> couponsList = customer.getCoupons();
            for(Coupons coupon : couponsList){
                coupon.getCustomers().remove(customer);//remove the connection between the customer and the coupon
            }
            couponsList.clear();
            customerDAO.deleteById(id);//delete the customer from the DB

            return "The customer [" + id + "] is deleted";
        } catch (NoSuchElementException | EmptyResultDataAccessException | NullPointerException e) {
            System.out.println("The customer with this ID: " + id + " is not exist");
        }
        return "The customer is not exist";
    }

    public String updateCompany(Companies company) {
        Optional<Companies> tempComp = companiesDAO.findById(company.getId());
        if (tempComp.isPresent()) {//check if the company exists
            Companies tComp = tempComp.get();
            if (tComp.getName().equals(company.getName())) {//a check to see that the name hasn't changed
                companiesDAO.save(company);
                return "The Company[" + company.getId() + "] is updated";
            }
        }
        return "The update didn't succeed";
    }
}