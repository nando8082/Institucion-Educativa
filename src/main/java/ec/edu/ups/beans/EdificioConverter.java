package ec.edu.ups.beans;

import ec.edu.ups.ejb.EdificioFacade;
import ec.edu.ups.entidades.Edificio;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Optional;

/**
 *
 * @author PAUL
 */
@RequestScoped
@Named("edificioConverter")
public class EdificioConverter implements Converter<Edificio> {

    @Inject
    private EdificioFacade edificiofacade;
            
    @Override
    public Edificio getAsObject(FacesContext context, UIComponent component, String id) {
        if (id == null) {
            return null;
        }
        Optional<Edificio> edificioOptional = edificiofacade.opcional(Long.valueOf(id));
        if (edificioOptional.isPresent()) {
            return edificioOptional.get();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Edificio edificio) {
        if (edificio == null) {
            return "0";
        }
        return edificio.getId().toString();
    }
}
