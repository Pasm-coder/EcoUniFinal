package co.edu.uniminuto.service;

import co.edu.uniminuto.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> obtenerTodos(); 
    Usuario obtenerPorId(Long id); 
    Usuario guardar(Usuario usuario);
    Usuario actualizar(Long id, Usuario usuario);
    void eliminar(Long id); 
    List<Usuario> buscarPorEmail(String email); 
}