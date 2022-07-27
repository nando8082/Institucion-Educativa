/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Estudiante;
import ec.edu.ups.entidades.FacturaCabecera;
import ec.edu.ups.entidades.Matricula;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author PAUL
 */
@Stateless
public class FacturaFacade extends AbstractFacade<FacturaCabecera>{
    @PersistenceContext(name="jdbc/__institucionEducativa")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaFacade() {
        super(FacturaCabecera.class);
    }
    
     public Estudiante buscarEstudiantePorId(int id) {
        //return em.find(Producto.class, id);
        return em.createQuery("select e from Estudiante e  where e.id=:id", Estudiante.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    public Matricula buscarMatriculaPorId(Long codigo) {
        //return em.find(Producto.class, id);
        return em.createQuery("select m from Matricula m where m.id=:id", Matricula.class)
                .setParameter("id", codigo)
                .getSingleResult();
    }
    
    public void guardarFactura(FacturaCabecera factura) {
        if(factura.getId() != 0) {
            em.merge(factura);
        } else {
            em.persist(factura);
        }
    }
}
