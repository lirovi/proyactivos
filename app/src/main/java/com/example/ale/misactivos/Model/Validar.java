package com.example.ale.misactivos.Model;

import android.util.Patterns;

public class Validar {

    String texto, telefono, email, fecha;
    Integer numero;


    public static boolean ValidaTexto(String campotexto) {
        if (!campotexto.isEmpty() && campotexto.length() > 2) {
            return true;
        } else return false;
    }

    public static boolean ValidaNumero(String campotexto, int digitos) {

        boolean resultado=false;
        try {
            if(campotexto.length()>digitos){
                Integer.parseInt(campotexto);
                resultado = true;
            }
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }

    public static boolean ValidaDireccion(String campotexto) {
        if (!campotexto.isEmpty() && campotexto.length() > 5) {
            return true;
        } else return false;
    }
    public static boolean ValidaEmail(String campotexto) {

        boolean resultado;
        if (Patterns.EMAIL_ADDRESS.matcher(campotexto).matches()) {
            resultado = true;
        } else {
            resultado = false;
        }

        return resultado;
    }
}
