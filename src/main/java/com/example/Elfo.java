package main.java.com.example;
import java.util.Date;
public class Elfo extends Personaje {
    public Elfo(int velocidad, int destreza, int fuerza, int nivel, int armadura, String nombre, String apodo, Date fechaNacimiento, int edad, String imagen) {
        super(velocidad, destreza, fuerza, nivel, armadura, nombre, apodo, fechaNacimiento, edad, imagen);
        this.raza = "Elfo";
    }

    @Override
    public double calcularDanio(double valorAtaque, int efectividadDisparo, int poderDefensa) {
        return ((((valorAtaque * (1 + efectividadDisparo / 100.0)) - poderDefensa) / 500) * 100) * 1.05;
    }
}
// •Daño provocado:
// si ataca un humano : ((VA*ED)-PDEF)/500)*100
// si ataca un elfo : ( ((VA*ED)-PDEF)/500)*100 ) * 1.05
// si ataca un orco : ( ((VA*ED)-PDEF)/500)*100 ) * 1.1