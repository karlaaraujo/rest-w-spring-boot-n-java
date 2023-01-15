package br.com.karla.greetingapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

// retorna apenas a resposta, como JSON ou XML, via HTTP
@RestController
public class GreetingController {


    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong(); // Mocka ids

    @RequestMapping("/greeting") // mapeia requisição no endpoint para o método
    public Greeting greeting(
            @RequestParam(value = "name", defaultValue = "World")
            String name
    ) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
