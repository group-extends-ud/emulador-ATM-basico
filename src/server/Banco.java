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

    public static boolean validarPassword(String password) {
        try {
            return BaseDeDatos.validarContrasenia(lastID, Integer.parseInt(password));
        }
        catch (Exception e) {
            return false;
        }
    }

    public static boolean verificarDisponibilidadSaldo(int saldo) {
        //por implementar
        return true;
    }

    //Transaccion: Retiro; Descripcion: cantidad
    public static void retirar(int saldo) {
        //por implementar
    }

    public static String datosUltimaOperacion(String numeroTarjeta) {
        //por implementar
        return "Fecha: x\nNumero cuenta: x\nOtra lineaaaaaaaaaaaaaaaaaaaaaaaaa\nOtra linea\nOtra linea\nOtra linea\nOtra linea\nOtra linea" +
                "\nOtra linea\nOtra linea\nOtra linea\nOtra linea\nOtra linea\nOtra linea\nOtra linea\nOtra linea";
    }


    public static void realizarTransaccion(int saldo, int id) {
        //por implementar
    }
}
