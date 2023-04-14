//Itay Amos 313348104
//Dotan Hazut 315779926
package com.kin.finalprojectb.controller;

import com.kin.finalprojectb.beans.Category;
import com.kin.finalprojectb.beans.Coupons;
import com.kin.finalprojectb.login.LoginBody;
import com.kin.finalprojectb.services.CompaniesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController extends ClientController {
    @Autowired
    CompaniesServices companiesServices;

    @PostMapping("/login")
    public boolean login(@RequestBody LoginBody loginBody) {
        isLogin = companiesServices.login(loginBody.email, loginBody.password);
        return isLogin;
    }

    @PostMapping("/addCoupon")
    public String addCoupon(@RequestBody Coupons coupon) {
        if (isLogin) return companiesServices.createCoupon(coupon);
        return askForLogin;
    }

    @PutMapping("/updateCoupon")
    public String updateCoupon(@RequestBody Coupons coupons) {
        if (isLogin) return companiesServices.updateCoupon(coupons);
        return askForLogin;
    }

    @DeleteMapping("/deleteCoupon/{id}")
    public String deleteCoupon(@PathVariable int id) {
        if(isLogin)return companiesServices.deleteCoupon(id);
        return askForLogin;
    }

    @GetMapping("/getAllCompanyCoupons")
    public Object getAllCompanyCoupons() {
        if(isLogin)return companiesServices.getAllCoupons();
        return askForLogin;
    }

    @PostMapping("/getAllCompanyCouponsByCategory/{category}")
    public Object getAllCompanyCoupons(@PathVariable Category category) {
        if(isLogin)return companiesServices.getCompanyCouponsCategory(category);
        return askForLogin;
    }

    @GetMapping("/getAllCompanyCouponsByMaxPrice/{maxPrice}")
    public Object getAllCompanyCoupons(@PathVariable  double maxPrice) {
        if(isLogin)return companiesServices.getCompanyCouponsMaxPrice(maxPrice);
        return askForLogin;
    }

    @GetMapping("/getCompanyDetails/{companyId}")
    public Object getCompanyDetails(@PathVariable int companyId ){
        if(isLogin) return companiesServices.getCompanyDetails(companyId);
        return askForLogin;
    }
}
