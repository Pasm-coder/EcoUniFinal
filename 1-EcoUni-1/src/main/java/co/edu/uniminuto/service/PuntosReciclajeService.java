package co.edu.uniminuto.service;

import co.edu.uniminuto.dao.IPuntosReciclajeDao;
import co.edu.uniminuto.entity.PuntosReciclaje;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PuntosReciclajeService implements IPuntosReciclajeService {

    @Autowired
    private IPuntosReciclajeDao puntosReciclajeDao;

    @Override
    public List<PuntosReciclaje> obtenerTodos() {
        return puntosReciclajeDao.obtenerTodos();  
    }
    

    @Override
    public void eliminar(Long id) {
        puntosReciclajeDao.eliminar(id); 
    }
    
    @Override
    public PuntosReciclaje guardar(PuntosReciclaje puntosReciclaje) {
        return puntosReciclajeDao.guardar(puntosReciclaje);
    }

    @Override
    public PuntosReciclaje actualizar(Long id, PuntosReciclaje punto) {
        PuntosReciclaje puntoExistente = puntosReciclajeDao.obtenerPorId(id);
        if (puntoExistente != null) {
            puntoExistente.setDireccion(punto.getDireccion());
            puntoExistente.setTipoMaterial(punto.getTipoMaterial());
            puntoExistente.setEmpresasRecicladora(punto.getEmpresasRecicladora());
            return puntosReciclajeDao.guardar(puntoExistente);  
        }
        return null;  
    }

    @Override
    public PuntosReciclaje obtenerPorId(Long id) {
        return puntosReciclajeDao.obtenerPorId(id);  
    }
}