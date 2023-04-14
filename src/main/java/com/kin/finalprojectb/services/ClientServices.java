package com.kin.finalprojectb.services;

import com.kin.finalprojectb.dao.CompaniesDAO;
import com.kin.finalprojectb.dao.CouponsDAO;
import com.kin.finalprojectb.dao.CustomerDAO;

public abstract class ClientServices {

    CompaniesDAO companiesDAO;
    CustomerDAO customerDAO;
    CouponsDAO couponsDAO;


    abstract boolean login(String email, String password);

}
