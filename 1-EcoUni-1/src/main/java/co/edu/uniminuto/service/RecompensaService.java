package co.edu.uniminuto.service;

import co.edu.uniminuto.dao.IRecompensaDao;
import co.edu.uniminuto.entity.Recompensa;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RecompensaService implements IRecompensaService {

    @Autowired
    private IRecompensaDao recompensaDao;

    @Override
    public List<Recompensa> obtenerTodas() {
        return recompensaDao.obtenerTodas();
    }

    @Override
    public Recompensa obtenerPorId(Long id) {
        return recompensaDao.obtenerPorId(id);
    }

    @Override
    public Recompensa guardar(Recompensa recompensa) {
        return recompensaDao.guardar(recompensa);
    }

    @Override
    public Recompensa actualizar(Long id, Recompensa recompensa) {
        Recompensa recompensaExistente = recompensaDao.obtenerPorId(id);
        if (recompensaExistente != null) {
            recompensaExistente.setDescripcion(recompensa.getDescripcion());
            recompensaExistente.setValorPuntos(recompensa.getValorPuntos());
            
            return recompensaDao.guardar(recompensaExistente);  
        }
        return null; 
    }

    @Override
    public void eliminar(Long id) {
        recompensaDao.eliminar(id);
    }
}