/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import ejb.adminBean;
import entity.Groupmaster;
import entity.User;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.transaction.UserTransaction;

/**
 *
 * @author Jeet
 */
@Named(value = "registerBean")
@RequestScoped
public class registerBean {

    @Resource
    private UserTransaction utx;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    private String name;
    private String email;
    private String password;
    private String message;

    @PersistenceContext
    private EntityManager em;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.password = passwordHash.generate(password.toCharArray());
    }

    public String register() {

        try {

            User user = new User();
            Groupmaster group = em.find(Groupmaster.class, 1);

            user.setName(name);
            user.setGroupId(group);
            user.setEmail(email);
            user.setPassword(password);

            utx.begin();

            em.persist(user);

            utx.commit();

            message = "Registered Success";
        } catch (Exception e) {
            message = "Error :- " + e;

        }
        return message;

    }

}
