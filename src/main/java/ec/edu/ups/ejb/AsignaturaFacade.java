/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Asignatura;
import ec.edu.ups.entidades.Docente;
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
public class AsignaturaFacade extends AbstractFacade<Asignatura>{
    @PersistenceContext(name="jdbc/__institucionEducativa")
    private EntityManager em;

    public AsignaturaFacade(){
        super(Asignatura.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Asignatura> getAsignaturas() {
        Query query = em.createNamedQuery("getAsignaturas");
        List<Asignatura> res = query.getResultList();
        return res;
    }
    
    public void guardarAsignatura(Asignatura asignatura){
        if(asignatura.getId() != 0) {
            em.merge(asignatura);
        } else {
            em.persist(asignatura);
        }
    }
    
    //Buscar al empleado por la ID
    public Asignatura BuscoPorId(Long id){
        return em.find(Asignatura.class, id);
    }
    
    //Elimiar al empleado por ID
    public void eliminar(long id){
        Asignatura asignatura = BuscoPorId(id);
        em.remove(asignatura);
    }
    
    //Para que no exista error al no existir empleados en la base
    public Optional<Asignatura> opcional(Long id){
        return Optional.ofNullable(BuscoPorId(id));
    }
    
    public Asignatura getAsignaturaByName(String name) {
        String jpql = "SELECT a FROM Asignatura a WHERE a.nombre = '" + name + "'";
        Asignatura asignatura = (Asignatura) em.createQuery(jpql).getSingleResult();
        return asignatura;
    }
}
