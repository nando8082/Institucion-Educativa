/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.EdificioFacade;
import ec.edu.ups.ejb.MatriculaFacade;
import ec.edu.ups.entidades.Matricula;
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
@Named("matriculaConverter")
public class MatriculaConverter implements Converter<Matricula> {

    @Inject
    private MatriculaFacade matriculafacade;
            
    @Override
    public Matricula getAsObject(FacesContext context, UIComponent component, String id) {
        if (id == null) {
            return null;
        }
        Optional<Matricula> matriculaOptional = matriculafacade.opcional(Long.valueOf(id));
        if (matriculaOptional.isPresent()) {
            return matriculaOptional.get();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Matricula matricula) {
        if (matricula == null) {
            return "0";
        }
        return matricula.getId().toString();
    }
}
