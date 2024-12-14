package co.edu.uniminuto.service;

import co.edu.uniminuto.dao.IUsuarioDao;
import co.edu.uniminuto.entity.Usuario;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioDao.obtenerTodos();
    }

    @Override
    public Usuario obtenerPorId(Long id) {
        return usuarioDao.obtenerPorId(id);
    }

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        return usuarioDao.guardar(usuario);  
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuario) {
        Usuario usuarioExistente = usuarioDao.obtenerPorId(id);
        if (usuarioExistente != null) {
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setPassword(usuario.getPassword());
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setApellido(usuario.getApellido());
            usuarioExistente.setTelefono(usuario.getTelefono());
            return usuarioDao.guardar(usuarioExistente);  
        }
        return null;  
    }

    @Override
    public void eliminar(Long id) {
        usuarioDao.eliminar(id);  
    }

    @Override
    public List<Usuario> buscarPorEmail(String email) {
        return usuarioDao.buscarPorEmail(email);
    }
}