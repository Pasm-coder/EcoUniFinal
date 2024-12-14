package co.edu.uniminuto.dao;

import co.edu.uniminuto.entity.PuntosReciclaje;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PuntosReciclajeDao implements IPuntosReciclajeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PuntosReciclaje> obtenerTodos() {
        String jpql = "SELECT p FROM PuntosReciclaje p";
        TypedQuery<PuntosReciclaje> query = entityManager.createQuery(jpql, PuntosReciclaje.class);
        return query.getResultList();
    }

    @Override
    public PuntosReciclaje obtenerPorId(Long id) {
        return entityManager.find(PuntosReciclaje.class, id);
    }

    @Override
    public PuntosReciclaje guardar(PuntosReciclaje puntosReciclaje) {
        if (puntosReciclaje.getId() == 0L) {
            entityManager.persist(puntosReciclaje); 
        } else {
            entityManager.merge(puntosReciclaje); 
        }
        return puntosReciclaje;
    }

    @Override
    public void eliminar(Long id) {
        PuntosReciclaje punto = obtenerPorId(id);
        if (punto != null) {
            entityManager.remove(punto);
        }
    }
}