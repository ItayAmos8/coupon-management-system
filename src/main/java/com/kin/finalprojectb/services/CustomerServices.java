//Itay Amos 313348104
//Dotan Hazut 315779926
package com.kin.finalprojectb.services;

import com.kin.finalprojectb.beans.Category;
import com.kin.finalprojectb.beans.Coupons;
import com.kin.finalprojectb.beans.Customers;
import com.kin.finalprojectb.dao.CompaniesDAO;
import com.kin.finalprojectb.dao.CouponsDAO;
import com.kin.finalprojectb.dao.CustomerDAO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class CustomerServices extends ClientServices {

    @Autowired
    CouponsDAO couponsDAO;

    @Autowired
    CompaniesDAO companiesDAO;
    @Autowired
    CustomerDAO customerDAO;

    public int customerID;

    @Override
    public boolean login(String email, String password) {
        Optional<Customers> tempCustomer = customerDAO.findCustomersByEmailAndPassword(email, password);
        tempCustomer.ifPresent(customers -> this.customerID = customers.getId());
        return customerDAO.existsByEmailAndPassword(email, password);
    }

    public String purchaseCoupon(int couponID) {
        Coupons coupon = couponsDAO.getCouponsById(couponID);
        Customers customers = customerDAO.getReferenceById(customerID);
        try{
            if (!customers.getCoupons().contains(coupon) && coupon.amount > 0 && coupon.getEndDate().after(new Date())) {//check if the coupons is exists and if the amount is higher than 0
                coupon.setAmount(coupon.amount - 1);//decrease the coupon amount by 1
                coupon.getCustomers().add(customers);//add the customer to the coupon list
                customers.getCoupons().add(coupon);//add the coupon to the customer list
                couponsDAO.save(coupon);
                customerDAO.save(customers);
                return "Coupon Purchased complete!";

            }
        }catch (NullPointerException | EntityNotFoundException e){
            return "you can't buy this coupon";
        }
        return "You can't buy this Coupon";
    }

    public ArrayList<Coupons> getCustomerCoupons() {
        ArrayList<Coupons> couponsByCustomerID = couponsDAO.findCouponsByCustomers(customerDAO.getCustomersById(customerID));
        if (!couponsByCustomerID.isEmpty()) {
            return couponsByCustomerID;
        }
        return new ArrayList<>();
    }

    public ArrayList<Coupons> getCustomerCouponsCategory(Category category) {
        //a list that contain all the coupons from the given category
        ArrayList<Coupons> couponsByCustomerID = couponsDAO.findCouponsByCustomersAndCategory(customerDAO.getCustomersById(customerID),category);
        if (!couponsByCustomerID.isEmpty()) {
            return couponsByCustomerID;
        }
        return new ArrayList<>();
    }

    public ArrayList<Coupons> getCustomerCouponsMaxPrice(double maxPrice) {
        // a list that contain all the coupons below the given max price
        ArrayList<Coupons> couponsByCustomerID = couponsDAO.findCouponsByCustomersAndPriceBefore
                (customerDAO.getCustomersById(customerID),maxPrice);
        if (!couponsByCustomerID.isEmpty()) {
            return couponsByCustomerID;
        }
        return new ArrayList<>();
    }
    public Customers getCustomerDetails() {
        return customerDAO.getCustomersById(customerID);
    }
}
