package main.java.com.example;
import java.util.Date;
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
    private Date fechaNacimiento;
    private int edad;
    protected int salud;
    private String imagen;

    public Personaje(int velocidad, int destreza, int fuerza, int nivel, int armadura, String nombre, String apodo, Date fechaNacimiento, int edad, String imagen) {
        this.velocidad = velocidad;
        this.destreza = destreza;
        this.fuerza = fuerza;
        this.nivel = nivel;
        this.armadura = armadura;
        this.nombre = nombre;
        this.apodo = apodo;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.imagen = imagen;
        this.salud = SALUD_INICIAL;
    }

    public abstract double calcularDanio(double valorAtaque, int efectividadDisparo, int poderDefensa);

    public double atacar(Personaje defensor) {
        Random random = new Random();
        int poderDisparo = destreza * fuerza * nivel;
        int efectividadDisparo = random.nextInt(10) + 1;
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

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "nombre='" + nombre + '\'' +
                ", raza='" + raza + '\'' +
                ", nivel=" + nivel +
                ", salud=" + salud +
                '}';
    }
}