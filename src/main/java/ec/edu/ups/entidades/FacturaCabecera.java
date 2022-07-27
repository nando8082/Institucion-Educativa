/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author PAUL
 */
@Entity
public class FacturaCabecera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double total;
    private LocalDate fecha;
    private double subtotal;
    private double iva;
    @ManyToOne
    @JoinColumn
    private Estudiante estudiante;
    
    @OneToMany(fetch = FetchType.LAZY)
    private List<FacturaDetalle> detalles;

    public FacturaCabecera() {
    }

    public FacturaCabecera(int id, double total, LocalDate fecha, double subtotal, double iva, Estudiante estudiante, List<FacturaDetalle> detalles) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.iva = iva;
        this.estudiante = estudiante;
        this.detalles = detalles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public List<FacturaDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<FacturaDetalle> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "FacturaCabecera{" + "id=" + id + ", total=" + total + ", fecha=" + fecha + ", subtotal=" + subtotal 
                + ", iva=" + iva + ", estudiante=" + estudiante + ", detalles=" + detalles + '}';
    }
    
}
