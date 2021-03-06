/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.EstudianteFacade;
import ec.edu.ups.entidades.Estudiante;
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
public class EstudianteControlador {
    @EJB
    private EstudianteFacade estudianteFacade;
    private Estudiante estudiante;
    private int id;
    private String nombres;
    
    @Produces
    @Model
    public String tituloEstudiante() {
        return "CRUD de Estudiantes";
    }
    
    @PostConstruct
    public void init() {
        this.estudiante = new Estudiante();
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
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
    @Named("listadoEstudiantes")
    public List<Estudiante>listarEstudiantes(){
        List<Estudiante> cli = estudianteFacade.getEstudiante();
        return cli;
    }
    
    public String guardar(){
        try {
            this.estudianteFacade.guardarEstudiante(estudiante);
        } catch (Exception e) {
        }
        return "Estudiante.xhtml?faces-redirect=true";
    }
    
    public String eliminar(int id){
        estudianteFacade.eliminar(id);
        return "Estudiante.xhtml?faces-redirect=true";
    }
    
    @Produces
    @Model
    public Estudiante estudiante(){
        if(id != 0){
            estudianteFacade.opcional(id).ifPresent(e ->{
                this.estudiante = e;
            });
        }
        return estudiante;
    }
    
    public String editar(int id){
        this.id = id;
        if(id != 0){
            estudianteFacade.opcional(id).ifPresent(e ->{
                    this.estudiante = e;
            });
        }
        return "Formulario.xhtml";
    }
    
    
    public void buscarPorNombre(String nombre){
        System.out.println("*************************************************"+ nombre);
        Estudiante cli = estudianteFacade.getEstudianteByName(nombre);
        System.out.println("*************************************************"+ cli.getNombre()+" "+cli.getApellido());
        nombres=(cli.getNombre()+" "+cli.getApellido());
    }
}
