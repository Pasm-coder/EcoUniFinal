package co.edu.uniminuto.dao;

import co.edu.uniminuto.entity.EmpresasRecicladora;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpresasRecicladoraDao implements IEmpresasRecicladoraDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EmpresasRecicladora guardar(EmpresasRecicladora empresa) {
        entityManager.persist(empresa);
        return empresa;
    }

    @Override
    public EmpresasRecicladora obtenerPorId(Long id) {
        return entityManager.find(EmpresasRecicladora.class, id);
    }

    @Override
    public List<EmpresasRecicladora> obtenerTodas() {
        String jpql = "SELECT e FROM EmpresasRecicladora e";
        TypedQuery<EmpresasRecicladora> query = entityManager.createQuery(jpql, EmpresasRecicladora.class);
        return query.getResultList();
    }

    @Override
    public void eliminar(Long id) {
        EmpresasRecicladora empresa = obtenerPorId(id);
        if (empresa != null) {
            entityManager.remove(empresa);
        }
    }
}