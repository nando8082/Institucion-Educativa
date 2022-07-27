/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.EdificioFacade;
import ec.edu.ups.ejb.GrupoFacade;
import ec.edu.ups.entidades.Grupo;
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
@Named("grupoConverter")
public class GrupoConverter implements Converter<Grupo> {

    @Inject
    private GrupoFacade grupofacade;
            
    @Override
    public Grupo getAsObject(FacesContext context, UIComponent component, String id) {
        if (id == null) {
            return null;
        }
        Optional<Grupo> grupoOptional = grupofacade.opcional(Long.valueOf(id));
        if (grupoOptional.isPresent()) {
            return grupoOptional.get();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Grupo grupo) {
        if (grupo == null) {
            return "0";
        }
        return grupo.getId().toString();
    }
}
