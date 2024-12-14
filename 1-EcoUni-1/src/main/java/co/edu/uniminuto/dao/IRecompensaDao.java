package co.edu.uniminuto.dao;

import co.edu.uniminuto.entity.Recompensa;
import java.util.List;

public interface IRecompensaDao {
    Recompensa guardar(Recompensa recompensa);
    Recompensa obtenerPorId(Long id);
    List<Recompensa> obtenerTodas();
    void eliminar(Long id);
}