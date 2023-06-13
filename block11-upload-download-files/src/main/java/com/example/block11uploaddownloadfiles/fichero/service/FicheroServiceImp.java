package com.example.block11uploaddownloadfiles.fichero.service;

import com.example.block11uploaddownloadfiles.fichero.Fichero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Date;
@Service
public class FicheroServiceImp implements FicheroService{
    @Autowired
    private FicheroRepository ficheroRepository;

    private String urlGuardar = "c:/tmp/";
    private String urlDescargar = "c:/tmp/Descargas/";

    @Override
    public Fichero guardarFichero(MultipartFile file, String categoria) throws IOException {
        if (file.isEmpty()){
            throw new RuntimeException("El fichero está vacío");
        }
        Fichero fichero = new Fichero();
        fichero.setNombreDeFichero(file.getOriginalFilename());
        fichero.setFechaDeSubida(new Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getDayOfMonth()));
        fichero.setCategoría(categoria);
        ficheroRepository.save(fichero);
        String path = urlGuardar + file.getOriginalFilename();
        file.transferTo(new File(path));

        return fichero;
    }

    @Override
    public Fichero obtenerFicheroPorId(Integer id) throws IOException {
        Fichero fichero = ficheroRepository.findById(id).orElse(null);
        String pathFichero = urlGuardar + fichero.getNombreDeFichero();
        File file = new File(pathFichero);
        String pathDestino = urlDescargar + fichero.getNombreDeFichero();
        File destino = new File(pathDestino);
        Files.copy(file.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return fichero;
    }

    @Override
    public void obtenerFicheroPorNombre(String nombre) throws IOException {
        for (Fichero fichero :
                ficheroRepository.findAll()) {
            if (fichero.getNombreDeFichero().equals(nombre)) {
                String pathFichero = urlGuardar + fichero.getNombreDeFichero();
                File file = new File(pathFichero);
                String pathDestino = urlDescargar + fichero.getNombreDeFichero();
                File destino = new File(pathDestino);
                Files.copy(file.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    @Override
    public Fichero guardarFicheroPorExtension(String extension, MultipartFile file, String categoria) throws IOException {
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
        String path = urlGuardar + file.getOriginalFilename();
        file.transferTo(new File(path));

        return fichero;
    }

    @Override
    public void setUrl(String urlGuardar, String urlDescargar) {
        this.urlGuardar = urlGuardar;
        this.urlDescargar = urlDescargar;
    }
}
