package main.java.com.example;
import java.util.Random;

public abstract class Personaje {
    public static final int SALUD_INICIAL = 100;

    private int velocidad;
    private int destreza;
    private int fuerza;
    private int nivel;
    private int armadura;
    protected String raza;
    private String nombre;
    private String apodo;
    private int edad;
    protected int salud;

    public Personaje(int velocidad, int destreza, int fuerza, int nivel, int armadura, String nombre, String apodo, int edad) {
        this.velocidad = velocidad;
        this.destreza = destreza;
        this.fuerza = fuerza;
        this.nivel = nivel;
        this.armadura = armadura;
        this.nombre = nombre;
        this.apodo = apodo;
        this.edad = edad;
        this.salud = SALUD_INICIAL;
    }

    public abstract double calcularDanio(double valorAtaque, int efectividadDisparo, int poderDefensa);

    public double atacar(Personaje defensor) {
        Random random = new Random();
        int poderDisparo = destreza * fuerza * nivel;
        int efectividadDisparo = random.nextInt(100) + 1;
        double valorAtaque = poderDisparo * (1 + efectividadDisparo / 100.0);
        int poderDefensa = defensor.getArmadura() * defensor.getVelocidad();
        double danio = calcularDanio(valorAtaque, efectividadDisparo, poderDefensa);
        defensor.recibirAtaque(danio);
        return danio;
    }

    public void recibirAtaque(double danio) {
        if (danio < 0) {
            danio = 0;
        }
        salud -= danio;
        if (salud < 0) {
            salud = 0;
        }
    }

    // Getters y setters
    public int getVelocidad() {
        return velocidad;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getNivel() {
        return nivel;
    }

    public int getArmadura() {
        return armadura;
    }

    public String getRaza() {
        return raza;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public int getEdad() {
        return edad;
    }

    public int getSalud() {
        return salud;
    }
}