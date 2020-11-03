package co.com.puj.aes.calificacion.service;

import co.com.puj.aes.calificacion.entity.Calificacion;
import co.com.puj.aes.calificacion.entity.HistorialCalificacion;
import co.com.puj.aes.calificacion.exceptions.ResourceNotFoundException;
import co.com.puj.aes.calificacion.repository.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionService implements ServiceInterface  <Calificacion, Short>{
    @Autowired
    private CalificacionRepository calificacionRepository;

    @Override
    public Calificacion findById(Short key) throws Exception {
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("La Calificaciones con id " + key + " no existe.");
        }
        return calificacionRepository.findById(key).get();
    }

    public Calificacion findByIdProvider(String idProveedor) throws Exception {
        return calificacionRepository.findByIdProveedor(idProveedor);
    }

    @Override
    public List<Calificacion> findAll() throws Exception {
        return (List <Calificacion>) calificacionRepository.findAll();
    }

    @Override
    public Calificacion create(Calificacion entity) throws Exception {
        entity.setEstadoCalificacion(true);
        return calificacionRepository.save(entity);
    }

    @Override
    public Calificacion update(Calificacion entity) throws Exception {
        Calificacion calificacion = calificacionRepository.findById(entity.getIdCalificacion()).get();
        return calificacionRepository.save(calificacion);
    }

    @Override
    public void delete(Short key) throws Exception {

    }

    public Calificacion deleteCalificacion(Short key) throws Exception {
        Calificacion calificacion = calificacionRepository.findById(key).get();
        calificacion.setEstadoCalificacion(false);
        return calificacionRepository.save(calificacion);
    }

    public Boolean existeById (Short key) throws Exception {
        if(key==null){
            throw new ResourceNotFoundException("El ID a validar, no puede estar vacio.");
        }
        if (calificacionRepository.findById(key) == null) {
            return false;
        }
        return calificacionRepository.existsById(key);
    }

    public Calificacion findId(Short key) throws Exception {
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("La Calificacion con id " + key + " no existe.");
        }
        return calificacionRepository.findById(key).get();
    }

    @Deprecated
    public Calificacion activar(Short key) throws Exception {
        Calificacion eps = calificacionRepository.findById(key).get();
        eps.setEstadoCalificacion(true);
        return calificacionRepository.save(eps);
    }




}
