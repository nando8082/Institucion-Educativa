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
import jakarta.persistence.OneToMany;

/**
 *
 * @author PAUL
 */
@Entity
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Estudiante estudiante;
    private Grupo grupo;
    //private Pension pension;
    private Asignatura asignatura;
    private double vmatricula;
    private double vpension;

    public Matricula() {
    }

    public Matricula(Long id, Estudiante estudiante, Grupo grupo, Asignatura asignatura, double vmatricula, double vpension) {
        this.id = id;
        this.estudiante = estudiante;
        this.grupo = grupo;
        this.asignatura = asignatura;
        this.vmatricula = vmatricula;
        this.vpension = vpension;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public double getVmatricula() {
        return vmatricula;
    }

    public void setVmatricula(double vmatricula) {
        this.vmatricula = vmatricula;
    }

    public double getVpension() {
        return vpension;
    }

    public void setVpension(double vpension) {
        this.vpension = vpension;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    @Override
    public String toString() {
        return "Matricula{" + "id=" + id + ", estudiante=" + estudiante + ", grupo=" + grupo + ", asignatura=" + asignatura + ", vmatricula=" + vmatricula + ", vpension=" + vpension + '}';
    }
    

}
