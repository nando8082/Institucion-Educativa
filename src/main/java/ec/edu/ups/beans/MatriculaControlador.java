/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.EstudianteFacade;
import ec.edu.ups.ejb.MatriculaFacade;
import ec.edu.ups.entidades.Estudiante;
import ec.edu.ups.entidades.Matricula;
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
public class MatriculaControlador {
    @EJB
    private MatriculaFacade matriculaFacade;
    private Matricula matricula;
    private Long id;
    private String nombres;
    
    @Produces
    @Model
    public String tituloMatricula() {
        return "CRUD de Matricula";
    }
    
    @PostConstruct
    public void init() {
        this.matricula = new Matricula();
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    @Named("listadoMatriculas")
    public List<Matricula>listarMatriculas(){
        List<Matricula> mat = matriculaFacade.getMatricula();
        return mat;
    }
    
    public String guardar(){
        try {
            this.matriculaFacade.guardarMatricula(matricula);
        } catch (Exception e) {
        }
        return "Matricula.xhtml?faces-redirect=true";
    }
    
    public String eliminar(Long id){
        matriculaFacade.eliminar(id);
        return "Matricula.xhtml?faces-redirect=true";
    }
    
    @Produces
    @Model
    public Matricula matricula(){
        if(id != 0){
            matriculaFacade.opcional(id).ifPresent(e ->{
                this.matricula = e;
            });
        }
        return matricula;
    }
    
    public String editar(Long id){
        this.id = id;
        if(id != 0){
            matriculaFacade.opcional(id).ifPresent(e ->{
                    this.matricula = e;
            });
        }
        return "Formulario.xhtml";
    }
    
}
