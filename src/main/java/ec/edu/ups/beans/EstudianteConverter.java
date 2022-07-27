/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.EdificioFacade;
import ec.edu.ups.ejb.EstudianteFacade;
import ec.edu.ups.entidades.Estudiante;
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
@Named("estudianteConverter")
public class EstudianteConverter implements Converter<Estudiante> {

    @Inject
    private EstudianteFacade estudiantefacade;
            
    @Override
    public Estudiante getAsObject(FacesContext context, UIComponent component, String id) {
        if (id == null) {
            return null;
        }
        Optional<Estudiante> estudianteOptional = estudiantefacade.opcional(Integer.valueOf(id));
        if (estudianteOptional.isPresent()) {
            return estudianteOptional.get();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Estudiante estudiante) {
        if (estudiante == null) {
            return "0";
        }
        return estudiante.toString();
    }
}
