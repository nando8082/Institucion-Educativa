/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.AsignaturaFacade;
import ec.edu.ups.ejb.EstudianteFacade;
import ec.edu.ups.entidades.Asignatura;
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
public class AsignaturaControlador {
    @EJB
    private AsignaturaFacade asignaturaFacade;
    private Asignatura asignatura;
    private int id;
    private String nombres;
    
    @Produces
    @Model
    public String tituloAsignatura() {
        return "CRUD de Asignaturas";
    }
    
    @PostConstruct
    public void init() {
        this.asignatura = new Asignatura();
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }  

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    @Produces
    @RequestScoped
    @Named("listadoAsignatura")
    public List<Asignatura>listarAsignaturas(){
        List<Asignatura> asig = asignaturaFacade.getAsignatura();
        return asig;
    }
    
    public String guardar(){
        try {
            this.asignaturaFacade.guardarAsignatura(asignatura);
        } catch (Exception e) {
        }
        return "Asignatura.xhtml?faces-redirect=true";
    }
    
    public String eliminar(int id){
        asignaturaFacade.eliminar(id);
        return "Asignatura.xhtml?faces-redirect=true";
    }
    
    @Produces
    @Model
    public Asignatura asignatura(){
        if(id != 0){
            asignaturaFacade.opcional(id).ifPresent(e ->{
                this.asignatura = e;
            });
        }
        return asignatura;
    }
    
    public String editar(int id){
        this.id = id;
        if(id != 0){
            asignaturaFacade.opcional(id).ifPresent(e ->{
                    this.asignatura = e;
            });
        }
        return "Formulario.xhtml";
    }
    
    public void buscarPorNombre(String nombre){
        System.out.println("*************************************************"+ nombre);
        Asignatura as = asignaturaFacade.getAsignaturaByName(nombre);
        System.out.println("*************************************************"+ as.getNombre());
        nombres=(as.getNombre());
    }
}
