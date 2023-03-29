package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Users;
import cu.edu.unah.sigenuead.service.UserServices;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
@Data
public class TemplateBean implements Serializable {

    private String username;
    private List<String> roles = new ArrayList<String>();
    private Users user;
    private boolean role_admin, role_secretaria, role_controlador, role_secretario_general, role_matriculador;
    private String password, password1, password2;
    private final UserServices serv = new UserServices();

    public String translateMessage(String message) {
        if (message == null)
            return "";
        else if (message.equals("Bad credentials"))
            return "El usuario o la contraseña son incorrectos";
        else if (message.equals("User is disabled"))
            return "El usuario se encuentra deshabilitado";
        else
            return "Se ha producido un error al establecer conexión con el servidor de autenticación.";
    }

    public void init() {
        username = getCurrentUser();
        roles = serv.findUsersRoles(username);
        user = serv.findUsersByUsername(username);
        verifyRol();
//        if(!log){
//            logs4j.printUserLog("Autenticación Exitosa", UsernamePasswordAuthenticationFilter.class.toString());
//            log = true;
//        }
    }

    public String getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public void verifyRol() {
        List<String> list_roles = new ArrayList<>();
//        list_roles = serv_user.findUsersRoles(username);
        for (String l : list_roles) {
            switch (l) {
                case "ROLE_ADMIN" -> {
                    role_admin = true;
                    break;
                }
                case "ROLE_SECRETARIA" -> {
                    role_secretaria = true;
                    break;
                }
                case "ROLE_SECRETARIO_GENERAL" -> {
                    role_secretario_general = true;
                    break;
                }
                case "ROLE_CONTROLADOR" -> {
                    role_controlador = true;
                    break;
                }
                default -> {
                    role_matriculador = true;
                }
            }
        }
    }

    public boolean translateRole_Boolean(boolean... roles) {
        boolean rolesSum = false;
        for (boolean b : roles) {
            rolesSum |= b;
        }
        return rolesSum;
    }

    public String translateRole_visible(boolean... roles) {
        if (translateRole_Boolean(roles))
            return "";
        return "display: none;";
    }

    public String translateEstado(boolean estado) {
        if (!estado) {
            return "Inactivo";
        }
        return "Activo";
    }

    public String translateBooleanToSeveriy(boolean element) {
        if (element) {
            return "ui-button-success";
        }
        return "ui-button-danger";
    }

    public void passwordRenew() {
        if (!password1.equals(password2)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Las contraseñas deben ser coincidentes", ""));
            return;
        }
        Par submit = serv.changePassword(getCurrentUser(), password, password1);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        clearVariables();
    }

    public void clearVariables() {
        password = "";
        password1 = "";
        password2 = "";
    }
}
