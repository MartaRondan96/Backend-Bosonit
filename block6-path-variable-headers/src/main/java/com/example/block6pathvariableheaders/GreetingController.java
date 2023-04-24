package com.example.block6pathvariableheaders;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    //Petición POST: mandando un objeto JSON en el body y recibiendo ese mismo objeto JSON en la respuesta (en el body)
    @PostMapping("/greeting")
    public String greeting1(@RequestBody String name){
        return name;
    }

    //Petición GET: mandando parámetros en el path (http://localhost:8080/user/{id}) y devolver el mismo id mandado
    @GetMapping("user/{id}")
    public long userId(@PathVariable(value = "id") long id){
        return id;
    }
    //Petición PUT: mandando  Request Params (http://localhost:8080/post?var1=1&var2=2) devolver un HashMap con los
    //datos mandados . Por ejemplo: [ {var1: 1}, {var2: 2} ]
    @PutMapping("post/")
    public Map<String, String> insertar (@RequestParam(name = "var1", required = true) String var1,
                                         @RequestParam(name = "var2", required = true) String var2){
        Map<String, String> map = new HashMap<>();
        map.put("var1", var1);
        map.put("var2", var2);
        return map;
    }
}