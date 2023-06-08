package com.example.block11uploaddownloadfiles.controlador;

import com.example.block11uploaddownloadfiles.fichero.Fichero;
import com.example.block11uploaddownloadfiles.fichero.service.FicheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/fichero")
public class ControladorFichero {

    private String url = "c:/tmp/";

    @Autowired
    private FicheroRepository ficheroRepository;

    @PostMapping("/guardar")
    public Fichero guardarFichero(@RequestParam("file") MultipartFile file,
                                  @RequestParam("categoria") String categoria) throws IOException {
        if (file.isEmpty()){
            throw new RuntimeException("El fichero está vacío");
        }
        Fichero fichero = new Fichero();
        fichero.setNombreDeFichero(file.getOriginalFilename());
        fichero.setFechaDeSubida(new Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getDayOfMonth()));
        fichero.setCategoría(categoria);
        ficheroRepository.save(fichero);
        String path = url + file.getOriginalFilename();
        file.transferTo(new File(path));

        return fichero;
    }

    @GetMapping("/{id}")
    public void obtenerFicheroPorId(@PathVariable("id") Integer id) throws IOException {
        Fichero fichero = ficheroRepository.findById(id).orElse(null);
        String pathFichero = url + fichero.getNombreDeFichero();
        File file = new File(pathFichero);
        String pathDestino = "c:/tmp/Descargas/" + fichero.getNombreDeFichero();
        File destino = new File(pathDestino);
        Files.copy(file.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    @GetMapping("/buscarPorNombre/{nombre}")
    public void obtenerFicheroPorNombre(@RequestParam("nombre") String nombre) throws IOException {
        for (Fichero fichero :
                ficheroRepository.findAll()) {
            if (fichero.getNombreDeFichero().equals(nombre)) {
                String pathFichero = url + fichero.getNombreDeFichero();
                File file = new File(pathFichero);
                String pathDestino = "c:/tmp/Descargas/" + fichero.getNombreDeFichero();
                File destino = new File(pathDestino);
                Files.copy(file.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
    @PostMapping("/guardar/{extension}")
    public Fichero guardarFicheroPorExtension(@PathVariable String extension,@RequestParam("file") MultipartFile file,
                                  @RequestParam("categoria") String categoria) throws IOException {
        if (file.isEmpty()){
            throw new RuntimeException("El fichero está vacío");
        }
        if (!file.getOriginalFilename().endsWith(extension)){
            throw new RuntimeException("El fichero no tiene la extensión correcta");
        }
        Fichero fichero = new Fichero();
        fichero.setNombreDeFichero(file.getOriginalFilename());
        fichero.setFechaDeSubida(new Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getDayOfMonth()));
        fichero.setCategoría(categoria);
        ficheroRepository.save(fichero);
        String path = url + file.getOriginalFilename();
        file.transferTo(new File(path));

        return fichero;
    }
}

