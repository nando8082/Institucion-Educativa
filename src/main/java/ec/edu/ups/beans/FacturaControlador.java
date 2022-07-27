/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.FacturaFacade;
import ec.edu.ups.ejb.MatriculaFacade;
import ec.edu.ups.entidades.Estudiante;
import ec.edu.ups.entidades.FacturaCabecera;
import ec.edu.ups.entidades.FacturaDetalle;
import ec.edu.ups.entidades.Matricula;
import jakarta.ejb.EJB;
import jakarta.enterprise.inject.Model;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PAUL
 */
@Model
public class FacturaControlador {
    @EJB
    private FacturaFacade facturaFacade;
    @EJB
    private MatriculaFacade matriculaFacade;
    private FacturaCabecera factura;
    private Estudiante estudiante;
    private Matricula matricula = new Matricula();
    private Long idMatricula;
    private String nombreMatricula;
    private FacturaDetalle detalle;
    static List<FacturaDetalle> detalles = new ArrayList<>();
    
    //calcular stock nuevo despues de venta
    private int stockActualizado;
    
    //Datos de la factura cabecera.
    private int idEstudiante;
    private String nombreEstudiante;
    private String apellido;
    private int stockAnterior;
    private int cant;
    
    //Datos del detalle factura
    private int cantidad;
    private double precio;
    private double totalXproducto;
    private double subtotal;
    private double iva;
    private double total;
    
    
    public FacturaControlador(){
        estudiante = new Estudiante();
        factura = new FacturaCabecera();
    }

    public FacturaFacade getFacturaFacade() {
        return facturaFacade;
    }

    public void setFacturaFacade(FacturaFacade facturaFacade) {
        this.facturaFacade = facturaFacade;
    }

    public MatriculaFacade getMatriculaFacade() {
        return matriculaFacade;
    }

    public void setMatriculaFacade(MatriculaFacade matriculaFacade) {
        this.matriculaFacade = matriculaFacade;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Long getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Long idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getNombreMatricula() {
        return nombreMatricula;
    }

    public void setNombreMatricula(String nombreMatricula) {
        this.nombreMatricula = nombreMatricula;
    }

    public int getStockActualizado() {
        return stockActualizado;
    }

    public void setStockActualizado(int stockActualizado) {
        this.stockActualizado = stockActualizado;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getStockAnterior() {
        return stockAnterior;
    }

    public void setStockAnterior(int stockAnterior) {
        this.stockAnterior = stockAnterior;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
    
    public List<FacturaDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<FacturaDetalle> detalles) {
        this.detalles = detalles;
    }

    public FacturaDetalle getDetalle() {
        return detalle;
    }
    
    public void setDetalle(FacturaDetalle detalle) {
        this.detalle = detalle;
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

    public double getTotalXproducto() {
        return totalXproducto;
    }

    public void setTotalXproducto(double totalXproducto) {
        this.totalXproducto = totalXproducto;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void buscarEstudiante(){
        estudiante = facturaFacade.buscarEstudiantePorId(idEstudiante);
        nombreEstudiante = estudiante.getNombre();
    }
    public void buscarMatricula(){
        System.out.println("CODIGO DEL PRODUCTO **** : " + idMatricula);
        
        matricula = facturaFacade.buscarMatriculaPorId(idMatricula);
        nombreMatricula = matricula.getAsignatura().getNombre(); 
        precio = matricula.getVmatricula();
    }   
    
    
    public void add() {
        totalXproducto = cantidad * precio;
        detalle = new FacturaDetalle();
        detalle.setId(idMatricula);
        detalle.setDescripcion(nombreMatricula);
        detalle.setCantidad(cantidad);
        detalle.setPrecio(precio);
        detalle.setTotalXproducto(totalXproducto);
        detalles.add(detalle);
        
        subtotal = 0;
        iva = 0;
        total = 0;
        for (int i = 0; i < detalles.size(); i++) {
             subtotal =  subtotal + detalles.get(i).getTotalXproducto();
             iva = subtotal * 0.12;
             total = subtotal + iva;  
        }	
        cant = cantidad+stockAnterior;
    }
    
    public void guardarFactura(){
        estudiante.setId(idEstudiante);
        estudiante.setNombre(nombreEstudiante);
        factura.setEstudiante(estudiante);
        factura.setIva(iva);
        factura.setSubtotal(subtotal);
        factura.setTotal(total);
        factura.setId(idEstudiante);
        factura.setDetalles(detalles);
        facturaFacade.guardarFactura(factura);
        //updateStock();
        detalles.clear();
        
    }
    
    /*public void updateStock(){
        for (int i = 0; i < detalles.size(); i++) {
            Producto p=prodFacade.getProductoByName(detalles.get(i).getDescripcion());
            p.setStock(p.getStock()-detalles.get(i).getCantidad());
            p.setCantidad(cantidad+stockAnterior);
            prodFacade.edit(p);
        }
    }*/
}
