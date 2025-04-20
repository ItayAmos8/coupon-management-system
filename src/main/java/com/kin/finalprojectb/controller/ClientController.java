//Itay Amos 313348104
//Dotan Hazut 315779926
package com.kin.finalprojectb.controller;

import com.kin.finalprojectb.login.LoginManager;
import com.kin.finalprojectb.services.ClientServices;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class ClientController {

    boolean isLogin = false;

    String askForLogin = "You need to login!!";

    public static ClientServices testLogin(String email, String password, LoginManager.ClientType clientType) {
        LoginManager loginM = LoginManager.getInstance();
        return loginM.loginInManager(email, password, clientType);
    }

}

