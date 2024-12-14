package co.edu.uniminuto.service;

import co.edu.uniminuto.entity.EmpresasRecicladora;

import java.util.List;

public interface IEmpresasRecicladoraService {
    List<EmpresasRecicladora> obtenerTodas();
    EmpresasRecicladora obtenerPorId(Long id);
    EmpresasRecicladora guardar(EmpresasRecicladora empresa);
    EmpresasRecicladora actualizar(Long id, EmpresasRecicladora empresa);
    void eliminar(Long id);
}