package com.example.Springanotaciones;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class ResponseController {
    //
    @GetMapping(value="/ok")
    public ResponseEntity<String> ok(){
        return new ResponseEntity<>(HttpStatus.OK +" Respuesta correcta ",HttpStatus.OK); //Usamos el segundo constructor
    }
    @GetMapping(value="/okBody")
    public ResponseEntity<String> okBody(){
        return ResponseEntity.ok(HttpStatus.OK +" Respuesta correcta "); //Usamos el segundo constructor
    }
    @GetMapping(value="/notFound")
    public ResponseEntity<String> notFound(){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND +" Respuesta: NOT FOUND ",HttpStatus.NOT_FOUND); //Usamos el segundo constructor
    }

    @GetMapping(value="/teapot")
    public ResponseEntity<String> teapot(){
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT +" Respuesta: No estoy diseñado para esto. ",HttpStatus.I_AM_A_TEAPOT); //Usamos el segundo constructor
    }

    @GetMapping(value="/header")
    public ResponseEntity<String> header(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Hello", "World!"); //key, value
        headers.add("Web", "Estamos en la web"); //key, value
        return new ResponseEntity<>(HttpStatus.OK +": Aqui tienes tus headers",headers, HttpStatus.OK);
    }
    @GetMapping(value="/headers2")
    public ResponseEntity<String> header2(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Header1", "Este es el header1");
        return ResponseEntity.ok().headers(headers).body(HttpStatus.OK + ": Mandando los headers");
    }

    @GetMapping(value="/badRequest")
    public ResponseEntity<String> badRequest(){
        return ResponseEntity.badRequest().body("Bad Request"); //Tambien podriamos pasarle un objeto  header si quisiéramos
    }

    @GetMapping(value="/notFound2")
    public ResponseEntity<String> notFound2(){
        return ResponseEntity.status(404).body("Recurso no encontrado"); //También podríamos pasarle un objeto header si quisiéramos
    }

    @GetMapping(value="/internalServerError")
    public ResponseEntity<String> internalServerError(){
        return ResponseEntity.status(500).body("Ups... Algo no ha salido bien en el servidor"); //También podríamos pasarle un objeto header si quisiéramos
    }
}
