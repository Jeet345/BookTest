/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.jsf"
        )
)

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/book",
        callerQuery = "select Password from user where Email = ?",
        groupsQuery = "select GroupName from groupmaster where Email = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priority = 30
)

@Named
@ApplicationScoped
public class ProjectConfig {

}
