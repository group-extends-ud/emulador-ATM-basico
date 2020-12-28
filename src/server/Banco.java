package server;

public class Banco {

    public static boolean validarTarjeta(String text) {
        //validar que no esta vacio
        if(text.isEmpty())
            return false;
        //por implementar
        return true;
    }

    public static boolean validarPassword(String password) {
        //por implementar
        return true;
    }

    public static boolean verificarDisponibilidadSaldo(int saldo) {
        //por implementar
        return true;
    }

    public static void retirar(int saldo) {
        //por implementar
    }

    public static String datosUltimaOperacion(String numeroTarjeta) {
        //por implementar
        return "Fecha: x\nNumero cuenta: x\nOtra lineaaaaaaaaaaaaaaaaaaaaaaaaa\nOtra linea\nOtra linea\nOtra linea\nOtra linea\nOtra linea" +
                "\nOtra linea\nOtra linea\nOtra linea\nOtra linea\nOtra linea\nOtra linea\nOtra linea\nOtra linea";
    }
}
