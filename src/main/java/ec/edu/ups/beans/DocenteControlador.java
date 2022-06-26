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
    private String nombres;

    @Produces
    @Model
    public String titutoDocente() {
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Produces
    @RequestScoped
    @Named("listadoDocentes")
    public List<Docente> listarDocentes() {
        List<Docente> cli = docenteFacade.getDocente();
        return cli;
    }

    public String guardar() {
        try {
            this.docenteFacade.guardarDocente(docente);
        } catch (Exception e) {
        }
        return "Docente.xhtml?faces-redirect=true";
    }

    public String eliminar() {
        docenteFacade.eliminar(id);
        return "Docente.xhtml?faces-redirect=true";
    }

    @Produces
    @Model
    public Docente docente() {
        if (id != 0) {
            docenteFacade.opcional(id).ifPresent(e -> {
                this.docente = e;
            });
        }
        return docente;
    }

    public String editar(int id) {
        this.id = id;
        if (id != 0) {
            docenteFacade.opcional(id).ifPresent(e -> {
                this.docente = e;
            });
        }
        return "Formulario.xhtml";
    }
    
    public static boolean validarCedula(String cedula) {
        char digito[] = cedula.toCharArray();
        int total = 0;
        for (int i = 0; i < digito.length - 1; i++) {
            int dato = Integer.parseInt(digito[i] + "");
            if (i % 2 == 0) {
                //dato = dato*2 > 9 ? dato*2 - 9 : dato*2;
                if (dato * 2 > 9) {
                    dato = (dato * 2) - 9;
                } else {
                    dato = dato * 2;
                }
            }
            total += dato;
        }
        int ultimo = Integer.parseInt(digito[digito.length - 1] + "");
        if (total % 10 == 0 && 0 == ultimo) {
            return true;
        } else {
            total = 10 - total % 10;
            if (total == ultimo) {
                return true;
            }
        }
        return false;
    }
    
    public void buscarPorNombre(String nombre){
        System.out.println("*************************************************"+ nombre);
        Docente cli = docenteFacade.getDocenteByName(nombre);
        System.out.println("*************************************************"+ cli.getNombre()+" "+cli.getApellido());
        nombres=(cli.getNombre()+" "+cli.getApellido());
    }
}
