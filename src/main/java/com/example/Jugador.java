package main.java.com.example;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private List<Personaje> personajes;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.personajes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Personaje> getPersonajes() {
        return new ArrayList<>(personajes);  // Retorna una copia de la lista para encapsulación
    }

    public void agregarPersonaje(Personaje personaje) {
        personajes.add(personaje);
    }

    public void eliminarPersonaje(Personaje personaje) {
        personajes.remove(personaje);
    }

    public void limpiarPersonajes() {
        personajes.clear();
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", personajes=" + personajes +
                '}';
    }
}
