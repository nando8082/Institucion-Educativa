/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Grupo;
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
public class GrupoFacade extends AbstractFacade<Grupo>{
    @PersistenceContext(name="jdbc/__institucionEducativa")
    private EntityManager em;
    private Grupo grupo;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoFacade() {
        super(Grupo.class);
    }
    
    public List<Grupo> listar() {
        return em.createQuery("select g from Grupo g left outer join fetch g.aula", Grupo.class).getResultList();
    }
    
    public void guardar(Grupo grupo) {
        if (grupo.getId() != 0) {
            em.merge(grupo);
        } else {
            em.persist(grupo);
        }
    }
    
    public Grupo porId(Long id) {
        //return em.find(Producto.class, id);
        return em.createQuery("select g from Grupo g left outer join fetch g.docente where g.id=:id", Grupo.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    
    public void eliminar(Long id) {
        Grupo grupo = porId(id);
        em.remove(grupo);
    }
    
    public Optional<Grupo> opcional(Long id) {
        return Optional.ofNullable(porId(id));
    }
}
