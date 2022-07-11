/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Aula;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author PAUL
 */
@Stateless
public class AulaFacade extends AbstractFacade<Aula>{
    @PersistenceContext(name="jdbc/__institucionEducativa")
    private EntityManager em;
    private Aula aula;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AulaFacade() {
        super(Aula.class);
    }
    
    public List<Aula> listar() {
        return em.createQuery("SELECT au FROM Aula au left outer join fetch au.edificio", Aula.class).getResultList();
    }
    
    public void guardar(Aula aula) {
        if (aula.getId() != 0) {
            em.merge(aula);
        } else {
            em.persist(aula);
        }
    }
    
    public Aula porId(int id) {
        //return em.find(Producto.class, id);
        return em.createQuery("select au from Aula au left outer join fetch au.edificio where au.id=:id", Aula.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    
    public void eliminar(int id) {
        Aula Aula = porId(id);
        em.remove(aula);
    }
    
    public Optional<Aula> opcional(int id) {
        return Optional.ofNullable(porId(id));
    }
    
    public Aula getProductoByName(String name) {
        String jpql = "SELECT a FROM Aula a WHERE a.nombre = '" + name + "'";
        Aula aula = (Aula) em.createQuery(jpql).getSingleResult();
        return aula;
    }
}
