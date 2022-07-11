/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.DiaFacade;
import ec.edu.ups.entidades.Dia;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author PAUL
 */
@Model
public class DiaControlador {
    @EJB
    private DiaFacade diaFacade;
    private Dia dia;
    private Long id;
    private String nombre;
    
    @Produces
    @Model
    public String tituloDia() {
        return "GESTIÓN DE DIAS";
    }
    
    @PostConstruct
    public void init() {
        this.dia = new Dia();
    }
    
    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiaFacade getDiaFacade() {
        return diaFacade;
    }

    public void setDiaFacade(DiaFacade diaFacade) {
        this.diaFacade = diaFacade;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Produces
    @RequestScoped
    @Named("listadoDias")
    public List<Dia> listarDias() {
        
        List<Dia> dias = diaFacade.listar();
        return dias;
    }
    
    
    public String guardar(){
        try {
            this.diaFacade.guardar(dia);
        } catch (Exception e) {
        }
        return "Dia.xhtml?faces-redirect=true";
    }
    
    public String eliminar(Long id){
        diaFacade.eliminar(id);
        return "Dia.xhtml?faces-redirect=true";
    }
    
    public String editar(Long id){
        this.id = id;
        
        if (id != null && id > 0) {
           diaFacade.opcional(id).ifPresent(p -> {
                this.dia = p;
            });
        }
        return "form.xhtml";
    }
}
