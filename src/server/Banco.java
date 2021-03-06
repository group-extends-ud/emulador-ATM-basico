package server;

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

    public static boolean existeCuenta(String text) {
        //validar que no esta vacio
        if(text.isEmpty())
            return false;

        //validar en la base de datos
        if(!BaseDeDatos.contieneCuenta(text))
            return false;

        return true;
    }

    public static boolean validarPassword(String password) {
        try {
            return BaseDeDatos.login(lastID, Integer.parseInt(password));
        }
        catch (Exception e) {
            return false;
        }
    }

    public static boolean verificarDisponibilidadSaldo(int saldo) {
        return saldo<=BaseDeDatos.obtenerSaldo(lastID);
    }

    public static void retirar(int saldo) {
        BaseDeDatos.retiro(lastID, saldo);
    }

    public static boolean realizarTransaccion(int saldo, String id) {
        return BaseDeDatos.transaccion(lastID, saldo, id);
    }

    public static String datosUltimaOperacion() {
        return BaseDeDatos.last(lastID);
    }

    public static int obtenerSaldo(){
        return BaseDeDatos.obtenerSaldo(lastID);
    }

}
