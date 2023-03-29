/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.util;

import cu.edu.unah.sigenuead.bean.TemplateBean;
import cu.edu.unah.sigenuead.controller.AuthoritiesJpaController;
import cu.edu.unah.sigenuead.controller.FacultadJpaController;
import cu.edu.unah.sigenuead.entity.Authorities;
import cu.edu.unah.sigenuead.entity.Users;
import java.util.List;
import jakarta.persistence.Persistence;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import cu.edu.unah.sigenuead.service.UserServices;

/**
 *
 * @author claudy
 */
public class userLoging {

    private String username;
    public Users user;
    public UserServices us=new UserServices();

    public userLoging() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        this.username = auth.getName();
        this.user=us.findUsersByUsername(username);
    }

    private AuthoritiesJpaController controllerAuthorities;

    public AuthoritiesJpaController getInstanceOfAuthorities() {
        return (controllerAuthorities == null) ? controllerAuthorities = new AuthoritiesJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerAuthorities;
    }

    private FacultadJpaController controllerFacultad;

    public FacultadJpaController getInstanceOfFacultad() {
        return (controllerFacultad == null) ? controllerFacultad = new FacultadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultad;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
