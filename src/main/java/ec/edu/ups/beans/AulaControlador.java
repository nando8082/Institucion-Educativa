/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.AulaFacade;
import ec.edu.ups.entidades.Aula;
import ec.edu.ups.entidades.Edificio;
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
public class AulaControlador {
    @EJB
    private AulaFacade aulaFacade;
    private Aula aula;
    private int id;
    private Edificio edificio;
    private String nombre;
    
    @Produces
    @Model
    public String tituloAula() {
        return "GESTIÓN DE AULAS";
    }
    
    @PostConstruct
    public void init() {
        this.aula = new Aula();
        this.edificio = new Edificio();
    }
    
    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public AulaFacade getAulaFacade() {
        return aulaFacade;
    }

    public void setAulaFacade(AulaFacade aulaFacade) {
        this.aulaFacade = aulaFacade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    @Produces
    @RequestScoped
    @Named("listadoAulas")
    public List<Aula> listarAulas() {
        
        List<Aula> aula = aulaFacade.listar();
        return aula;
    }
    
    
    public String guardar(){
        try {
            aula.setEdificio(edificio);
            this.aulaFacade.guardar(aula);
        } catch (Exception e) {
        }
        return "Aula.xhtml?faces-redirect=true";
    }
    
    public String eliminar(int id){
        aulaFacade.eliminar(id);
        return "Aula.xhtml?faces-redirect=true";
    }
    
    public String editar(int id){
        this.id = id;
        
        if (id != 0) {
            aulaFacade.opcional(id).ifPresent(p -> {
                this.aula = p;
            });
        }
        return "formu.xhtml";
    }
}
