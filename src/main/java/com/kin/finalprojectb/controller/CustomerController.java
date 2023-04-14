//Itay Amos 313348104
//Dotan Hazut 315779926
package com.kin.finalprojectb.controller;

import  com.kin.finalprojectb.beans.Category;
import com.kin.finalprojectb.login.LoginBody;
import com.kin.finalprojectb.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/customer")
public class CustomerController extends ClientController {
    @Autowired
    CustomerServices customerServices;

    @PostMapping("/login")
    public boolean login(@RequestBody LoginBody loginBody){
        isLogin = customerServices.login(loginBody.email, loginBody.password);
        return isLogin;
    }

    @PostMapping("/purchaseCoupon/{couponID}")
    public String purchaseCoupon(@PathVariable int couponID){
        if(isLogin)return customerServices.purchaseCoupon(couponID);
        return askForLogin;
    }

    @GetMapping("/getCustomerCoupons")
    public Object getCustomerCoupons() {
        if(isLogin)return customerServices.getCustomerCoupons();
        return askForLogin;
    }

    @GetMapping("/getCustomerCouponsCategory/{category}")
    public Object getCustomerCouponsCategory(@PathVariable Category category) {
        if(isLogin)return customerServices.getCustomerCouponsCategory(category);
        return askForLogin;
    }

    @GetMapping("/getCustomerCouponsMaxPrice")
    public Object getCustomerCouponsMaxPrice(double maxPrice) {
        if(isLogin)return customerServices.getCustomerCouponsMaxPrice(maxPrice);
        return askForLogin;
    }

    @GetMapping("/getCustomerDetails")
    public Object getCustomerDetails(){
        if(isLogin)return customerServices.getCustomerDetails();
        return askForLogin;
    }
}
