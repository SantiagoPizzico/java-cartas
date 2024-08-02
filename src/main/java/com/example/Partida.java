package main.java.com.example;
import java.util.List;
import java.util.Random;

public class Partida {
    private Jugador jugador1;
    private Jugador jugador2;
    private Logger logger;

    public Partida() {
        jugador1 = new Jugador("Jugador 1");
        jugador2 = new Jugador("Jugador 2");
        logger = new Logger("logs.txt");
    }

    public void iniciarPartida() {
        Random random = new Random();
        boolean turnoJugador1 = random.nextBoolean();

        while (!juegoTerminado()) {
            Personaje personajeAtacante;
            Personaje personajeDefensor;

            if (turnoJugador1) {
                personajeAtacante = seleccionarPersonajeAleatorio(jugador1);
                personajeDefensor = seleccionarPersonajeAleatorio(jugador2);
            } else {
                personajeAtacante = seleccionarPersonajeAleatorio(jugador2);
                personajeDefensor = seleccionarPersonajeAleatorio(jugador1);
            }

            combatir(personajeAtacante, personajeDefensor);
            turnoJugador1 = !turnoJugador1;
        }

        determinarGanador();
    }

    private Personaje seleccionarPersonajeAleatorio(Jugador jugador) {
        List<Personaje> personajes = jugador.getPersonajes();
        return personajes.get(new Random().nextInt(personajes.size()));
    }

    private void combatir(Personaje atacante, Personaje defensor) {
        for (int i = 0; i < 7; i++) {
            double danio = atacante.atacar(defensor);
            String mensaje = String.format("===============================================\n" +
                            "%s ataca a %s y le quita %.2f de salud.\n" +
                            "===============================================\n" +
                            "%s queda con %d de salud.",
                    atacante.getNombre(), defensor.getNombre(), danio,
                    defensor.getNombre(), defensor.getSalud());
            logger.log(mensaje);


            if (defensor.getSalud() <= 0) {
                String muerteMsg = String.format("X X X X X X %s ha muerto. X X X X X X", defensor.getNombre());
                logger.log(muerteMsg);
                eliminarPersonaje(defensor);
                break;
            }

            // Alternando los turnos de ataque
            Personaje temp = atacante;
            atacante = defensor;
            defensor = temp;
        }
    }

    private boolean juegoTerminado() {
        return jugador1.getPersonajes().isEmpty() || jugador2.getPersonajes().isEmpty();
    }

    private void determinarGanador() {
        Jugador ganador;
        Jugador perdedor;
        if (jugador1.getPersonajes().isEmpty()) {
            ganador = jugador2;
            perdedor = jugador1;
        } else {
            ganador = jugador1;
            perdedor = jugador2;
        }

        String mensajeGanador = String.format("¡%s es el ganador del Trono de Hierro!", ganador.getNombre());
        logger.log(mensajeGanador);

        imprimirPersonajesVivos(ganador.getPersonajes());
    }

    private void imprimirPersonajesVivos(List<Personaje> personajes) {
        logger.log("Personajes vivos:");
        for (Personaje personaje : personajes) {
            String infoPersonaje = String.format(
                    "Nombre: %s\n" +
                            "Raza: %s\n" +
                            "Apodo: %s\n" +
                            "Velocidad: %d\n" +
                            "Destreza: %d\n" +
                            "Fuerza: %d\n" +
                            "Nivel: %d\n" +
                            "Armadura: %d\n" +
                            "Edad: %d\n" +
                            "Salud: %d\n",
                    personaje.getNombre(), personaje.getRaza(), personaje.getApodo(),
                    personaje.getVelocidad(), personaje.getDestreza(), personaje.getFuerza(),
                    personaje.getNivel(), personaje.getArmadura(), personaje.getEdad(),
                    personaje.getSalud()
            );
            logger.log(infoPersonaje);
        }
    }

    private void eliminarPersonaje(Personaje personaje) {
        if (jugador1.getPersonajes().contains(personaje)) {
            jugador1.eliminarPersonaje(personaje);
        } else {
            jugador2.eliminarPersonaje(personaje);
        }
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }
}

class Logger {
    private String nombreArchivo;

    public Logger(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void log(String mensaje) {
        try (java.io.FileWriter writer = new java.io.FileWriter(nombreArchivo, true)) {
            writer.write(mensaje + System.lineSeparator());
            System.out.println(mensaje);
        } catch (java.io.IOException e) {
            System.err.println("Error al escribir en el archivo de logs: " + e.getMessage());
        }
    }
}