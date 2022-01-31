/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Set;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jeet
 */
@Named(value = "loginBean")
@RequestScoped
public class loginBean {

    @Inject
    private SecurityContext ctx;

    private String email;
    private String password;
    private String message;

    public loginBean() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String login() {
        try {
            Credential credential = new UsernamePasswordCredential(email, new Password(password));

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            AuthenticationStatus status = ctx.authenticate(request, response, withParams().credential(credential));

            if (status.equals(SUCCESS)) {

                System.out.println("In Success" + ctx.getCallerPrincipal().getName());

                if (ctx.isCallerInRole("admin")) {
                    return "admin";
                } else if (ctx.isCallerInRole("author")) {
                    return "author";
                } else {
                    return "login";
                }

            } else {
                message = "username or password incorrect";
                return "login";
            }

        } catch (Exception e) {
            message = "Username or Password are not correct";
            return "login";
        }
    }

    public String logout() throws ServletException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.logout();

        return "/login.jsf";
    }

}
