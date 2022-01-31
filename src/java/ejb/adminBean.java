/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Groupmaster;
import java.util.Collection;
import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jeet
 */
@Named(value = "adminBean")
@DeclareRoles({"admin", "author"})
@Stateless
public class adminBean {

    @PersistenceContext(unitName = "BookTestPU")
    EntityManager em;

    public Collection<Groupmaster> getAllGroup() {

        return em.createNamedQuery("Groupmaster.findAll").getResultList();

    }

}
