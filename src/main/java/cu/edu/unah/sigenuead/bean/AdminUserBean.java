package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Users;
import cu.edu.unah.sigenuead.service.FacultadServices;
import cu.edu.unah.sigenuead.service.UserServices;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
@Data
public class AdminUserBean implements Serializable {

    private String username;
    private String username1;
    private String password_pref;
    private String password;
    private String password1;

    private String id;
    private String firstname;
    private String lastname;
    private String lastname1;
    private String description;

    private String email;
    private List<String> Roles = new ArrayList<String>();
    private List<String> Roles1 = new ArrayList<String>();
    private String[] badges = {"badge-primary", "badge-info", "badge-success", "badge-danger", "badge-warning"};
    private List<String> list_area = new ArrayList<String>();
    private List<String> list_permisos = new ArrayList<String>();
    private boolean enabled = true;
    private List<Users> userList = new ArrayList<Users>();
    private Users selectedUser;
    final private UserServices serv = new UserServices();
    final private FacultadServices serv_facultad = new FacultadServices();

    public void init() {
        userList.clear();
        userList = serv.findAllUsers();
        Roles.clear();
        Roles1.clear();
        cleanVariables();
        updateListPermisos();
    }

    public void updateListPermisos() {
        list_area = new ArrayList<String>();
        list_area = serv_facultad.findAreasAvailablesForRoles();
    }

    public void cleanVariables() {
        username = "";
        password = "";
        password1 = "";
        firstname = "";
        lastname = "";
        lastname1 = "";
        description = "";
        id = "";
        email = "";
        enabled = true;
        Roles = new ArrayList<String>();
        list_permisos = new ArrayList<String>();
    }

    public void updateSelectedUsers(Users users) {
        this.setSelectedUser(users);
        Roles = serv.findUsersRoles(users.getUsername());
        list_permisos = serv.findUsersPermisos(users.getUsername());
    }

    public void updateSelectedUsersToDelete(Users users) {
        this.setSelectedUser(users);
        username1 = users.getUsername();
    }

    public void updateSelected_Users_toEdit(Users users) {
        selectedUser = users;
        username = users.getUsername();
        password_pref = users.getPassword();
        username1 = users.getUsername();
        firstname = users.getNombre();
        lastname = users.getApellido1();
        lastname1 = users.getApellido2();
        id = users.getIdentificacion();
        description = users.getDescripcion();
        email = users.getEmail();
        enabled = users.getEnabled();
        Roles = serv.findUsersRoles(users.getUsername());
        Roles1 = serv.findUsersRoles(users.getUsername());
        list_permisos = serv.findUsersPermisos(users.getUsername());
    }

    public void addUsers() {
        if (!password.equals(password1)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Las contraseñas deben ser coincidentes", ""));
            return;
        }
        Par submit = serv.addUsers(username, id, firstname, lastname, lastname1, email, password, enabled, description, Roles, list_permisos);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(((Par) submit).getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addUserDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
    }

    public void editUsers() {
        if (!password.equals(password1)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Las contraseñas deben ser coincidentes", ""));
            return;
        }
//        if (password.equals("")) {
//            pass = password_pref;
//        } else {
//            pass = password;
//        }
        Par submit = serv.editUsers(username, id, firstname, lastname, lastname1, email, password, enabled, description, Roles1, Roles, list_permisos);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editUserDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
    }

    public String translateRole(String role) {
        return switch (role) {
            case "ROLE_ADMIN" -> "Administrador";
            case "ROLE_SECRETARIA" -> "Secretaria";
            case "ROLE_SECRETARIO_GENERAL" -> "Secretario General";
            case "ROLE_CONTROLADOR" -> "Controlador de Plan de Estudio";
            default -> "Matriculador";
        };
    }

    public void deleteUsers() {
//        Par submit = serv.deleteUsers(username1);
//        FacesContext context = FacesContext.getCurrentInstance();
//        init();
//        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
    }

    public String translateEstado(boolean estado) {
        if (!estado) {
            return "Inactivo";
        }
        return "Activo";
    }

    public String translateBooleanToSeverity(boolean element) {
        if (element) {
            return "badge badge-success";
        }
        return "badge badge-danger";
    }

    public String translateBadge(String role) {
        return switch (role) {
            case "ROLE_ADMIN" -> badges[0];
            case "ROLE_SECRETARIA" -> badges[1];
            case "ROLE_SECRETARIO_GENERAL" -> badges[2];
            case "ROLE_CONTROLADOR" -> badges[3];
            case "ROLE_MATRICULADOR" -> badges[4];
            default -> "badge-dark";
        };
    }

}
