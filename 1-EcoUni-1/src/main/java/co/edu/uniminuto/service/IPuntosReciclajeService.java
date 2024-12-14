package co.edu.uniminuto.service;

import co.edu.uniminuto.entity.PuntosReciclaje;

import java.util.List;

public interface IPuntosReciclajeService {
    List<PuntosReciclaje> obtenerTodos();
    void eliminar(Long id);
    PuntosReciclaje actualizar(Long id, PuntosReciclaje punto);
	PuntosReciclaje obtenerPorId(Long id);
	PuntosReciclaje guardar(PuntosReciclaje punto);
}