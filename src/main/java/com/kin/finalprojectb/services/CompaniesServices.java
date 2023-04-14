//Itay Amos 313348104
//Dotan Hazut 315779926
package com.kin.finalprojectb.services;

import com.kin.finalprojectb.beans.Category;
import com.kin.finalprojectb.beans.Companies;
import com.kin.finalprojectb.beans.Coupons;
import com.kin.finalprojectb.beans.Customers;
import com.kin.finalprojectb.dao.CompaniesDAO;
import com.kin.finalprojectb.dao.CouponsDAO;
import com.kin.finalprojectb.dao.CustomerDAO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompaniesServices extends ClientServices {
    @Autowired
    CouponsDAO couponsDAO;
    @Autowired
    CompaniesDAO companiesDAO;
    @Autowired
    CustomerDAO customerDAO;

    int companyID;

    @Override
    public boolean login(String email, String password) {
        List<Companies> companies = this.companiesDAO.findAll();

        for (Companies company : companies) {
            if (company.getEmail().equals(email) && company.getPassword().equals(password)) {
                this.companyID = company.getId();
            }
        }
        return this.companiesDAO.existsByEmailAndPassword(email, password);
    }

    public String deleteCoupon(int couponId) {
        Companies company = companiesDAO.getCompaniesById(companyID);
        Coupons OneCoupon = couponsDAO.getCouponsById(couponId);
        company.coupons.remove(OneCoupon);//remove the coupon from the company
        for (Customers customers : OneCoupon.getCustomers()) {//a loop to remove the coupons from all the customers
            customers.getCoupons().remove(OneCoupon);
        }
        OneCoupon.getCustomers().clear();
        if (companiesDAO.getCompaniesByCouponsAndId(OneCoupon, companyID).isPresent()) {
            couponsDAO.deleteById(couponId);//delete the coupon from the company
            return "success";
        }
        return "not found";
    }

    public boolean existsByTitleAndCompanyID(String title) {
        try {
            return couponsDAO.existsByCompaniesAndTitle(companiesDAO.getCompaniesById(companyID),title);
        } catch (Exception e) {
            System.out.println("The title is already exists");
        }
        return true;
    }

    public String createCoupon(Coupons coupons) {
        System.out.println(companyID);
        if (!existsByTitleAndCompanyID(coupons.getTitle())) {//check if the coupon is already exists
            coupons.setCompanies(companiesDAO.getReferenceById(companyID));
            coupons.customers = new ArrayList<>();
            couponsDAO.save(coupons);
            return "The coupon created!";
        }
        return "The coupon is not created";
    }

    public String updateCoupon(Coupons coupon) {
        Optional<Coupons> tempCoupon = couponsDAO.findById(coupon.getId());
        if (tempCoupon.isPresent()) {//check if the coupon exists
            int companyID = tempCoupon.get().getCompanies().getId();
            if (coupon.getCompanies().getId() == companyID) {//check to valid that the id hasn't been changed
                couponsDAO.save(coupon);
                return "The coupon with ID: " + coupon.getId() + " updated";
            }
        }
        return "The Coupon is not updated";
    }

    //RETURN ALL COMPANY COUPONS
    public List<Coupons> getAllCoupons() {
        try {
            Companies tempCompany = companiesDAO.getReferenceById(companyID);
            return tempCompany.getCoupons();
        } catch (EntityNotFoundException e) {
            System.out.println("There is no coupons for this company");
        }
        return new ArrayList<>();
    }

    public ArrayList<Coupons> getCompanyCouponsMaxPrice(double maxPrice) {
        // a list that contain all the coupons below the given max price
        ArrayList<Coupons> couponsByCompaniesAndPriceBefore = couponsDAO.findCouponsByCompaniesAndPriceBefore(companiesDAO.getCompaniesById(companyID), maxPrice);
        if (!couponsByCompaniesAndPriceBefore.isEmpty()) {
            return couponsByCompaniesAndPriceBefore;
        }
        return new ArrayList<>();
    }

    public ArrayList<Coupons> getCompanyCouponsCategory(Category category) {
        //a list that contain all the coupons from the given category
        ArrayList<Coupons> couponsByCompaniesAndCategory = couponsDAO.findCouponsByCompaniesAndCategory(companiesDAO.getCompaniesById(companyID), category);
        if (!couponsByCompaniesAndCategory.isEmpty()) {
            System.out.println("123");
            return couponsByCompaniesAndCategory;
        }
        return new ArrayList<>();
    }

    public Companies getCompanyDetails(int companyID){
        return companiesDAO.findById(companyID).get();
    }

}
