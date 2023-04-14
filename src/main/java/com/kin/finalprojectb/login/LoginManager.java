package com.kin.finalprojectb.login;

import com.kin.finalprojectb.services.AdminServices;
import com.kin.finalprojectb.services.ClientServices;
import com.kin.finalprojectb.services.CompaniesServices;
import com.kin.finalprojectb.services.CustomerServices;

public class LoginManager {

    private static LoginManager instance;


    //make it a singleton class
    private LoginManager() {
        System.out.println("instance create correct");
    }

    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }

        return instance;
    }

    public ClientServices loginInManager(String email, String password, ClientType clientType) {
        switch (clientType) {
            case Company -> {
                CompaniesServices companiesServices = new CompaniesServices();
                if (companiesServices.login(email, password)) {
                    return companiesServices;
                }
            }
            case Administrator -> {
                AdminServices adminServices = new AdminServices();
                if (adminServices.login(email, password)) {
                    return adminServices;
                }
            }
            case Customer -> {
                CustomerServices customerServices = new CustomerServices();
                if (customerServices.login(email, password)) {
                    return customerServices;
                }
            }
        }

        return null;
    }

    public enum ClientType {
        Administrator,
        Company,
        Customer;

        private ClientType() {
        }
    }
}




