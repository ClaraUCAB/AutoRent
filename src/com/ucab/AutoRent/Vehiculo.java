package com.ucab.AutoRent;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vehiculo {
    protected int codigo;
    protected String placa;
    protected String marca;
    protected int ano;
    protected float costoDeAdquisicion;
    protected float precioDeRenta;
    protected float kilometraje;
    protected int estado;
    protected Fecha fechaUltimoMantenimiento;

    // Estado
    // 0: No disponible
    // 1: Disponible para renta
    // 2: En renta
    // 3: Fuera de servicio o retirado

    // I/O
    private Scanner scanner = new Scanner(System.in);
    
    // Constructor genérico. Genera un vehículo inválido
    public Vehiculo() {
        this.codigo = 0;
        this.placa = "";
        this.marca = "";
        this.ano = -1;
        this.costoDeAdquisicion = -1;
        this.precioDeRenta = -1;
        this.kilometraje = -1;
        this.estado = -1;
    }

    public Vehiculo(
        int codigo,
        String placa,
        String marca,
        int ano,
        float costoDeAdquisicion,
        float precioDeRenta,
        float kilometraje,
        int estado
    ) {
        this.codigo = codigo;
        this.placa = placa;
        this.marca = marca;
        this.ano = ano;
        this.costoDeAdquisicion = costoDeAdquisicion;
        this.precioDeRenta = precioDeRenta;
        this.kilometraje = kilometraje;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        if (codigo > 0)
            this.codigo = codigo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        if (validarPlaca(placa))
            this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if (marca.length() > 0)
            this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        if (ano >= 1885 && ano <=2025)
            this.ano = ano;
    }

    public float getCostoDeAdquisicion() {
        return costoDeAdquisicion;
    }

    public void setCostoDeAdquisicion(float costoDeAdquisicion) {
        if (costoDeAdquisicion >= 0)
            this.costoDeAdquisicion = costoDeAdquisicion;
    }

    public float getPrecioDeRenta() {
        return precioDeRenta;
    }

    public void setPrecioDeRenta(float precioDeRenta) {
        if (precioDeRenta >= (this.costoDeAdquisicion * 1.20))
            this.precioDeRenta = precioDeRenta;
    }

    public float getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(float kilometraje) {
        if (validarKilometraje(kilometraje))
            this.kilometraje = kilometraje;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        if (validarEstado(estado))
            this.estado = estado;
    }

    public Fecha getFechaUltimoMantenimiento() {
        return fechaUltimoMantenimiento;
    }

    public void setFechaUltimoMantenimiento(Fecha fechaUltimoMantenimiento) {
        this.fechaUltimoMantenimiento = fechaUltimoMantenimiento;
    }

    // Funciones de validación
    
    public boolean validarCodigo() {
        return this.codigo > 0;
    }
    
    public boolean validarPlaca() {
        return this.placa.length() == 8 && Utils.esAlfanumerico(this.placa);
    }

    public boolean validarPlaca(String placa) {
        return placa.length() == 8 && Utils.esAlfanumerico(placa);
    }

    public boolean validarMarca() {
        return this.marca.length() > 0;
    }

    public boolean validarAno() {
        return this.ano >= 1885 && this.ano <= 2025;
    }

    public boolean validarCostoDeAdquisicion() {
        return this.costoDeAdquisicion >= 0;
    }
   
    public boolean validarPrecioDeRenta() {
        return this.precioDeRenta >= (this.costoDeAdquisicion * 1.20);
    }

    public boolean validarPrecioDeRenta(float precioDeRenta) {
        return precioDeRenta >= (this.costoDeAdquisicion * 1.20);
    }
    
    public boolean validarKilometraje() {
        return this.kilometraje >= 0;
    }

    public boolean validarKilometraje(float kilometraje) {
        return kilometraje >= 0;
    }
    
    public boolean validarEstado() {
        return this.estado >= 0 && this.estado <= 3;
    }

    public boolean validarEstado(int estado) {
        return estado >= 0 && estado <= 3;
    }

    public void leerDatos() {
        // Leemos el codigo
        while (!validarCodigo()) {
            setCodigo(Utils.leerEntero("Código del vehículo (valor entero): "));

            if (!validarCodigo())
                System.out.println("[!] El código que ingresó no es válido. Intente de nuevo.");
        }

        // Leemos la placa
        while (!validarPlaca()) {
            System.out.print("Placa del vehículo (8 caracteres alfanuméricos): ");
            setPlaca(scanner.nextLine().toUpperCase());     // Convertimos la placa a mayúsculas

            if (!validarPlaca())
                System.out.println("[!] La placa que ingresó no es válida. Intente de nuevo.");
        }

        // Leemos la marca
        while (!validarMarca()) {
            System.out.print("Marca del vehículo: ");
            setMarca(scanner.nextLine());

            if (!validarMarca())
                System.out.println("[!] La marca que ingresó no es válida. Intente de nuevo.");
        }

        // Leemos el año de fabricación
        while (!validarAno()) {
            setAno(Utils.leerEntero("Año de fabricación del vehículo (1885-2025): "));

            if (!validarAno())
                System.out.println("[!] El año que ingresó no es válido. Intente de nuevo.");
        }

        // Leemos el costo de adquisición
        while (!validarCostoDeAdquisicion()) {
            setCostoDeAdquisicion(Utils.leerEntero("Costo de adquisición del vehículo: "));

            if (!validarCostoDeAdquisicion())
                System.out.println("[!] El costo de adquisición que ingresó no es válido. Intente de nuevo.");
        }

        // Leemos el precio de renta
        while (!validarPrecioDeRenta()) {
            setPrecioDeRenta(Utils.leerEntero("Precio de renta del vehículo (Al menos 20% superior al precio de cadquisición): "));

            if (!validarPrecioDeRenta())
                System.out.println("[!] El precio de renta que ingresó no es válido. Intente de nuevo.");
        }

        // Leemos el kilometraje
        while (!validarKilometraje()) {
            setKilometraje(Utils.leerEntero("Kilometraje del vehículo: "));

            if (!validarKilometraje())
                System.out.println("[!] El precio de renta que ingresó no es válido. Intente de nuevo.");
        }

        // Leemos el estado
        while (!validarEstado()) {
            System.out.println("\nEstado del vehículo:");
            System.out.println("0: No disponible    1: Disponible    2: En renta    3: Fuera de servicio o retirado");
            setEstado(Utils.leerEntero("> "));
            System.out.println();

            if (!validarEstado())
                System.out.println("[!] El estado que ingresó no es válido. Intente de nuevo.");
        }

        // Leemos fecha de último mantenimiento
        // while (true) {
        //     System.out.println("\nEstado del vehículo:");
        //     setFechaUltimoMantenimiento(Utils.leerEntero());
        //     scanner.nextLine();
        //
        //     if (validarKilometraje())
        //         break;
        //
        //     System.out.println("[!] El precio de renta que ingresó no es válido. Intente de nuevo.");
        // }
    }

    // El porcentaje es el mínimo que debe estar el precio de renta
    // por encima al de adquisición
    public float calcularPrecioDeRenta(int porcentaje) {
        this.precioDeRenta = this.costoDeAdquisicion * (1 + (porcentaje / 100));
        return this.precioDeRenta;
    }

    public float calcularPrecioDeRenta(int porcentaje, int cargosAdicionales) {
        this.precioDeRenta = (this.costoDeAdquisicion + cargosAdicionales) * (1 + (porcentaje / 100));
        return this.precioDeRenta;
    }

    // Devuelve verdadero si se recomienda mantenimiento, falso de lo contrario
    public boolean determinarMantenimiento(int maxDias, int maxKilometraje) {
        // El tiempo de máximo mantenimiento está en días
        // Por ejemplo, 6 meses = 180 dias

        // Creamos una fecha inválida temporalmente
        // Fecha fechaActual = new Fecha(-1, -1, -1);

        // Preguntamos la fecha de hoy
        // System.out.println("¿Qué día es hoy?");
        // fechaActual.leerFecha();

        // Calculamos el tiempo que ha pasado desde el último mantenimiento (en días)
        // int diferencia = 0;
        //
        // // Primero los años
        // diferencia += (fechaActual.ano - this.fechaUltimoMantenimiento.ano) * 365;
        //
        // // Luego los meses
        // int mesesDiferencia = (fechaActual.mes - this.fechaUltimoMantenimiento.mes) * 30;
        // if (mesesDiferencia < 0)
        //
        // // Luego los dias
        // diferencia += fechaActual.dia - this.fechaUltimoMantenimiento.dia;

        // Vemos si ya se superó el kilometraje desde el último mantenimiento
        if (this.kilometraje > maxKilometraje) {
            System.out.println("!!! SE RECOMIENDA MANTENIMIENTO !!!");
            System.out.println("Motivo: Kilometraje superado");
            System.out.println("Kilometraje máximo: " + maxKilometraje);
            System.out.println("Kilometraje actual: " + this.kilometraje);
            Utils.pause();
            return true;
        }

        return false;
    }

    // Hace varios chequeos de integridad del vehículo.
    // Devuelve true si se retira, falso si no.
    public boolean retirarVehiculo() {
        // Si le hace falta mantenimiento al vehiculo, lo retiramos
        if (determinarMantenimiento(180, 125000)) {
            // Retira el vehiculo
            this.estado = 3;
            return true;
        }

        // Preguntamos si se ha detectado alguna falla. El default es NO
        String respuesta = Utils.leerString("¿Se ha detectado alguna falla grave en el vehículo? [y/N] ").toLowerCase();

        if (respuesta.equals("y")) {
            // Retira el vehiculo
            this.estado = 3;
            return true;
        }

        // Preguntamos si se ha programado algún mantenimiento. El default es NO
        respuesta = Utils.leerString("¿Se ha programado algún mantenimiento al vehículo? [y/N] ");

        if (respuesta.equals("y")) {
            // Retira el vehiculo
            this.estado = 3;
            return true;
        }

        // No se retira el vehiculo
        return false;
    }

    // Muestra la información actual del vehículo
    public void mostrarInformacion() {
        System.out.println("Código: " + this.codigo);
        System.out.println("Placa: " + this.placa);
        System.out.println("Marca: " + this.marca);
        System.out.println("Año de fabricación: " + this.ano);
        System.out.println("Costo de adquisición: " + this.costoDeAdquisicion);
        System.out.println("Precio de renta: " + this.precioDeRenta);
        System.out.println("Kilometraje: " + kilometraje);

        System.out.print("Estado: ");
        switch (estado) {
            case 0:
                System.out.println("No disponible");
                break;

            case 1:
                System.out.println("Disponible para renta");
                break;

            case 2:
                System.out.println("En renta");
                break;

            case 3:
                System.out.println("Fuera de servicio o retirado");
                break;
        }

        // System.out.println("Fecha de último mantenimiento: " + this.fechaUltimoMantenimiento.toString());
    }
}

