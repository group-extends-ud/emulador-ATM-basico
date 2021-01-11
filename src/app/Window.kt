package app

import lib.sRAD.gui.resource.black
import lib.sRAD.gui.resource.white
import lib.sRAD.gui.sComponent.SLabel
import lib.sRAD.gui.sComponent.SPanel
import lib.sRAD.gui.tool.setProperties
import lib.sRAD.logic.isInt
import server.Banco
import javax.swing.ImageIcon
import javax.swing.JPasswordField
import javax.swing.JTextField

class Window: SPanel(150, 25, 980, 410) {
    var siguienteEstado = Estado.Apagado

    var estado: Estado = Estado.Apagado
        set(value) {
            playClickSound()
            removeAll()
            when (value) {
                Estado.Final -> setFinal()
                Estado.Factura -> setFactura()
                Estado.MontoPersonalizado -> setCustomMonto()
                Estado.SeleccionarMonto -> setMonto()
                Estado.Contrasenia -> setPassword()
                Estado.Transaccion -> setTransaccion()
                Estado.EscogerOperacion -> setOperacion()
                else -> setBienvenido()
            }
            field = value
            repaint()
        }

    private var tfPassword = JPasswordField()
    private var tfNum = JTextField()

    init {
        estado = Estado.Bienvenido
        tfPassword.setProperties(
            310, 210, 390, 50, background = white, editable = false, hAlignment = JTextField.RIGHT,
            foreground = black
        )
        tfNum.setProperties(
            310, 210, 390, 50, background = white, editable = false, hAlignment = JTextField.RIGHT,
            foreground = black
        )
    }

    private fun setTransaccion() {
        val operacion = SLabel(2, 2, ImageIcon("resources/image/pantallaTransaccion.png"))
        add(operacion)

        add(tfNum)
        tfNum.text = "CARGANDO"
        tfNum.text = ""
    }

    private fun setFinal() {
        val operacion = SLabel(2, 2, ImageIcon("resources/image/pantallaFinal.png"))
        add(operacion)
    }

    private fun setFactura() {
        val operacion = SLabel(2, 2, ImageIcon("resources/image/pantallaFactura.png"))
        add(operacion)
    }

    private fun setCustomMonto() {
        val operacion = SLabel(2, 2, ImageIcon("resources/image/pantallaCustomMonto.png"))
        add(operacion)

        add(tfNum)
        tfNum.text = "CARGANDO"
        tfNum.text = ""
    }

    private fun setMonto() {
        val operacion = SLabel(2, 2, ImageIcon("resources/image/pantallaMonto.png"))
        add(operacion)
    }

    @Suppress("DEPRECATION")
    fun validar() {
        estado = if (Banco.validarPassword(tfPassword.text)) {
            siguienteEstado
        } else {
            //JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE)
            Estado.EscogerOperacion
        }
    }

    @Suppress("DEPRECATION")
    fun addPoint (i: Int) {
        if (estado == Estado.Contrasenia)
            tfPassword.text = "${tfPassword.text}$i"
        else
            tfNum.text = "${tfNum.text}$i"
    }

    @Suppress("DEPRECATION")
    fun removePoint () {
        if (estado == Estado.Contrasenia && tfPassword.text.isNotEmpty())
            tfPassword.text = tfPassword.text.substring(0, tfPassword.text.length - 1)

        else if(estado == Estado.MontoPersonalizado && tfNum.text.isNotEmpty())
            tfNum.text = tfNum.text.substring(0, tfNum.text.length - 1)
    }

    private fun setPassword() {
        val operacion = SLabel(2, 2, ImageIcon("resources/image/pantallaContrasena.png"))
        add(operacion)

        add(tfPassword)
        tfPassword.text = "CARGANDO"
        tfPassword.text = ""
    }

    private fun setOperacion () {
        val operacion = SLabel(2, 2, ImageIcon("resources/image/pantallaOperacion.png"))
        add(operacion)
    }

    private fun setBienvenido() {
        val bienvenido = SLabel(2, 2, ImageIcon("resources/image/pantallaBienvenido.png"))
        add(bienvenido)
    }

    fun obtenerValor(): Int {
        if (tfNum.text.isInt()) {
            return tfNum.text.toInt()
        }
        return -1
    }

}

enum class Estado {
    Apagado, //el cajero automatico se encuentra apagado inicialmente
    Bienvenido, //espera el ingreso de alguna tarjeta
    EscogerOperacion, //permite escoger si desea retirar, hacer una transaccion, o consultar
    Contrasenia,
    SeleccionarMonto,
    MontoPersonalizado,
    Factura,
    Final,
    Transaccion
}