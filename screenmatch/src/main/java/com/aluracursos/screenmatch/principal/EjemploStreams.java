package com.aluracursos.screenmatch.principal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class EjemploStreams {
    public void muestraEjemplo(){
        List<String> nombres = Arrays.asList("Fredy", "Erick", "Gerardo", "Kenia", "Genesis");
        nombres.stream()
                .sorted()
                .limit(4)
                .filter(n -> n.startsWith("G"))
                .map(n -> n.toUpperCase())
                .forEach(System.out::println);
    }
}
