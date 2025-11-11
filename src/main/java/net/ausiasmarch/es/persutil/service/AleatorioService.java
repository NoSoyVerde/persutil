package net.ausiasmarch.es.persutil.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class AleatorioService {

    private final ArrayList<String> palabras = new ArrayList<>();
    private final ArrayList<String> titulos = new ArrayList<>();
    private final ArrayList<String> etiquetas = new ArrayList<>();

    public AleatorioService() {
        inicializarPalabras();
        inicializarTitulos();
        inicializarEtiquetas();
    }

    private void inicializarPalabras() {
        palabras.add("el");
        palabras.add("gato");
        palabras.add("perro");
        palabras.add("casa");
        palabras.add("árbol");
        palabras.add("mar");
        palabras.add("sol");
        palabras.add("luna");
        palabras.add("estrella");
        palabras.add("montaña");
        palabras.add("río");
        palabras.add("flor");
        palabras.add("viento");
        palabras.add("fuego");
        palabras.add("agua");
        palabras.add("tierra");
        palabras.add("cielo");
        palabras.add("nube");
        palabras.add("jardín");
        palabras.add("camino");
        palabras.add("puerta");
        palabras.add("ventana");
        palabras.add("libro");
        palabras.add("música");
        palabras.add("amor");
    }

    private void inicializarTitulos() {
        titulos.add("Historia Increíble");
        titulos.add("Aventura Épica");
        titulos.add("Misterio Fascinante");
        titulos.add("Viaje Extraordinario");
        titulos.add("Descubrimiento Asombroso");
        titulos.add("Relato Mágico");
        titulos.add("Experiencia Única");
        titulos.add("Momento Especial");
        titulos.add("Reflexión Profunda");
        titulos.add("Inspiración Diaria");
    }

    private void inicializarEtiquetas() {
        etiquetas.add("aventura");
        etiquetas.add("inspiración");
        etiquetas.add("naturaleza");
        etiquetas.add("reflexión");
        etiquetas.add("misterio");
        etiquetas.add("viaje");
        etiquetas.add("descubrimiento");
        etiquetas.add("magia");
        etiquetas.add("experiencia");
        etiquetas.add("momento");
    }

    public int generarNumeroAleatorio(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public String generarTituloAleatorio() {
        int indice = generarNumeroAleatorio(0, titulos.size() - 1);
        return titulos.get(indice);
    }

    public String generarFraseAleatoria() {
        ArrayList<String> palabrasMezcladas = new ArrayList<>(palabras);
        Collections.shuffle(palabrasMezcladas);
        StringBuilder frase = new StringBuilder();
        
        for (int i = 0; i < 5 && i < palabrasMezcladas.size(); i++) {
            if (i > 0) {
                frase.append(" ");
            }
            frase.append(palabrasMezcladas.get(i));
        }
        String fraseCompleta = frase.toString();
        if (!fraseCompleta.isEmpty()) {
            fraseCompleta = fraseCompleta.substring(0, 1).toUpperCase() + 
                           fraseCompleta.substring(1) + ".";
        }
        
        return fraseCompleta;
    }

    public String generarEtiquetasAleatorias() {
        ArrayList<String> etiquetasDisponibles = new ArrayList<>(etiquetas);
        Collections.shuffle(etiquetasDisponibles);
        int numEtiquetas = generarNumeroAleatorio(1, 3);
        ArrayList<String> etiquetasSeleccionadas = new ArrayList<>();
        
        for (int i = 0; i < numEtiquetas && i < etiquetasDisponibles.size(); i++) {
            etiquetasSeleccionadas.add(etiquetasDisponibles.get(i));
        }
        
        return String.join(", ", etiquetasSeleccionadas);
    }
}
