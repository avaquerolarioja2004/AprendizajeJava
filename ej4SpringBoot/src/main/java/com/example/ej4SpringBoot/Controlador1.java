package com.example.ej4SpringBoot;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controlador1 {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @PostMapping("/postMapping")
    public Greeting postMapping(@RequestBody Greeting greeting) {
        return new Greeting(greeting.getId(), greeting.getNombre());
    }
    @GetMapping("/user/{id}")
    public Greeting greeting(@PathVariable("id") long id) {
        return new Greeting(id, "nombre");
    }

    @PutMapping("/post")
    public HashMap<Long, Long> putMapping(@RequestParam("var1") long var1, @RequestParam("var2") long var2) {
        return new HashMap<Long, Long>() {{
            put(var1, var2);
        }};
    }

}
