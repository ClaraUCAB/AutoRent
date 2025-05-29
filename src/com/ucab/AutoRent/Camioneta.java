package com.ucab.AutoRent;

import java.util.Scanner;

class Camioneta extends Vehiculo {
    private int numeroDePuertas;
    private int capacidadDePasajeros;

    private Scanner scanner = new Scanner(System.in);

    // Constructor genérico
    Camioneta() {
        super();
        this.numeroDePuertas = 0;
        this.capacidadDePasajeros = 0;
    }

    Camioneta(
        int codigo,
        String placa,
        String marca,
        int ano,
        int costoDeAdquisicion,
        int precioDeRenta,
        int kilometraje,
        int estado,
        int numeroDePuertas,
        int capacidadDePasajeros
    ) {
        // Invoca al constructor de la clase padre
        super(codigo, placa, marca, ano, costoDeAdquisicion, precioDeRenta, kilometraje, estado);

        // Inicializa los nuevos atributos
        this.numeroDePuertas = numeroDePuertas;
        this.capacidadDePasajeros = capacidadDePasajeros;
    }

    public int getNumeroDePuertas() {
        return this.numeroDePuertas;
    }

    public void setNumeroDePuertas(int numeroDePuertas) {
        if (numeroDePuertas >= 1)
            this.numeroDePuertas = numeroDePuertas;
    }

    public int getCapacidadDePasajeros() {
        return this.capacidadDePasajeros;
    }

    public void setCapacidadDePasajeros(int capacidadDePasajeros) {
        if (capacidadDePasajeros > 0)
            this.capacidadDePasajeros = capacidadDePasajeros;
    }

    public boolean validarNumeroDePuertas() {
        return this.numeroDePuertas >= 1;
    }

    public boolean validarCapacidadDePasajeros() {
        return this.capacidadDePasajeros > 0;
    }

    // Para la Camioneta de Pasajeros, al precio de renta se le suma
    // una tarifa de 1% más por cada puerta que tenga el vehículo
    @Override public float calcularPrecioDeRenta(int porcentaje) {
        setPrecioDeRenta(super.calcularPrecioDeRenta(porcentaje) * (1 + (this.numeroDePuertas / 100)));
        return getPrecioDeRenta();
    }

    @Override public float calcularPrecioDeRenta(int porcentaje, int cargosAdicionales) {
        setPrecioDeRenta(super.calcularPrecioDeRenta(porcentaje, cargosAdicionales) * (1 + (this.numeroDePuertas / 100)));
        return getPrecioDeRenta();
    }

    @Override public void mostrarInformacion() {
        // Mostramos los elementos de la clase padre
        super.mostrarInformacion();

        // mostramos los elementos adicionales
        System.out.println("Número de puertas: " + this.numeroDePuertas);
        System.out.println("Capacidad de pasajeros: " + this.numeroDePuertas);
    }

    @Override public void leerDatos() {
        super.leerDatos();

        // Leemos los datos adicionales
        while (!validarNumeroDePuertas()) {
            setNumeroDePuertas(Utils.leerEntero("Número de puertas: "));

            if (!validarNumeroDePuertas())
                System.out.println("[!] El número de puertas que ingresó no es válida. Intente de nuevo.");
        }

        while (!validarCapacidadDePasajeros()) {
            setCapacidadDePasajeros(Utils.leerEntero("Capacidad de pasajeros: "));

            if (!validarCapacidadDePasajeros())
                System.out.println("[!] La capacidad de pasajeros que ingresó no es válida. Intente de nuevo.");
        }
    }
}
