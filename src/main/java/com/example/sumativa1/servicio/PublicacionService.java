package com.example.sumativa1.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sumativa1.model.Publicacion;
import com.example.sumativa1.model.Usuario;
import com.example.sumativa1.repository.PublicacionRepository;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    public List<Publicacion> obtenerTodasLasPublicaciones() {
        return publicacionRepository.findAll();
    }

    public Publicacion obtenerPublicacionPorId(Long id) {
        return publicacionRepository.findById(id).orElse(null);
    }

    public List<Publicacion> obtenerPublicacionesPorAutor(Long autorId) {
        return publicacionRepository.findByAutorId(autorId);
    }

    public Publicacion crearPublicacion(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    public Publicacion actualizarPublicacion(Long id, Publicacion publicacionActualizada) {
        if (publicacionRepository.existsById(id)) {
            publicacionActualizada.setId(id);
            return publicacionRepository.save(publicacionActualizada);
        }
        return null;
    }

    public void eliminarPublicacion(Long id, Usuario usuario) {
        Publicacion publicacion = publicacionRepository.findById(id).orElse(null);

        if (publicacion == null) {
            throw new RuntimeException("Publicación no encontrada");
        }

        if (!usuario.getRol().equals("ADMIN")) {
            throw new RuntimeException("No tienes permisos para eliminar esta publicación");
        }

        publicacionRepository.deleteById(id);
    }

    public void banearPublicacion(Long id, Usuario usuario) {
        Publicacion publicacion = publicacionRepository.findById(id).orElse(null);

        if (publicacion == null) {
            throw new RuntimeException("Publicación no encontrada");
        }

        if (!usuario.getRol().equals("ADMIN")) {
            throw new RuntimeException("No tienes permisos para banear esta publicación");
        }

        publicacion.setBaneado(true);
        publicacionRepository.save(publicacion);
    }
}