//Itay Amos 313348104
//Dotan Hazut 315779926
package com.kin.finalprojectb.login;
public class LoginBody {
    public String email;
    public String password;

    public LoginBody(String userName, String password) {
        this.email = userName;
        this.password = password;
    }
    //Getter and Setter
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginBody{"+"email: "+email+",password: "+password+"}";
    }
}
