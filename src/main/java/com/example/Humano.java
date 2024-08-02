package main.java.com.example;
import java.util.Date;

public class Humano extends Personaje {
    public Humano(int velocidad, int destreza, int fuerza, int nivel, int armadura, String nombre, String apodo, Date fechaNacimiento, int edad, String imagen) {
        super(velocidad, destreza, fuerza, nivel, armadura, nombre, apodo, fechaNacimiento, edad, imagen);
        this.raza = "Humano";
    }

    @Override
    public double calcularDanio(double valorAtaque, int efectividadDisparo, int poderDefensa) {
        return ((valorAtaque * (1 + efectividadDisparo / 100.0) - poderDefensa) / 500) * 100;
    }
}