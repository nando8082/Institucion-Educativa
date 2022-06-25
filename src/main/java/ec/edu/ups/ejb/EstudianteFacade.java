/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Estudiante;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author PAUL
 */
@Stateless
public class EstudianteFacade extends AbstractFacade<Estudiante>{

    @PersistenceContext(name="my_persistence_unit")
    private EntityManager em;

    public EstudianteFacade(){
        super(Estudiante.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Estudiante>getEstudiante() {
        Query query = em.createNamedQuery("getEstudiante");
        List<Estudiante> res = query.getResultList();
        return res;
    }
    
    public void guardarEstudiante(Estudiante estudiante){
        if(estudiante.getId() != 0) {
            em.merge(estudiante);
        } else {
            em.persist(estudiante);
        }
    }
    
    //Buscar al empleado por la ID
    public Estudiante BuscoPorId(int id){
        return em.find(Estudiante.class, id);
    }
    
    //Elimiar al empleado por ID
    public void eliminar(int id){
        Estudiante estudiante = BuscoPorId(id);
        em.remove(estudiante);
    }
    
    //Para que no exista error al no existir empleados en la base
    public Optional<Estudiante> opcional(int id){
        return Optional.ofNullable(BuscoPorId(id));
    }
    
    public Estudiante getEstudianteByName(String name) {
        String jpql = "SELECT e FROM Estudiante e WHERE e.nombre = '" + name + "'";
        Estudiante estudiante = (Estudiante) em.createQuery(jpql).getSingleResult();
        return estudiante;
    }
}
