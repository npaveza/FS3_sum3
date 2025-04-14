package com.example.sumativa1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sumativa1.model.Comentario;
import com.example.sumativa1.model.Usuario;
import com.example.sumativa1.servicio.ComentarioService;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/publicacion/{publicacionId}")
    public List<Comentario> obtenerComentariosPorPublicacion(@PathVariable Long publicacionId) {
        return comentarioService.obtenerComentariosPorPublicacion(publicacionId);
    }

    @PostMapping
    public Comentario agregarComentario(@RequestBody Comentario comentario) {
        return comentarioService.agregarComentario(comentario);
    }

    @PutMapping("/{id}/banear")
    public void banearComentario(@PathVariable Long id, @RequestBody Usuario usuario) {
        comentarioService.banearComentario(id, usuario);
    }

    @GetMapping
    public List<Comentario> obtenerTodosLosComentarios() {
        return comentarioService.obtenerTodosLosComentarios();
    }
}
