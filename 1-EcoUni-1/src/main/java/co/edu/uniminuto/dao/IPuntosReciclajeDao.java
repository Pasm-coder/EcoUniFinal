package co.edu.uniminuto.dao;

import co.edu.uniminuto.entity.PuntosReciclaje;

import java.util.List;

public interface IPuntosReciclajeDao {
    List<PuntosReciclaje> obtenerTodos(); 
    PuntosReciclaje obtenerPorId(Long id);  
    PuntosReciclaje guardar(PuntosReciclaje punto);
    void eliminar(Long id); 
}