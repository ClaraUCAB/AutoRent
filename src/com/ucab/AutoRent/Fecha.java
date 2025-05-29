package com.ucab.AutoRent;

import java.util.Scanner;

public class Fecha {
    private int dia;
    private int mes;
    private int ano;

    private Scanner scanner = new Scanner(System.in);

    Fecha(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return this.dia;
    }

    public void setDia(int dia) {
        if (dia > 0 && dia <= 31)
            this.dia = dia;
    }

    public int getMes() {
        return this.mes;
    }

    public void setMes(int mes) {
        if (mes > 0 && mes <= 12)
            this.mes = mes;
    }

    public int getAno() {
        return this.ano;
    }

    public void setAno(int ano) {
        if (ano >= 0)
            this.ano = ano;
    }

    public void leerFecha() {
        while (true) {
            System.out.print("Ingrese día: ");
            setDia(scanner.nextInt());
            scanner.nextLine();

            if (this.dia > 0 && this.dia <= 31)
                break;

            System.out.println("[!] El día que ha ingresado no es válido. Intente de nuevo.");
        }

        while (true) {
            System.out.print("Ingrese mes: ");
            setMes(scanner.nextInt());
            scanner.nextLine();

            if (this.mes > 0 && this.mes <= 12)
                break;

            System.out.println("[!] El mes que ha ingresado no es válido. Intente de nuevo.");
        }

        while (true) {
            System.out.print("Ingrese año: ");
            setAno(scanner.nextInt());
            scanner.nextLine();

            if (this.ano >= 0)
                break;

            System.out.println("[!] El año que ha ingresado no es válido. Intente de nuevo.");
        }
    }

    public String toString() {
        return this.dia + "/" + this.mes + "/" + this.ano;
    }
}
