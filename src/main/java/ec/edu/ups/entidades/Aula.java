/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import java.util.Objects;

/**
 *
 * @author PAUL
 */
//@NamedQuery(name = "getAula", query = "SELECT a FROM  Aula a")
@Entity
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int capacidad;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Edificio edificio;

    public Aula() {
    }

    public Aula(int id, String nombre, int capacidad, Edificio edificio) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.edificio = edificio;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Aula{" + "id=" + id + ", nombre=" + nombre + ", capacidad=" + capacidad + ", edificio=" + edificio + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aula other = (Aula) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }
    
    
}
