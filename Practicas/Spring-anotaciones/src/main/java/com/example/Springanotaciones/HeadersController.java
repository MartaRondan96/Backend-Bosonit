package com.example.Springanotaciones;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/headers")
public class HeadersController {
    @GetMapping(value="/language")
    public String header(@RequestHeader("accept-language") String language) {
        return language;
    }
    @GetMapping(value = "/requered")
    public String header1(@RequestHeader(value="accept-language", required = false) String mapa) {
        return mapa;
    }

    @GetMapping(value = "/default")
    public String header2(@RequestHeader(value="accept-language", defaultValue="en-US") String mapa) {
        return mapa;
    }

}
