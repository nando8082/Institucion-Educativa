/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.DocenteFacade;
import ec.edu.ups.entidades.Docente;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Named;
import jakarta.ws.rs.Produces;
import java.util.List;

/**
 *
 * @author Daniel
 */
@Model
public class DocenteControlador {
    @EJB
    private DocenteFacade docenteFacade;
    private Docente docente;
    private int id;
    private String nombre;
    
    @Produces
    @Model
    public String tituloDocente() {
        return "CRUD de Docentes";
    }
    
    @PostConstruct
    public void init() {
        this.docente = new Docente();
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }  

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Produces
    @RequestScoped
    @Named("listadoDocente")
    public List<Docente>listarDocentes(){
        List<Docente> doc = docenteFacade.getDocentes();
        return doc;
    }
    
    public String guardar(){
        try {
            this.docenteFacade.guardarDocente(docente);
        } catch (Exception e) {
        }
        return "Docente.xhtml?faces-redirect=true";
    }
    
    public String eliminar(int id){
        docenteFacade.eliminar(id);
        return "Docente.xhtml?faces-redirect=true";
    }
    
    @Produces
    @Model
    public Docente estudiante(){
        if(id != 0){
            docenteFacade.opcional(id).ifPresent(e ->{
                this.docente = e;
            });
        }
        return docente;
    }
    
    public String editar(int id){
        this.id = id;
        if(id != 0){
            docenteFacade.opcional(id).ifPresent(e ->{
                    this.docente = e;
            });
        }
        return "Formulario.xhtml";
    }
    
    public void buscarPorNombre(String nombre){
        System.out.println("*************************************************"+ nombre);
        Docente doc = docenteFacade.getDocenteByName(nombre);
        System.out.println("*************************************************"+ doc.getNombre()+" "+doc.getApellido());
        nombre=(doc.getNombre()+" "+doc.getApellido());
    }
}
