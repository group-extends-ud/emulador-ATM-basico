package app

import lib.sRAD.gui.resource.black
import lib.sRAD.gui.resource.white
import lib.sRAD.gui.sComponent.SLabel
import lib.sRAD.gui.sComponent.SPanel
import lib.sRAD.gui.tool.setProperties
import lib.sRAD.logic.isInt
import server.Banco
import javax.swing.ImageIcon
import javax.swing.JOptionPane
import javax.swing.JPasswordField
import javax.swing.JTextField

class Window: SPanel(150, 25, 980, 410) {
    var next = Current.Apagado

    var current: Current = Current.Apagado
        set(value) {
            removeAll()
            when (value) {
                Current.Final -> setFinal()
                Current.Factura -> setFactura()
                Current.CustomMonto -> setCustomMonto()
                Current.Monto -> setMonto()
                Current.Password -> setPassword()
                Current.Operacion -> setOperacion()
                else -> setBienvenido()
            }
            field = value
            repaint()
        }

    private var tfPassword = JPasswordField()
    private var tfMonto = JTextField()

    init {
        current = Current.Bienvenido
        tfPassword.setProperties(
            310, 210, 390, 50, background = white, editable = false, hAlignment = JTextField.RIGHT,
            foreground = black
        )
        tfMonto.setProperties(
            310, 210, 390, 50, background = white, editable = false, hAlignment = JTextField.RIGHT,
            foreground = black
        )
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

        add(tfMonto)
        tfMonto.text = "CARGANDO"
        tfMonto.text = ""
    }

    private fun setMonto() {
        val operacion = SLabel(2, 2, ImageIcon("resources/image/pantallaMonto.png"))
        add(operacion)
    }

    @Suppress("DEPRECATION")
    fun validar() {
        current = if (Banco.validarPassword(tfPassword.text)) {
            next
        } else {
            //JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE)
            Current.Operacion
        }
    }

    @Suppress("DEPRECATION")
    fun addPoint (i: Int) {
        if (current == Current.Password)
            tfPassword.text = "${tfPassword.text}$i"
        else
            tfMonto.text = "${tfMonto.text}$i"
    }

    @Suppress("DEPRECATION")
    fun removePoint () {
        if (current == Current.Password && tfPassword.text.isNotEmpty())
            tfPassword.text = tfPassword.text.substring(0, tfPassword.text.length - 1)

        else if(current == Current.CustomMonto && tfMonto.text.isNotEmpty())
            tfMonto.text = tfMonto.text.substring(0, tfMonto.text.length - 1)
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

    fun obtenerSaldo(): Int {
        if (tfMonto.text.isInt()) {
            return tfMonto.text.toInt()
        }
        return -1
    }

}

enum class Current {
    Apagado, Bienvenido, Operacion, Password, Monto, CustomMonto, Factura, Final
}