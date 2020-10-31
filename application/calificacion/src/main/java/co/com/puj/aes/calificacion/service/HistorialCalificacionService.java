package co.com.puj.aes.calificacion.service;

import co.com.puj.aes.calificacion.entity.HistorialCalificacion;
import co.com.puj.aes.calificacion.exceptions.ResourceNotFoundException;
import co.com.puj.aes.calificacion.repository.HistorialCalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialCalificacionService implements ServiceInterface <HistorialCalificacion, Short> {

    @Autowired
    private HistorialCalificacionRepository historialCalificacionRepository;

    @Override
    public HistorialCalificacion findById(Short key) throws Exception {
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("El registro con id " + key + " no existe.");
        }
        return historialCalificacionRepository.findById(key).get();
    }

    public String promedioCalificacion(String idProveedor) throws Exception {
        return historialCalificacionRepository.avgCalificacionProveedor(idProveedor);
    }
    @Override
    public List<HistorialCalificacion> findAll() throws Exception {
        return (List <HistorialCalificacion>) historialCalificacionRepository.findAll();
    }

    @Override
    public HistorialCalificacion create(HistorialCalificacion entity) throws Exception {





        entity.setEstadoCalificacion(true);
        return historialCalificacionRepository.save(entity);
    }

    @Override
    public HistorialCalificacion update(HistorialCalificacion entity) throws Exception {
        HistorialCalificacion historialCalificacion = historialCalificacionRepository.findById(entity.getId()).get();

        if (entity.getIdCliente() != null) {
            historialCalificacion.setIdCliente(entity.getIdCliente());
        }
        if (entity.isEstadoCalificacion()) {
            historialCalificacion.setEstadoCalificacion(true);
        } else {
            historialCalificacion.setEstadoCalificacion(false);
        }

        return historialCalificacionRepository.save(historialCalificacion);
    }

    @Override
    public void delete(Short key) throws Exception {

    }
    public HistorialCalificacion deleteRegistroCalificacion(Short key) throws Exception {
        HistorialCalificacion historialCalificacion = historialCalificacionRepository.findById(key).get();
        historialCalificacion.setEstadoCalificacion(false);
        return historialCalificacionRepository.save(historialCalificacion);
    }

    public Boolean existeById (Short key) throws Exception {
        if(key==null){
            throw new ResourceNotFoundException("El ID a validar, no puede estar vacio.");
        }
        if (historialCalificacionRepository.findById(key) == null) {
            return false;
        }
        return historialCalificacionRepository.existsById(key);
    }

    public HistorialCalificacion findId(Short key) throws Exception {
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("La Calificacion con id " + key + " no existe.");
        }
        return historialCalificacionRepository.findById(key).get();
    }

    @Deprecated
    public HistorialCalificacion activar(Short key) throws Exception {
        HistorialCalificacion historialCalificacion = historialCalificacionRepository.findById(key).get();
        historialCalificacion.setEstadoCalificacion(true);
        return historialCalificacionRepository.save(historialCalificacion);
    }
}
