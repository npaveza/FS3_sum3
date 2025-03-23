package com.example.sumativa1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sumativa1.model.Publicacion;
import com.example.sumativa1.model.Usuario;
import com.example.sumativa1.servicio.PublicacionService;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public List<Publicacion> obtenerTodasLasPublicaciones() {
        return publicacionService.obtenerTodasLasPublicaciones();
    }

    @GetMapping("/{id}")
    public Publicacion obtenerPublicacionPorId(@PathVariable Long id) {
        return publicacionService.obtenerPublicacionPorId(id);
    }

    @GetMapping("/autor/{autorId}")
    public List<Publicacion> obtenerPublicacionesPorAutor(@PathVariable Long autorId) {
        return publicacionService.obtenerPublicacionesPorAutor(autorId);
    }

    @PostMapping
    public Publicacion crearPublicacion(@RequestBody Publicacion publicacion) {
        return publicacionService.crearPublicacion(publicacion);
    }

    @PutMapping("/{id}")
    public Publicacion actualizarPublicacion(@PathVariable Long id, @RequestBody Publicacion publicacion) {
        return publicacionService.actualizarPublicacion(id, publicacion);
    }

    @DeleteMapping("/{id}")
    public void eliminarPublicacion(@PathVariable Long id, @RequestBody Usuario usuario) {
        publicacionService.eliminarPublicacion(id, usuario);
    }

    @PutMapping("/{id}/banear")
    public void banearPublicacion(@PathVariable Long id, @RequestBody Usuario usuario) {
        publicacionService.banearPublicacion(id, usuario);
    }
}