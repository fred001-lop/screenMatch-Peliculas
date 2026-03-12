package com.aluracursos.screenmatch;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.principal.EjemploStreams;
import com.aluracursos.screenmatch.principal.Principal;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
////        System.out.println("Hola mundo desde Spring");
//        var consumoApi = new ConsumoAPI();
//        var json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=Game+of+Thrones&apikey=c94ace96");
////        var json = consumoAPI.obtenerDatos("https://coffee.alexflipnote.dev/random.json");
//        System.out.println(json);
//        ConvierteDatos conversor = new ConvierteDatos();
//        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
//        System.out.println(datos);
//        json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=Game+of+Thrones&Season=1&episode=1&apikey=c94ace96");
//        DatosEpisodio episodios = conversor.obtenerDatos(json, DatosEpisodio.class);
//        System.out.println(episodios);
//
//        //Busca los datos de todas las temporadas
//
//        List<DatosTemporadas> temporadas = new ArrayList<>();
//        for (int i = 1; i <= datos.totalTemporada() ; i++) {
//            json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=Game+of+Thrones&Season="+i+"&apikey=c94ace96");
//            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
//            temporadas.add(datosTemporadas);
//        }
//        temporadas.forEach(System.out::println);
        Principal principal = new Principal();
        principal.muestraElMennu();
//        EjemploStreams ejemploStreams = new EjemploStreams();
//        ejemploStreams.muestraEjemplo();
    }
}
