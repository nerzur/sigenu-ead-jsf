/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.AuthoritiesJpaController;
import cu.edu.unah.sigenuead.controller.CumJpaController;
import cu.edu.unah.sigenuead.controller.FacultadJpaController;
import cu.edu.unah.sigenuead.controller.UsersJpaController;
import cu.edu.unah.sigenuead.entity.Authorities;
import cu.edu.unah.sigenuead.entity.AuthoritiesPK;
import cu.edu.unah.sigenuead.entity.Cum;
import cu.edu.unah.sigenuead.entity.Facultad;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import cu.edu.unah.sigenuead.util.texts;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author claudy
 */
public class UserServices {

    private UsersJpaController controllerUsers;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UsersJpaController getInstanceOfUsers() {
        return (controllerUsers == null) ? controllerUsers = new UsersJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerUsers;
    }

    private AuthoritiesJpaController controllerAuthorities;

    public AuthoritiesJpaController getInstanceOfAuthorities() {
        return (controllerAuthorities == null) ? controllerAuthorities = new AuthoritiesJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerAuthorities;
    }
    private CumJpaController controllerCum;

    public CumJpaController getInstanceOfCum() {
        return (controllerCum == null) ? controllerCum = new CumJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCum;
    }
    private FacultadJpaController controllerFacultad;

    public FacultadJpaController getInstanceOfFacultad() {
        return (controllerFacultad == null) ? controllerFacultad = new FacultadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultad;
    }

    public Par addUsers(String usuario, String identificacion, String nombre, String apellido1, String apellido2, String email, String password, boolean enabled, String descripcion, List<String> authorities, List<String> areas) {
        try {
            if (getInstanceOfUsers().findUsers(usuario) == null) {
                if (getInstanceOfUsers().findUsersByIdentificacion(identificacion) != null) {
                    return new Par(2, texts.getUserIdentificaion());
                }
                String passwBcrypt = passwordEncoder.encode(password); //TODO FIX
                Users u = new Users(usuario, identificacion, nombre, apellido1, apellido2, email, passwBcrypt, enabled, descripcion);
                getInstanceOfUsers().create(u);
                for (int i = 0; i < authorities.size(); i++) {
                    Authorities a = new Authorities(usuario, authorities.get(i));
                    a.setUsers(u);

                    ArrayList<Cum> cl = new ArrayList();
                    ArrayList<Facultad> fl = new ArrayList();
                    for (int j = 0; j < areas.size(); j++) {
                        Cum c = getInstanceOfCum().findCumByNombreAvailable(areas.get(j));
                        if (c == null) {
                            Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(areas.get(j));
                            if (f == null) {
                                continue;
                            } else {
                                fl.add(f);
                            }
                        } else {
                            cl.add(c);
                        }
                    }
                    a.setCumList(cl);
                    a.setFacultadList(fl);
                    getInstanceOfAuthorities().create(a);
                }
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editUsers(String usuario, String identificacion, String nombre, String apellido1, String apellido2, String email, String password, boolean enabled, String descripcion, List<String> authorities, List<String> authoritiesNew, List<String> areas) {
        try {
            Users p = getInstanceOfUsers().findUsers(usuario);
            if (p == null) {
                return new Par(2, texts.getUsuarioNull());
            }
            p.setApellido1(apellido1);
            p.setApellido2(apellido2);
            p.setDescripcion(descripcion);
            p.setEmail(email);
            p.setEnabled(enabled);
            p.setNombre(nombre);
            if (!password.equals("")) {
                p.setPassword(passwordEncoder.encode(password));
            }
            getInstanceOfUsers().edit(p);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!auth.getName().equals(usuario)) {
                for (int i = 0; i < authorities.size(); i++) {
                    getInstanceOfAuthorities().destroy(new AuthoritiesPK(usuario, authorities.get(i)));
                }
                for (int i = 0; i < authoritiesNew.size(); i++) {
                    Authorities a = new Authorities(usuario, authoritiesNew.get(i));
                    a.setUsers(p);
                    ArrayList<Cum> cl = new ArrayList();
                    ArrayList<Facultad> fl = new ArrayList();
                    for (int j = 0; j < areas.size(); j++) {
                        Cum c = getInstanceOfCum().findCumByNombreAvailable(areas.get(j));
                        if (c == null) {
                            Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(areas.get(j));
                            if (f == null) {
                                continue;
                            } else {
                                fl.add(f);
                            }
                        } else {
                            cl.add(c);
                        }
                    }
                    a.setCumList(cl);
                    a.setFacultadList(fl);
                    getInstanceOfAuthorities().create(a);
                }
                return new Par(1, texts.getSatisfactorio());
            }
            else{
                return new Par(3, "Los datos del usuario han sido modificados pero no se han podido modificar sus Roles y Permisos ya que se encuentra registrado en el sistema en estos momentos");
            }

        } catch (Exception ex) {
            Logger.getLogger(UserServices.class
                    .getName()).log(Level.SEVERE, null, ex);
            return new Par(
                    2, texts.getError());
        }
    }

    public Par changePassword(String usuario, String password, String passwordNew) {
        try {
            Users p = getInstanceOfUsers().findUsers(usuario);
            if (BCrypt.checkpw(password, p.getPassword())) {
                p.setPassword(passwordEncoder.encode(passwordNew));
                getInstanceOfUsers().edit(p);
                return new Par(1, texts.getSatisfactorio());
            }
            return new Par(3, "La contraseña actual no es correcta");

        } catch (Exception ex) {
            Logger.getLogger(UserServices.class
                    .getName()).log(Level.SEVERE, null, ex);
            return new Par(
                    2, texts.getError());
        }
    }

//    public Par cancelarUsers(String usuario) {
//        try {
//            Users p = getInstanceOfUsers().findUsers(usuario);
//            p.setEnabled(true);
//            getInstanceOfUsers().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteUsers(String nombre) {
//        try {
//            Users p = getInstanceOfUsers().findUsers(nombre);
//            List<String> authorities = getInstanceOfAuthorities().findUserRoles(nombre);
//            for (int i = 0; i < authorities.size(); i++) {
//                getInstanceOfAuthorities().destroy(new AuthoritiesPK(p.getUsername(), authorities.get(i)));
//            }
//            getInstanceOfUsers().destroy(p.getUsername());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
    public List<Users> findAllUsers() {
        return getInstanceOfUsers().findUsersEntities();
    }

    public Users findUsersByUsername(String user) {
        return getInstanceOfUsers().findUsersByUsernameAvailable(user);
    }

    public List<String> findUsersRoles(String user) {
        return getInstanceOfAuthorities().findUserRoles(user);
    }

    public List<String> findUsersPermisos(String user) {
        List<String> res = new ArrayList<String>();
        res.addAll(getInstanceOfAuthorities().findUserPermisosCum(user));
        res.addAll(getInstanceOfAuthorities().findUserPermisosFac(user));
        return res;
    }

//    public List<String> findFacultadAutorities(String user) {
//        return getInstanceOfUsers().findFacultadAutorities(user);
//    }
}
