package cu.edu.unah.sigenuead.util;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

@FacesConverter (value = "entityConverter")
public class EntityConverter implements Converter {

    private static Map<Object, String> entities = new WeakHashMap<Object, String>();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String uuid) {
        for (Map.Entry<Object, String> entry : entities.entrySet()) {
            if (entry.getValue().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object entity) {
        synchronized (entities) {
            if (!entities.containsKey(entity)) {
                String uuid = UUID.randomUUID().toString();
                entities.put(entity, uuid);
                return uuid;
            } else {
                return entities.get(entity);
            }
        }
    }
}
