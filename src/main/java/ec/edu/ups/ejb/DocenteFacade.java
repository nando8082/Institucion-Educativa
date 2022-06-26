/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.ejb;

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
public class DocenteFacade extends AbstractFacade<Docente>{
    @PersistenceContext(name="my_persistence_unit")
    private EntityManager em;

    public DocenteFacade(){
        super(Docente.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Docente>getDocente() {
        Query query = em.createNamedQuery("getDocente");
        List<Docente> res = query.getResultList();
        return res;
    }
    
    public void guardarDocente(Docente docente){
        if(docente.getId() != 0) {
            em.merge(docente);
        } else {
            em.persist(docente);
        }
    }
    
    //Buscar al empleado por la ID
    public Docente BuscoPorId(int id){
        return em.find(Docente.class, id);
    }
    
    //Elimiar al empleado por ID
    public void eliminar(int id){
        Docente docente = BuscoPorId(id);
        em.remove(docente);
    }
    
    //Para que no exista error al no existir empleados en la base
    public Optional<Docente> opcional(int id){
        return Optional.ofNullable(BuscoPorId(id));
    }
    
    public Docente getDocenteByName(String name) {
        String jpql = "SELECT d FROM Docente d WHERE d.nombre = '" + name + "'";
        Docente docente = (Docente) em.createQuery(jpql).getSingleResult();
        return docente;
    }
}
