package com.reaktorlabs.sam;

import com.reaktorlabs.repository.model.SamUser;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("assetOwnerConverter")
public class AssetOwnerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        SamUser user = new SamUser();
        user.setEmail(value);
        return user;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((SamUser) value).getEmail();
    }
    
}
