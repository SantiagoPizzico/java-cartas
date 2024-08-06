package main.java.com.example;

//constructor superclase personaje
public class Orco extends Personaje {
    public Orco(int velocidad, int destreza, int fuerza, int nivel, int armadura, String nombre, String apodo, int edad) {
        super(velocidad, destreza, fuerza, nivel, armadura, nombre, apodo, edad);
        this.raza = "Orco";
    }
    //override con los daños de orco
    @Override
    public double calcularDanio(double valorAtaque, int efectividadDisparo, int poderDefensa) {
        return ((valorAtaque * (1 + efectividadDisparo / 100.0) - poderDefensa) / 500) * 100 * 1.1;
    }
}
