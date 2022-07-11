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
    /*@EJB
    private GrupoFacade gruFacade;
    private Grupo grupo;
    private Long id;
    private Docente docente;
    private Asignatura asig;
    private Aula aula;
    
    @Produces
    @Model
    public String titulo() {
        return "GESTIÓN DE GRUPOS";
    }
    
    @PostConstruct
    public void init() {
        this.grupo = new Grupo();
        this.docente = new Docente();
        this.asig = new Asignatura();
        this.aula = new Aula();
    }*/
    
    /*public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
    
    
    @Produces
    @RequestScoped
    @Named("listadoProductos")
    public List<Producto> listarProductos() {
        
        List<Producto> prod = prodFacade.listar();
        return prod;
    }
    
    
    public String guardar(){
        try {
            producto.setSucursal(sucursal);
            this.prodFacade.guardar(producto);
        } catch (Exception e) {
        }
        return "Producto.xhtml?faces-redirect=true";
    }
    
    public String eliminar(Long id){
        prodFacade.eliminar(id);
        return "Producto.xhtml?faces-redirect=true";
    }
    
    public String editar(Long id){
        this.id = id;
        
        if (id != null && id > 0) {
            prodFacade.opcional(id).ifPresent(p -> {
                this.producto = p;
            });
        }
        return "form.xhtml";
    }*/
}
