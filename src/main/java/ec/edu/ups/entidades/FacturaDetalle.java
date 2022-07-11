/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 *
 * @author PAUL
 */
@Entity
public class FacturaDetalle {
    @Id
    private Long id;
    private int cantidad;
    private double precio;
    private String descripcion;
    private double totalXproducto;

    public FacturaDetalle() {
    }

    public FacturaDetalle(Long id, int cantidad, double precio, String descripcion, double totalXproducto) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descripcion = descripcion;
        this.totalXproducto = totalXproducto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTotalXproducto() {
        return totalXproducto;
    }

    public void setTotalXproducto(double totalXproducto) {
        this.totalXproducto = totalXproducto;
    }

    @Override
    public String toString() {
        return "FacturaDetalle{" + "id=" + id + ", cantidad=" + cantidad + ", precio=" + precio + ", descripcion=" 
                + descripcion + ", totalXproducto=" + totalXproducto + '}';
    }
    
    
}
