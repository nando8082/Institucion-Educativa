/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Dia;
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
public class DiaFacade extends AbstractFacade<Dia> {
    @PersistenceContext(name="jdbc/__institucionEducativa")
    private EntityManager em;
    private Dia dia;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiaFacade() {
        super(Dia.class);
    }
    
    public List<Dia> listar() {
        return em.createQuery("select d from Dia d", Dia.class).getResultList();
    }
    
    public void guardar(Dia dia) {
        if (dia.getId() != null && dia.getId() > 0) {
            em.merge(dia);
        } else {
            em.persist(dia);
        }
    }
    
    public Dia porId(Long id) {
        //return em.find(Producto.class, id);
        return em.createQuery("select d from Dia d", Dia.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    
    public void eliminar(Long id) {
        Dia dia = porId(id);
        em.remove(dia);
    }
    
    public Optional<Dia> opcional(Long id) {
        return Optional.ofNullable(porId(id));
    }
    
    public Dia getDiaByName(String name) {
        String jpql = "SELECT d FROM Dia d WHERE d.nombre = '" + name + "'";
        Dia dia = (Dia) em.createQuery(jpql).getSingleResult();
        return dia;
    }
    /*public void editar(Long id){
        em.createQuery("select p from Producto p", Producto.class)
         .setParameter("id", id)
         .getSingleResult();
        em.merge(producto);
    }*/
}
