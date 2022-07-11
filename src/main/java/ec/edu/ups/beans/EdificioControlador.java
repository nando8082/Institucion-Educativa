/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.EdificioFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import ec.edu.ups.entidades.Edificio;
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
public class EdificioControlador {
    @EJB
    private EdificioFacade edificioFacade;
    private Edificio edificio;
    private Long id;
    private String nombre;
    
    @Produces
    @Model
    public String tituloEdificio() {
        return "GESTIÓN DE EDIFICIOS";
    }
    
    @PostConstruct
    public void init() {
        this.edificio = new Edificio();
    }
    
    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EdificioFacade getEdificioFacade() {
        return edificioFacade;
    }

    public void setEdificioFacade(EdificioFacade edificioFacade) {
        this.edificioFacade = edificioFacade;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Produces
    @RequestScoped
    @Named("listadoEdificios")
    public List<Edificio> listarEdificios() {
        List<Edificio> ed = edificioFacade.listar();
        return ed;
    }
    
    
    public String guardar(){
        try {
            this.edificioFacade.guardar(edificio);
        } catch (Exception e) {
        }
        return "Edificio.xhtml?faces-redirect=true";
    }
    
    public String eliminar(Long id){
        edificioFacade.eliminar(id);
        return "Edificio.xhtml?faces-redirect=true";
    }
    
    public String editar(Long id){
        this.id = id;
        
        if (id != null && id > 0) {
            edificioFacade.opcional(id).ifPresent(p -> {
                this.edificio = p;
            });
        }
        return "formul.xhtml";
    }
    
    public void buscarPorNombre(String nombre){
        System.out.println("*************************************************"+ nombre);
        Edificio edi = edificioFacade.getEdificioByName(nombre);
        System.out.println("*************************************************"+ edi.getNombre()+" ");
        nombre=(edi.getNombre()+" ");
    }
}
