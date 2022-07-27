/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.DocenteFacade;
import ec.edu.ups.ejb.EdificioFacade;
import ec.edu.ups.entidades.Docente;
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
@Named("docenteConverter")
public class DocenteConverter implements Converter<Docente> {

    @Inject
    private DocenteFacade docentefacade;
            
    @Override
    public Docente getAsObject(FacesContext context, UIComponent component, String id) {
        if (id == null) {
            return null;
        }
        Optional<Docente> docenteOptional = docentefacade.opcional(Integer.valueOf(id));
        if (docenteOptional.isPresent()) {
            return docenteOptional.get();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Docente docente) {
        if (docente == null) {
            return "0";
        }
        return docente.toString();
    }
}
