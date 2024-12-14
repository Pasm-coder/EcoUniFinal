package co.edu.uniminuto.dao;

import co.edu.uniminuto.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UsuarioDao implements IUsuarioDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Usuario guardar(Usuario usuario) {
        entityManager.persist(usuario);
        return usuario;
    }

    @Override
    public Usuario obtenerPorId(Long id) {
        return entityManager.find(Usuario.class, id);
    }

    @Override
    public List<Usuario> obtenerTodos() {
        String jpql = "SELECT u FROM Usuario u";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        return query.getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = obtenerPorId(id);
        if (usuario != null) {
            entityManager.remove(usuario);
        }
    }

    @Override
    public List<Usuario> buscarPorEmail(String email) {
        String jpql = "SELECT u FROM Usuario u WHERE u.email = :email";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        query.setParameter("email", email);
        return query.getResultList();
    }
}