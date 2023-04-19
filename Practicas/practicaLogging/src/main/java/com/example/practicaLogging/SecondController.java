package com.example.practicaLogging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SecondController {

    @RequestMapping("/mapping")
    public String index() {
        log.trace("2. Mensaje a nivel de TRACE");
        log.debug("2. Mensaje a nivel de DEBUG");
        log.info("2. Mensaje a nivel de INFO");
        log.warn("2. Mensaje a nivel de WARNING");
        log.error("2. Mensaje a nivel de ERROR");

        return "Hola! Mira los logs para ver los resultados";
    }

}
