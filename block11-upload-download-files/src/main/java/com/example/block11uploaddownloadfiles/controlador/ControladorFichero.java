package com.example.block11uploaddownloadfiles.controlador;

import com.example.block11uploaddownloadfiles.fichero.Fichero;
import com.example.block11uploaddownloadfiles.fichero.service.FicheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/fichero")
public class ControladorFichero {

    @Autowired
    FicheroService ficheroService;

    @PostMapping("/guardar")
    public Fichero guardarFichero(@RequestParam("file") MultipartFile file,
                                  @RequestParam("categoria") String categoria) throws IOException {
        return ficheroService.guardarFichero(file, categoria);
    }

    @GetMapping("/{id}")
    public Fichero obtenerFicheroPorId(@PathVariable("id") Integer id) throws IOException {
        return ficheroService.obtenerFicheroPorId(id);
    }

    @GetMapping("/buscarPorNombre/{nombre}")
    public void obtenerFicheroPorNombre(@RequestParam("nombre") String nombre) throws IOException {
        ficheroService.obtenerFicheroPorNombre(nombre);
    }
    @PostMapping("/guardar/{extension}")
    public Fichero guardarFicheroPorExtension(@PathVariable String extension,@RequestParam("file") MultipartFile file,
                                  @RequestParam("categoria") String categoria) throws IOException {
        return ficheroService.guardarFicheroPorExtension(extension,file, categoria);
    }
    @GetMapping("setUrl")
    public void setUrl(@RequestParam(defaultValue = "c:/tmp/") String urlGuardar, @RequestParam(defaultValue = "c:/tmp/Descargas/") String urlDescargar){
        ficheroService.setUrl(urlGuardar,urlDescargar);
    }
}

