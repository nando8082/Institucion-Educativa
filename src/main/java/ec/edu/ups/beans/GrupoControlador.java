/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.GrupoFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import ec.edu.ups.entidades.Grupo;
import ec.edu.ups.entidades.Docente;
import ec.edu.ups.entidades.Asignatura;
import ec.edu.ups.entidades.Aula;
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
public class GrupoControlador {
    @EJB
    private GrupoFacade gruFacade;
    private Grupo grupo;
    private Long id;
    private Docente docente;
    private Asignatura asig;
    private Aula aula;
    
    @Produces
    @Model
    public String tituloGrupo() {
        return "GESTIÓN DE GRUPOS";
    }
    
    @PostConstruct
    public void init() {
        this.grupo = new Grupo();
        this.docente = new Docente();
        this.asig = new Asignatura();
        this.aula = new Aula();
    }

    public GrupoFacade getGruFacade() {
        return gruFacade;
    }

    public void setGruFacade(GrupoFacade gruFacade) {
        this.gruFacade = gruFacade;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Asignatura getAsig() {
        return asig;
    }

    public void setAsig(Asignatura asig) {
        this.asig = asig;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }
    
    @Produces
    @RequestScoped
    @Named("listadoGrups")
    public List<Grupo> listarGrupos() {
        List<Grupo> prod = gruFacade.listar();
        return prod;
    }
    
    
    public String guardar(){
        try {
            grupo.setDocente(docente);
            grupo.setAsignatura(asig);
            grupo.setAula(aula);
            this.gruFacade.guardar(grupo);
        } catch (Exception e) {
        }
        return "Grupo.xhtml?faces-redirect=true";
    }
    
    public String eliminar(Long id){
        gruFacade.eliminar(id);
        return "Grupo.xhtml?faces-redirect=true";
    }
    
    public String editar(Long id){
        this.id = id;
        
        if (id != null && id > 0) {
            gruFacade.opcional(id).ifPresent(p -> {
                this.grupo = p;
            });
        }
        return "Formulario.xhtml";
    }
}
