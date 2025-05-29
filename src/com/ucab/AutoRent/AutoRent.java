package com.ucab.AutoRent;

import java.util.Scanner;
import java.util.ArrayList;

public class AutoRent {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Aquí almacenaremos los vehículos (capacidad de 100 vehículos)
        ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

        while (true) {
            // Imprimimos el menú
            Utils.clearImprimirEncabezado("Menú principal");
            System.out.println("Seleccione una opción:\n");
            System.out.println("[0] Añadir nuevo vehículo");
            System.out.println("[1] Actualizar información");
            System.out.println("[2] Calcular precio de renta");
            System.out.println("[3] Gestionar retiro de vehículo");
            System.out.println("[4] Consultar información completa");
            System.out.println("[5] Salir\n");

            // Leemos la elección del usuario
            int opcion = Utils.leerEntero("> ");
            System.out.println();

            switch (opcion) {
                case 0:
                    Utils.clearImprimirEncabezado("Añadir nuevo vehículo");

                    System.out.println("¿Qué tipo de vehículo es?\n");
                    System.out.println("[0] Vehiculo genérico");
                    System.out.println("[1] Camioneta de pasajeros");
                    System.out.println("[2] Autobus turístico\n");

                    // Leemos la elección del usuario
                    opcion = Utils.leerEntero("> ");
                    System.out.println();

                    // Donde almacenaremos el nuevo objeto
                    Vehiculo vehiculoNuevo;

                    if (opcion == 0) {
                        // Creamos un vehículo genérico
                        vehiculoNuevo = new Vehiculo();
                    } else if (opcion == 1) {
                        // Creamos una camioneta
                        vehiculoNuevo = new Camioneta();
                    } else if (opcion == 2){
                        // Creamos un autobus turístico
                        vehiculoNuevo = new AutobusTuristico();
                    } else {
                        // Opción inválida ingresada
                        System.out.println("Opción inválida -- '" + opcion + "'");
                        Utils.pause();
                        break;
                    }

                    // Leemos datos del nuevo vehículo para agregarlo al arreglo
                    vehiculoNuevo.leerDatos();
                    vehiculos.add(vehiculoNuevo);
                    break;

                // Actualizar información
                case 1: {
                    // Se puede cambiar: Kilometraje, estado y placa
                    Utils.clearImprimirEncabezado("Actualizar información");

                    // Escogemos un vehículo para desplegar su información
                    Vehiculo vehiculo = Utils.escogerVehiculo(vehiculos);
                
                    // Nos aseguramos de que el vehículo pudo ser escogido
                    if (vehiculo == null) {
                        // No hay vehiculos de los cuales escoger
                        System.out.println("[*] Actualmente no hay ningún vehículo.");
                        Utils.pause();
                        break;
                    }

                    // Decidimos qué atributo cambiar
                    opcion = -1;
                    while (opcion < 0 || opcion > 3) {
                        Utils.clearImprimirEncabezado("Actualizar información");
                        System.out.println("¿Qué información desea actualizar");
                        System.out.println("[0] Kilometraje");
                        System.out.println("[1] Estado");
                        System.out.println("[2] Placa");
                        System.out.println();

                        opcion = Utils.leerEntero("> ");
                    }

                    switch (opcion) {
                        // Kilometraje
                        case 0:
                            Utils.clearImprimirEncabezado("Actualizar información");

                            int nuevoKilometraje = -1;

                            while (!vehiculo.validarKilometraje(nuevoKilometraje)) {
                                nuevoKilometraje = Utils.leerEntero("Nuevo kilometraje: ");

                                if (!vehiculo.validarKilometraje(nuevoKilometraje))
                                    System.out.println("\n[!] Kilometraje debe ser un entero >= 0.\n");
                            }

                            vehiculo.setKilometraje(nuevoKilometraje);

                            Utils.clearImprimirEncabezado("Actualizar información");
                            System.out.println("[+] Información actualizada con éxito.");
                            Utils.pause();
                            break;

                        // Estado
                        case 1:
                            Utils.clearImprimirEncabezado("Actualizar información");
                            System.out.println("Estado del vehículo:");
                            System.out.println("0: No disponible    1: Disponible    2: En renta    3: Fuera de servicio o retirado");

                            int nuevoEstado = -1;

                            while (!vehiculo.validarEstado(nuevoEstado)) {
                                nuevoEstado = Utils.leerEntero("> ");
                                
                                if (!vehiculo.validarEstado(nuevoEstado)) 
                                    System.out.println("\n[!] El estado que ingresó no es válido. Intente de nuevo.\n");
                            }

                            vehiculo.setEstado(nuevoEstado);

                            Utils.clearImprimirEncabezado("Actualizar información");
                            System.out.println("[+] Información actualizada con éxito.");
                            Utils.pause();
                            break;

                        // Placa
                        case 2:
                            Utils.clearImprimirEncabezado("Actualizar información");

                            String nuevaPlaca = "";

                            while (!vehiculo.validarPlaca(nuevaPlaca)) {
                                nuevaPlaca = Utils.leerString("Nueva placa (8 chars. alfanuméricos): ").toUpperCase();

                                if (!vehiculo.validarPlaca(nuevaPlaca))
                                    System.out.println("\n[!] La placa que ingresó no es válida. Intente de nuevo.\n");
                            }

                            vehiculo.setPlaca(nuevaPlaca);

                            Utils.clearImprimirEncabezado("Actualizar información");
                            System.out.println("[+] Información actualizada con éxito.");
                            Utils.pause();
                            break;

                        default:
                            Utils.clearImprimirEncabezado("Actualizar información");
                            System.out.println("[!] Opción inválida -- '" + opcion + "'");
                            Utils.pause();
                            break;
                    }

                    break;
                }

                case 2: {
                    Utils.clearImprimirEncabezado("Calcular precio de renta");

                    // Escogemos un vehículo para desplegar su información
                    Vehiculo vehiculo = Utils.escogerVehiculo(vehiculos);
                
                    // Nos aseguramos de que el vehículo pudo ser escogido
                    if (vehiculo == null) {
                        // No hay vehiculos de los cuales escoger
                        System.out.println("[*] Actualmente no hay ningún vehículo.");
                        Utils.pause();
                        break;
                    }

                    Utils.clearImprimirEncabezado("Calcular precio de renta");

                    int porcentajeGanancia = Utils.leerEntero("¿Qué porcentaje de ganancia sobre el precio de adiquisión desea? ");

                    if (Utils.leerString("Desea agregar una tarifa adicional? [y/N] ").toLowerCase().equals("y")) {
                        int tarifaAdicional = Utils.leerEntero("Ingrese tarifa adicional (entero): ");
                        vehiculo.calcularPrecioDeRenta(porcentajeGanancia, tarifaAdicional);
                    } else {
                        vehiculo.calcularPrecioDeRenta(porcentajeGanancia);
                    }

                    Utils.clearImprimirEncabezado("Calcular precio de renta");
                    System.out.println("[+] El precio de renta ha sido cambiado a $" + vehiculo.getPrecioDeRenta());
                    Utils.pause();
                    break;
                }

                // Gestionar retiro
                case 3: {
                    Utils.clearImprimirEncabezado("Gestionar retiro");

                    // Escogemos un vehículo para desplegar su información
                    Vehiculo vehiculo = Utils.escogerVehiculo(vehiculos);
                
                    // Nos aseguramos de que el vehículo pudo ser escogido
                    if (vehiculo == null) {
                        // No hay vehiculos de los cuales escoger
                        System.out.println("[*] Actualmente no hay ningún vehículo.");
                        Utils.pause();
                        break;
                    }

                    Utils.clearImprimirEncabezado("Gestionar retiro");
                    if (vehiculo.retirarVehiculo()) {
                        System.out.println("\n[-] El vehículo ha sido automáticamente retirado para mantenimiento.");
                    } else {
                        System.out.println("\n[*] El vehículo se encuentra en condiciones aptas. No necesita ser retirado.");
                    }

                    Utils.pause();
                    break;
                }

                case 4: {
                    Utils.clearImprimirEncabezado("Imprimir información completa");

                    // Escogemos un vehículo para desplegar su información
                    Vehiculo vehiculo = Utils.escogerVehiculo(vehiculos);
                
                    // Nos aseguramos de que el vehículo pudo ser escogido
                    if (vehiculo == null) {
                        // No hay vehiculos de los cuales escoger
                        System.out.println("[*] Actualmente no hay ningún vehículo.");
                        Utils.pause();
                        break;
                    }

                    // Mostramos la información del vehículo escogido
                    System.out.println();
                    vehiculo.mostrarInformacion();
                    System.out.println();

                    Utils.pause();
                    break;
                }

                case 5:
                    Utils.clear();
                    System.out.println("Gracias por usar. Hasta luego (:");
                    return;

                default:
                    System.out.println("\n[!] La opción ingresada no es válida. Ingrese de nuevo.\n");
                    break;
            }
        }
    }
}
