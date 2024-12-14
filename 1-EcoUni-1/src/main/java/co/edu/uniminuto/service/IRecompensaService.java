package co.edu.uniminuto.service;

import co.edu.uniminuto.entity.Recompensa;

import java.util.List;

public interface IRecompensaService {
    List<Recompensa> obtenerTodas();
    Recompensa obtenerPorId(Long id);
    Recompensa guardar(Recompensa recompensa);
    Recompensa actualizar(Long id, Recompensa recompensa);
    void eliminar(Long id);
}