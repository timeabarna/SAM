package com.reaktorlabs.samview.util;

import com.reaktorlabs.sam.repository.model.samuser.SamUser;
import com.reaktorlabs.sam.repository.repo.samuser.SamUserRepository;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "assetOwnerConverter")
@ApplicationScoped
public class AssetOwnerConverter implements Converter, Serializable {
    private SamUserRepository userRepository;

    @Inject
    public AssetOwnerConverter(SamUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        SamUser user;
        if(("").equals(value)) {
            return null;
        } else if (value.contains("@")) {
            user = userRepository.findUserByEmail(value);
        } else {
            user = userRepository.findUserByName(value);
        }
        return user;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null) {
            return "";
        }
        return ((SamUser) value).getEmail();
    }
    
}
