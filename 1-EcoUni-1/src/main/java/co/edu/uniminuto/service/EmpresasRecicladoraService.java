package co.edu.uniminuto.service;

import co.edu.uniminuto.dao.IEmpresasRecicladoraDao;
import co.edu.uniminuto.entity.EmpresasRecicladora;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmpresasRecicladoraService implements IEmpresasRecicladoraService {

    @Autowired
    private IEmpresasRecicladoraDao empresasRecicladoraDao;

    @Override
    public List<EmpresasRecicladora> obtenerTodas() {
        return empresasRecicladoraDao.obtenerTodas();
    }

    @Override
    public EmpresasRecicladora obtenerPorId(Long id) {
        return empresasRecicladoraDao.obtenerPorId(id);
    }

    @Override
    public EmpresasRecicladora guardar(EmpresasRecicladora empresa) {
        return empresasRecicladoraDao.guardar(empresa);
    }

    @Override
    public void eliminar(Long id) {
        empresasRecicladoraDao.eliminar(id);
    }

	@Override
	public EmpresasRecicladora actualizar(Long id, EmpresasRecicladora empresa) {
		// TODO Auto-generated method stub
		return null;
	}
}