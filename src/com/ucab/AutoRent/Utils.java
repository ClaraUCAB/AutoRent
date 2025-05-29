package com.ucab.AutoRent;

import java.util.Scanner;
import java.util.ArrayList;

public class Utils {
    // I/O
    private static Scanner scanner = new Scanner(System.in);

    // Pausa la ejecución hasta que el usuario presiona ENTER
    public static void pause() {
        System.out.print("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }

    // Limpia la pantalla
    public static void clear() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    // Imprime el título del programa junto con un subtítulo.
    // Este método por default limpia la pantalla antes de imprimir
    public static void clearImprimirEncabezado(String subtitulo) {
        clear();
        System.out.println("= AutoRent =");
        System.out.println("- " + subtitulo + " -\n");
    }

    // Imprime el título del programa junto con un subtítulo.
    // Este método por default NO limpia la pantalla antes de imprimir
    public static void imprimirEncabezado(String subtitulo) {
        System.out.println("= AutoRent =");
        System.out.println("- " + subtitulo + " -\n");
    }

    // Escoge de manera interactiva un vehículo del arreglo.
    // Devuelve un vehículo del arreglo, o null si está vacío.
    public static Vehiculo escogerVehiculo(ArrayList<Vehiculo> vehiculos) {
        // Comprobamos que hay vehiculos añadidos
        if (vehiculos.size() == 0) {
            // No hay vehiculos de los cuales escoger
            return null;
        }

        System.out.println("Seleccione un vehiculo para imprimir información:\n");
        for (int i = 0; i < vehiculos.size(); i++) {
            String marca = vehiculos.get(i).getMarca();
            String placa = vehiculos.get(i).getPlaca();
            System.out.println("[" + i + "] " + marca + " con placa " + placa);
        }

        int opcion = -1;
        while (!(opcion < vehiculos.size() && opcion >= 0)) {
            opcion = Utils.leerEntero("\n> ");
            
            if (opcion < 0 || opcion >= vehiculos.size())
                System.out.println("\n[!] Opción inválida. Intente de nuevo.");
        }

        // Devuelve el vehículo escogido
        return vehiculos.get(opcion);
    }

    // Esta función continuará intentando leer un entero hasta que sea válido
    public static int leerEntero(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int entero = scanner.nextInt();
                scanner.nextLine();
                return entero;
            }

            catch (Exception e) {
                scanner.nextLine(); // Consumimos la línea basura del string
                System.out.println("\n[!] Por favor, ingrese un entero válido.\n");
            }
        }
    }

    public static int leerEntero() {
        while (true) {
            try {
                int entero = scanner.nextInt();
                scanner.nextLine();
                return entero;
            }

            catch (Exception e) {
                // scanner.nextLine(); // Consumimos la línea basura del string
                System.out.println("\n[!] Por favor, ingrese un entero válido.\n");
            }
        }
    }

    public static String leerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static boolean esAlfanumerico(String string){
        String pattern = "^[a-zA-Z0-9]*$";
        return string.matches(pattern);
    }
}
