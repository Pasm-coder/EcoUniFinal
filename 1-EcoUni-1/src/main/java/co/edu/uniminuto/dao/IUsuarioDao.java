package co.edu.uniminuto.dao;

import co.edu.uniminuto.entity.Usuario;
import java.util.List;

public interface IUsuarioDao {
    Usuario guardar(Usuario usuario);
    Usuario obtenerPorId(Long id);
    List<Usuario> obtenerTodos();
    void eliminar(Long id);
    List<Usuario> buscarPorEmail(String email);
}