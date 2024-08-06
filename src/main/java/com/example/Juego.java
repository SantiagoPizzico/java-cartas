package main.java.com.example;
import java.io.*;
import java.util.*;

public class Juego {
    private Partida partida;
    private Scanner scanner;

    private static final Map<Raza, List<String>> NOMBRES = new EnumMap<>(Raza.class);
    private static final Map<Raza, List<String>> APODOS = new EnumMap<>(Raza.class);

    static {
        NOMBRES.put(Raza.HUMANO, Arrays.asList("Juan", "María", "Pedro", "Ana", "Luis", "Isabel", "Carlos", "Sofía"));
        NOMBRES.put(Raza.ELFO, Arrays.asList("Legolas", "Arwen", "Galadriel", "Elrond", "Thranduil", "Eowyn", "Frodo", "Tauriel"));
        NOMBRES.put(Raza.ORCO, Arrays.asList("Grishnak", "Lurtz", "Azog", "Bolg", "Grom", "Ugluk", "Dushnak", "Muzgash"));

        APODOS.put(Raza.HUMANO, Arrays.asList("El Valiente", "La Sabia", "El Fuerte", "La Astuta", "El Audaz", "La Justa", "El Protector", "La Diplomática"));
        APODOS.put(Raza.ELFO, Arrays.asList("El Arquero", "La Doncella", "La Señora de la Luz", "El Semielfo", "El Guardián del Bosque", "La Intrépida", "El Portador del Anillo", "La Protectora del Bosque"));
        APODOS.put(Raza.ORCO, Arrays.asList("El Sanguinario", "El Cazador", "El Profanador", "El Inmundo", "El Destructor", "El Astuto", "El Despiadado", "El Devorador"));
    }

    public Juego() {
        scanner = new Scanner(System.in);
    }

    public void iniciarPartidaAleatoria() {
        partida = new Partida();
        Jugador jugador1 = partida.getJugador1();
        Jugador jugador2 = partida.getJugador2();

        boolean cartasAceptadas = false;
        while (!cartasAceptadas) {
            jugador1.limpiarPersonajes();
            jugador2.limpiarPersonajes();

            generarPersonajesAleatorios(jugador1);
            generarPersonajesAleatorios(jugador2);

            System.out.println("- Se generaron 6 personajes:");
            imprimirPersonajesGenerados(jugador1, 1);
            imprimirPersonajesGenerados(jugador2, 2);

            System.out.println("Presione Enter para continuar o escriba 'R' para rehacer las cartas:");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.isEmpty()) {
                cartasAceptadas = true;
            } else if (input.equals("R")) {
                System.out.println("Regenerando cartas...");
            } else {
                System.out.println("Opción no válida. Por favor, presione Enter para continuar o escriba 'R' para rehacer las cartas.");
            }
        }

        partida.iniciarPartida();
    }

    public void iniciarPartidaManual() {
        partida = new Partida();
        ingresarPersonajesManualmente(partida.getJugador1());
        ingresarPersonajesManualmente(partida.getJugador2());
        partida.iniciarPartida();
    }

    private void generarPersonajesAleatorios(Jugador jugador) {
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            Raza raza = Raza.values()[random.nextInt(Raza.values().length)];
            int velocidad = random.nextInt(10) + 1;
            int destreza = random.nextInt(5) + 1;
            int fuerza = random.nextInt(10) + 1;
            int nivel = random.nextInt(10) + 1;
            int armadura = random.nextInt(10) + 1;
            String nombre = NOMBRES.get(raza).get(random.nextInt(NOMBRES.get(raza).size()));
            String apodo = APODOS.get(raza).get(random.nextInt(APODOS.get(raza).size()));
            int edad = random.nextInt(300);

            Personaje personaje = PersonajeFactory.crearPersonaje(raza, velocidad, destreza, fuerza, nivel, armadura, nombre, apodo, edad);
            jugador.agregarPersonaje(personaje);
        }
    }

    private void imprimirPersonajesGenerados(Jugador jugador, int numJugador) {
        System.out.println("---- Cartas del Jugador " + numJugador + " ----");
        for (int i = 0; i < jugador.getPersonajes().size(); i++) {
            Personaje personaje = jugador.getPersonajes().get(i);
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
            System.out.println("========== CARTA " + (i + 1) + " ==========");
            System.out.println(infoPersonaje);
            System.out.println("=============================");
        }
    }

    private void ingresarPersonajesManualmente(Jugador jugador) {
        for (int i = 0; i < 3; i++) {
            System.out.println("Ingrese los datos del personaje " + (i + 1) + ":");
            Raza raza = solicitarRaza();
            String nombre = solicitarInput("Nombre");
            String apodo = solicitarInput("Apodo");
            int velocidad = solicitarEntero("Velocidad", 1, 10);
            int destreza = solicitarEntero("Destreza", 1, 5);
            int fuerza = solicitarEntero("Fuerza", 1, 10);
            int nivel = solicitarEntero("Nivel", 1, 10);
            int armadura = solicitarEntero("Armadura", 1, 10);
            int edad = solicitarEntero("Edad", 0, 300);

            Personaje personaje = PersonajeFactory.crearPersonaje(raza, velocidad, destreza, fuerza, nivel, armadura, nombre, apodo, edad);
            jugador.agregarPersonaje(personaje);
        }
    }

    private Raza solicitarRaza() {
        while (true) {
            System.out.print("Raza (HUMANO, ELFO, ORCO): ");
            try {
                return Raza.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Raza inválida. Por favor, ingrese HUMANO, ELFO o ORCO.");
            }
        }
    }

    private String solicitarInput(String campo) {
        System.out.print(campo + ": ");
        return scanner.nextLine().trim();
    }

    private int solicitarEntero(String campo, int min, int max) {
        while (true) {
            System.out.print(campo + " (" + min + "-" + max + "): ");
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor >= min && valor <= max) {
                    return valor;
                } else {
                    System.out.println("El valor debe estar entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }

    public void leerLogs() {
        try (BufferedReader reader = new BufferedReader(new FileReader("logs.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de logs: " + e.getMessage());
        }
    }

    public void borrarLogs() {
        File archivo = new File("logs.txt");
        if (archivo.delete()) {
            System.out.println("Archivo de logs borrado correctamente.");
        } else {
            System.out.println("No se pudo borrar el archivo de logs o no existe.");
        }
    }

    public void ejecutarMenu() {
        int opcion;
        do {
            System.out.println("\n---- Menú ----");
            System.out.println("1. Iniciar partida con personajes aleatorios");
            System.out.println("2. Iniciar partida con personajes ingresados manualmente");
            System.out.println("3. Leer logs de partidas jugadas");
            System.out.println("4. Borrar archivo de logs");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = solicitarEntero("Opción", 1, 5);

            switch (opcion) {
                case 1:
                    iniciarPartidaAleatoria();
                    break;
                case 2:
                    iniciarPartidaManual();
                    break;
                case 3:
                    leerLogs();
                    break;
                case 4:
                    borrarLogs();
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    break;
            }
        } while (opcion != 5);
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.ejecutarMenu();
    }
}

enum Raza {
    HUMANO, ELFO, ORCO
}

class PersonajeFactory {
    public static Personaje crearPersonaje(Raza raza, int velocidad, int destreza, int fuerza, int nivel, int armadura, String nombre, String apodo, int edad) {
        switch (raza) {
            case HUMANO:
                return new Humano(velocidad, destreza, fuerza, nivel, armadura, nombre, apodo, edad);
            case ELFO:
                return new Elfo(velocidad, destreza, fuerza, nivel, armadura, nombre, apodo, edad);
            case ORCO:
                return new Orco(velocidad, destreza, fuerza, nivel, armadura, nombre, apodo, edad);
            default:
                throw new IllegalArgumentException("Raza inválida: " + raza);
        }
    }
}