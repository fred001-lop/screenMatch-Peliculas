package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;



public class Principal {


    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=c94ace96";
    private ConvierteDatos conversor = new ConvierteDatos();


    public void muestraElMennu() {
        System.out.println("Escribe el nombre de la serie que deseas buscar");
        //BUSCA L
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        //Busca los datos de todas las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <= datos.totalTemporada(); i++) {
            json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
//        temporadas.forEach(System.out::println);

        //MOSTRAR SOLO EL TITULO DE LOS EPISODIOS PARA LAS TEMPORADAS
//        for (int i = 0; i < datos.totalTemporada(); i++) {
//            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporada.size(); j++) {
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }

//            temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        //Convertir todas las informaciones a una lista del tipo DatosEpisodio
        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        //Top 5 episodios
        //Obtener los top 5 episodios
        System.out.println("Top 5 mejores episodios");
        datosEpisodios.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primer Filtro N/A" + e))
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                .peek(e -> System.out.println("Segunda ordenacion (M>m)" +e))
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("Tercer Filtro Mayuscula (m>M)" +e))
                .limit(5)
                .forEach(System.out::println);

        //Convirtiendo los datos de la lista del tipo Episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(),d)))
                .collect(Collectors.toList());
        //episodios.forEach(System.out::println);

        //Busqueda de episodios apartir de x ano
        System.out.println("Por favor indica el año a partir del cual deseas ver el episodio");
        var fecha = teclado.nextInt();
        teclado.nextLine();
        LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodios.stream()
//                .filter(e -> e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda))
//                .forEach(e -> System.out.println(
//                        "Temporada " + e.getTemporada()  +
//                                "Episodio " + e.getTitulo() +
//                                "Fecha de Lanzamiento  " + e.getFechaDeLanzamiento().format(dtf)
//                ));



        //Busca episodios por pedazo del titulo
        System.out.println("Escriba el titulo del episodio que desea ver");
        var pedazoTitulo = teclado.nextLine();
        episodios.stream()
                .filter(e -> e.getTitulo().contains(pedazoTitulo))
                .findFirst();
    }


}
