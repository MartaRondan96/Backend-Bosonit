package com.example.block11uploaddownloadfiles.controller;

import com.example.block11uploaddownloadfiles.application.FicheroService;
import com.example.block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/")
public class FicheroController {
    @Autowired
    FicheroService ficheroService;


    @RequestMapping(method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<FicheroOutputDto> subirFichero(@RequestParam(name = "ruta", defaultValue = "src/main/resources/ficheros", required = false) String ruta, @RequestParam("file")MultipartFile file){

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(ficheroService.subirFichero(ruta,file).ficheroToficheroOutputDto());
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Error al subir el fichero");
            throw new RuntimeException("Error al subir el fichero");
        }
    }

    @GetMapping("/descargar/{filename:.+}")
    public ResponseEntity<Resource> descargarFichero(@PathVariable String filename) {

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
            return ResponseEntity.ok().headers(headers).body(ficheroService.descargarFichero(filename));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<FicheroOutputDto> getFicheroById(@PathVariable int id) {

        try {
            return ResponseEntity.ok().body(ficheroService.getFicheroById(id));

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("name/{name}")
    public ResponseEntity<List<FicheroOutputDto>> getFicheroByName(@PathVariable String name) {

        try {
            return ResponseEntity.ok().body(ficheroService.getFicheroByName(name));

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
