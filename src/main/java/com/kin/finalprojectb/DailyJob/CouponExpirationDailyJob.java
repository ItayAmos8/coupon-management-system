//Itay Amos 313348104
//Dotan Hazut 315779926
package com.kin.finalprojectb.DailyJob;

import com.kin.finalprojectb.dao.CompaniesDAO;
import com.kin.finalprojectb.dao.CouponsDAO;
import com.kin.finalprojectb.dao.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service

public class CouponExpirationDailyJob {

    @Autowired
    CouponsDAO couponsDAO;
    @Autowired
    CompaniesDAO companiesDAO;
    @Autowired
    CustomerDAO customerDAO;


    //second minute hour day month year
    //  0 0/1 * 1/1 * ? //for checking only every second
    @Scheduled(cron = "0 0 0 1/1 * ?")//scheduling the coupon deletion from the DB that expired
    public void deleteExpiredCoupons() {
        couponsDAO.deleteCouponByEndDateBefore();
    }

}


