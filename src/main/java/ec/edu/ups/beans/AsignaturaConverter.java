/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.AsignaturaFacade;
import ec.edu.ups.ejb.EdificioFacade;
import ec.edu.ups.entidades.Asignatura;
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
@Named("asignaturaConverter")
public class AsignaturaConverter implements Converter<Asignatura> {

    @Inject
    private AsignaturaFacade asignaturafacade;
            
    @Override
    public Asignatura getAsObject(FacesContext context, UIComponent component, String id) {
        if (id == null) {
            return null;
        }
        Optional<Asignatura> asignaturaOptional = asignaturafacade.opcional(Long.valueOf(id));
        if (asignaturaOptional.isPresent()) {
            return asignaturaOptional.get();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Asignatura asignatura) {
        if (asignatura == null) {
            return "0";
        }
        return asignatura.getId().toString();
    }
}