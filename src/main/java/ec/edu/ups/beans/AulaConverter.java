/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.AulaFacade;
import ec.edu.ups.ejb.EdificioFacade;
import ec.edu.ups.entidades.Aula;
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
@Named("aulaConverter")
public class AulaConverter implements Converter<Aula> {

    @Inject
    private AulaFacade aulafacade;
            
    @Override
    public Aula getAsObject(FacesContext context, UIComponent component, String id) {
        if (id == null) {
            return null;
        }
        Optional<Aula> aulaOptional = aulafacade.opcional(Integer.valueOf(id));
        if (aulaOptional.isPresent()) {
            return aulaOptional.get();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Aula aula) {
        if (aula == null) {
            return "0";
        }
        return aula.toString();
    }
}
