package server;

import com.fasterxml.jackson.databind.ser.Serializers;

public class Banco {
    private static String lastID;

    public static boolean validarTarjeta(String text) {
        //validar que no esta vacio
        if(text.isEmpty())
            return false;

        //validar en la base de datos
        if(!BaseDeDatos.contieneCuenta(text))
            return false;

        lastID = text;
        return true;
    }

    public static boolean validarPassword(String password) {
        try {
            return BaseDeDatos.validarContrasenia(lastID, Integer.parseInt(password));
        }
        catch (Exception e) {
            return false;
        }
    }

    public static boolean verificarDisponibilidadSaldo(int saldo) {
        return saldo<=BaseDeDatos.obtenerSaldo(lastID);
    }

    //Transaccion: Retiro; Descripcion: cantidad
    public static void retirar(int saldo) {
        BaseDeDatos.retiro(lastID, saldo);
    }

    public static void realizarTransaccion(int saldo, String id) {
        BaseDeDatos.transaccion(lastID, saldo, id);
    }

    public static String datosUltimaOperacion(String id) {
        return BaseDeDatos.last(id);
    }

    public static String datosCincoUltimas(String id) {
        return BaseDeDatos.last5(id);
    }

}
