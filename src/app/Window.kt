package app

import lib.sRAD.gui.resource.*
import lib.sRAD.gui.sComponent.SLabel
import lib.sRAD.gui.sComponent.SPanel
import lib.sRAD.gui.tool.setProperties
import lib.sRAD.logic.isInt
import server.Banco
import javax.swing.ImageIcon
import javax.swing.JPasswordField
import javax.swing.JTextArea
import javax.swing.JTextField

class Window: SPanel(150, 25, 980, 410) {
    var siguienteEstado = Estado.Apagado

    var estado: Estado = Estado.Apagado
        set(value) {
            Altavoz.playClickSound()
            removeAll()
            when (value) {
                Estado.Final -> setFinal()
                Estado.Factura -> setFactura()
                Estado.MontoPersonalizado -> setCustomMonto()
                Estado.SeleccionarMonto -> setMonto()
                Estado.Contrasenia -> setPassword()
                Estado.Transaccion -> setTransaccion()
                Estado.EscogerOperacion -> setOperacion()
                Estado.Consulta -> setConsulta()
                Estado.Saldo -> setSaldo()
                Estado.UltimoMovimiento -> setUltimoMovimiento()
                else -> setBienvenido()
            }
            field = value
            repaint()
        }

    private var tfPassword = JPasswordField()
    private var tfNum = JTextField()
    private val lSaldo = SLabel(
        150, 210, 390, 50, font = fontTitle2, background = white, hAlignment = JTextField.RIGHT,
        foreground = black)
    private val taInfo = JTextArea()

    init {
        taInfo.setProperties(250, 170, 500, 180, font = fontTitle2, background = transparent,
            foreground = black, border = null, editable = false)

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

    fun establecerInformacion(info: String) {
        taInfo.text = info
    }

    fun establecerSaldo(saldo: String) {
        lSaldo.text = saldo
    }

    private fun setSaldo () {
        add(lSaldo)

        val operacion = SLabel(2, 2, ImageIcon("resources/image/pantallaSaldo.png"))
        add(operacion)
    }

    private fun setUltimoMovimiento() {
        add(taInfo)

        val operacion = SLabel(2, 2, ImageIcon("resources/image/pantallaUltimoMovimiento.png"))
        add(operacion)
    }

    private fun setConsulta() {
        val operacion = SLabel(2, 2, ImageIcon("resources/image/pantallaConsulta.png"))
        add(operacion)
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
            Altavoz.playWinXpErrorSound()
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

        else if(tfNum.text.isNotEmpty() && (estado == Estado.MontoPersonalizado || estado == Estado.Transaccion))
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

/**
 * Estados en los que puede estar el ATM
 */
enum class Estado {
    Apagado, //el cajero automatico se encuentra apagado inicialmente
    Bienvenido, //espera el ingreso de alguna tarjeta
    EscogerOperacion, //permite escoger si desea retirar, hacer una transaccion, o consultar
    Contrasenia,
    SeleccionarMonto,
    MontoPersonalizado,
    Factura,
    Final,
    Transaccion,
    Consulta, //Permite seleccionar entre consulta de saldo, del ultimo movimiento o de los ultimos 5 movimientos
    Saldo, //Muestra el saldo disponible de la cuenta ingresada
    UltimoMovimiento
}