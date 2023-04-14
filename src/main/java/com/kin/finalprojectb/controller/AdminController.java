//Itay Amos 313348104
//Dotan Hazut 315779926
package com.kin.finalprojectb.controller;

import com.kin.finalprojectb.beans.Companies;
import com.kin.finalprojectb.beans.Customers;
import com.kin.finalprojectb.login.LoginBody;
import com.kin.finalprojectb.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController extends ClientController {
    @Autowired
    AdminServices adminServices;

    @PostMapping("/login")
    public boolean login(@RequestBody LoginBody loginBody) {
        this.isLogin =  adminServices.login(loginBody.getEmail(), loginBody.getPassword());
        return isLogin;
    }


    @PostMapping("/addCompany")
    public String addCompany(@RequestBody Companies company) {
        if(isLogin) return adminServices.createCompany(company);
        return askForLogin;
    }

    @PutMapping("/updateCompany")
    public String updateCompany(@RequestBody Companies company) {
        if(isLogin)return adminServices.updateCompany(company);
        return askForLogin;
    }

    @DeleteMapping("/deleteCompany/{companyID}")
    public String deleteCompany(@PathVariable int companyID) {
        if(isLogin)return adminServices.deleteCompany(companyID);
        return askForLogin;
    }

    @GetMapping("/getAllCompanies")
    public Object getAllCompanies()
    {
        if(isLogin)return adminServices.getAllCompanies();
        return askForLogin;
    }


    @GetMapping("/getOneCompany/{companyID}")
    public Object getOneCompany(@PathVariable int companyID) {
        if(isLogin)return adminServices.getOneCompany(companyID);
        return askForLogin;
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@RequestBody Customers customer) {
        if(isLogin)return adminServices.createCustomer(customer);
        return askForLogin;
    }

    @PutMapping("/updateCustomer")
    public String updateCustomer(@RequestBody Customers customer) {
        if(isLogin)return adminServices.updateCustomer(customer);
        return askForLogin;
    }

    @PostMapping("/deleteCustomer/{customerID}")
    public String deleteCustomer(@PathVariable int customerID) {
        if(isLogin)return adminServices.deleteCustomer(customerID);
        return askForLogin;
    }

    @GetMapping("/getAllCustomers")
    public Object getAllCustomers() {
        if(isLogin)return adminServices.getAllCustomers();
        return askForLogin;
    }

}
