package cu.edu.unah.sigenuead.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@SessionScoped
@Named
public class TemplateBean implements Serializable {

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
}
