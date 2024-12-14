package co.edu.uniminuto.dao;

import co.edu.uniminuto.entity.EmpresasRecicladora;
import java.util.List;

public interface IEmpresasRecicladoraDao {
    EmpresasRecicladora guardar(EmpresasRecicladora empresa);
    EmpresasRecicladora obtenerPorId(Long id);
    List<EmpresasRecicladora> obtenerTodas();
    void eliminar(Long id);
}