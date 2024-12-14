package co.edu.uniminuto.dao;

import co.edu.uniminuto.entity.Recompensa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecompensaDao implements IRecompensaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Recompensa guardar(Recompensa recompensa) {
        entityManager.persist(recompensa);
        return recompensa;
    }

    @Override
    public Recompensa obtenerPorId(Long id) {
        return entityManager.find(Recompensa.class, id);
    }

    @Override
    public List<Recompensa> obtenerTodas() {
        String jpql = "SELECT r FROM Recompensa r";
        TypedQuery<Recompensa> query = entityManager.createQuery(jpql, Recompensa.class);
        return query.getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Recompensa recompensa = obtenerPorId(id);
        if (recompensa != null) {
            entityManager.remove(recompensa);
        }
    }
}