/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Edificio;
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
public class EdificioFacade extends AbstractFacade<Edificio>{
    @PersistenceContext(name="jdbc/__institucionEducativa")
    private EntityManager em;
    private Edificio edificio;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EdificioFacade() {
        super(Edificio.class);
    }
    
    public List<Edificio> listar() {
        return em.createQuery("Select ed FROM Edificio ed", Edificio.class).getResultList();
    }
    
    public void guardar(Edificio edificio) {
        if (edificio.getId()!= null && edificio.getId() > 0) {
            em.merge(edificio);
        } else {
            em.persist(edificio);
        }
    }
    
    public Edificio porId(Long id) {
        return em.find(Edificio.class, id);
    }
    
    
    public void eliminar(Long id) {
        Edificio edificio = porId(id);
        em.remove(edificio);
    }
    
    public Optional<Edificio> opcional(Long id) {
        return Optional.ofNullable(porId(id));
    }
    
    public Edificio getEdificioByName(String name) {
        String jpql = "select ed from Edificio ed WHERE ed.nombre = '" + name + "'";
        Edificio edificio = (Edificio) em.createQuery(jpql).getSingleResult();
        return edificio;
    }
    /*public void editar(Long id){
        em.createQuery("select p from Producto p", Producto.class)
         .setParameter("id", id)
         .getSingleResult();
        em.merge(producto);
    }*/
}
