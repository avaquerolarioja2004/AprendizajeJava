package com.example.block11uploaddownloadfiles.fichero.service;

import com.example.block11uploaddownloadfiles.fichero.Fichero;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FicheroService {
    public Fichero guardarFichero(MultipartFile file, String categoria) throws IOException;
    public Fichero obtenerFicheroPorId(Integer id) throws IOException;
    public void obtenerFicheroPorNombre(String nombre) throws IOException;
    public Fichero guardarFicheroPorExtension(String extension,MultipartFile file, String categoria) throws IOException;
    public void setUrl(String urlGuardar, String urlDescargar);
}
