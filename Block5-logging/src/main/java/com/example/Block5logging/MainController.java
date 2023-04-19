package com.example.Block5logging;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    Logger log = LoggerFactory.getLogger(MainController.class);
    @RequestMapping("/")
    public String logs(){
        log.trace("Mensaje TRACE");
        log.debug("Mensaje DEBUG");
        log.info("Mensaje INFO");
        log.warn("Mensaje WARNING");
        log.error("Mensaje ERROR");

        return "Ejercicio bloque 5 logging. Mira los logs";
    }
}
