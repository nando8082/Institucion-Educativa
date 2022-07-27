/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Estudiante;
import ec.edu.ups.entidades.Matricula;
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
public class MatriculaFacade extends AbstractFacade<Matricula> {
    @PersistenceContext(name="jdbc/__institucionEducativa")
    private EntityManager em;
    private Matricula matricula;

    public MatriculaFacade(){
        super(Matricula.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Matricula>getMatricula() {
        Query query = em.createNamedQuery("getMatricula");
        List<Matricula> mat = query.getResultList();
        return mat;
    }
    
    public void guardarMatricula(Matricula matricula){
        if(matricula.getId() != 0) {
            em.merge(matricula);
        } else {
            em.persist(matricula);
        }
    }
    
    //Buscar al estudiante por la ID
    public Matricula BuscoPorId(Long id){
        return em.find(Matricula.class, id);
    }
    
    //Elimiar al estudiante por ID
    public void eliminar(Long id){
        Matricula matricula = BuscoPorId(id);
        em.remove(matricula);
    }
    
    //Para que no exista error al no existir estudiantes en la base
    public Optional<Matricula> opcional(Long id){
        return Optional.ofNullable(BuscoPorId(id));
    }
    
}
