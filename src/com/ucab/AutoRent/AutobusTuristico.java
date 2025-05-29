package com.ucab.AutoRent;

import java.util.Scanner;

class AutobusTuristico extends Vehiculo {
    private int capacidadDePasajeros;
    private String agenciaTuristica;

    private Scanner scanner = new Scanner(System.in);

    // Constructor genérico
    AutobusTuristico() {
        super();
        this.capacidadDePasajeros = 0;
        this.agenciaTuristica = "";
    }

    AutobusTuristico(
        int codigo,
        String placa,
        String marca,
        int ano,
        float costoDeAdquisicion,
        float precioDeRenta,
        float kilometraje,
        int estado,
        int capacidadDePasajeros,
        String agenciaTuristica
    ) {
        // Invoca al constructor de la clase padre
        super(codigo, placa, marca, ano, costoDeAdquisicion, precioDeRenta, kilometraje, estado);

        // Inicializa los nuevos atributos
        this.capacidadDePasajeros = capacidadDePasajeros;
        this.agenciaTuristica = agenciaTuristica;
    }

    public String getAgenciaturistica() {
        return this.agenciaTuristica;
    }

    public void setAgenciaTuristica(String agenciaTuristica) {
        if (agenciaTuristica.length() > 0)
            this.agenciaTuristica = agenciaTuristica;
    }

    public int getCapacidadDePasajeros() {
        return this.capacidadDePasajeros;
    }

    public void setCapacidadDePasajeros(int capacidadDePasajeros) {
        if (capacidadDePasajeros > 0)
            this.capacidadDePasajeros = capacidadDePasajeros;
    }

    public boolean validarAgenciaTuristica() {
        return this.agenciaTuristica.length() > 0;
    }

    public boolean validarCapacidadDePasajeros() {
        return this.capacidadDePasajeros > 0;
    }

    // Para el Autobus de Pasajeros, al precio de renta se le suma
    // una tarifa de 1% más por cada pasajero que pueda retener
    @Override public float calcularPrecioDeRenta(int porcentaje) {
        setPrecioDeRenta(super.calcularPrecioDeRenta(porcentaje) * (1 + (this.capacidadDePasajeros / 100)));
        return getPrecioDeRenta();
    }

    @Override public float calcularPrecioDeRenta(int porcentaje, int cargosAdicionales) {
        setPrecioDeRenta(super.calcularPrecioDeRenta(porcentaje, cargosAdicionales) * (1 + (this.capacidadDePasajeros / 100)));
        return getPrecioDeRenta();
    }

    @Override public void mostrarInformacion() {
        // Mostramos los elementos de la clase padre
        super.mostrarInformacion();

        // mostramos los elementos adicionales
        System.out.println("Agencia Turística: " + this.agenciaTuristica);
        System.out.println("Capacidad de pasajeros: " + this.capacidadDePasajeros);
    }

    @Override public void leerDatos() {
        super.leerDatos();

        // Leemos los datos adicionales
        while (!validarAgenciaTuristica()) {
            System.out.print("Agencia Turistica: ");
            setAgenciaTuristica(scanner.nextLine());

            if (!validarAgenciaTuristica())
                System.out.println("[!] La agencia turística que ingresó no es válida. Intente de nuevo.");
        }

        while (!validarCapacidadDePasajeros()) {
            System.out.print("Capacidad de pasajeros: ");
            setCapacidadDePasajeros(Utils.leerEntero());

            if (!validarCapacidadDePasajeros())
                System.out.println("[!] La capacidad de pasajeros que ingresó no es válida. Intente de nuevo.");
        }
    }
}

