package com.example.sumativa1.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sumativa1.model.Comentario;
import com.example.sumativa1.model.Publicacion;
import com.example.sumativa1.model.Usuario;
import com.example.sumativa1.repository.ComentarioRepository;
import com.example.sumativa1.repository.PublicacionRepository;
import com.example.sumativa1.repository.UsuarioRepository;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    public List<Comentario> obtenerComentariosPorPublicacion(Long publicacionId) {
        return comentarioRepository.findByPublicacionId(publicacionId);
    }

    public Comentario agregarComentario(Comentario comentario) {
        Usuario autor = usuarioRepository.findById(comentario.getAutor().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Publicacion publicacion = publicacionRepository.findById(comentario.getPublicacion().getId())
                .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));

        comentario.setAutor(autor);
        comentario.setPublicacion(publicacion);

        return comentarioRepository.save(comentario);
    }

    public void banearComentario(Long id, Usuario usuario) {
        Comentario comentario = comentarioRepository.findById(id).orElse(null);

        if (comentario == null) {
            throw new RuntimeException("Comentario no encontrado");
        }

        if (!usuario.getRol().equals("ADMIN")) {
            throw new RuntimeException("No tienes permisos para banear este comentario");
        }

        comentario.setBaneado(true);
        comentarioRepository.save(comentario);
    }

    public List<Comentario> obtenerTodosLosComentarios() {
        return comentarioRepository.findAll();
    }
}