package app

import lib.sRAD.gui.resource.black
import lib.sRAD.gui.resource.white
import lib.sRAD.gui.sComponent.SLabel
import lib.sRAD.gui.sComponent.SPanel
import lib.sRAD.gui.tool.setProperties
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
                Current.Monto -> setMonto()
                Current.Password -> setPassword()
                Current.Operacion -> setOperacion()
                else -> setBienvenido()
            }
            field = value
            repaint()
        }

    private fun setMonto() {
        val operacion = SLabel(2, 2, ImageIcon("resources/pantallaMonto.png"))
        add(operacion)
    }

    private var tfPassword = JPasswordField()

    init {
        current = Current.Bienvenido
        tfPassword.setProperties(
            310, 210, 390, 50, background = white, editable = false, hAlignment = JTextField.RIGHT,
            foreground = black
        )
    }

    @Suppress("DEPRECATION")
    fun validar() {
        current = if (Banco.validarPassword(tfPassword.text)) {
            next
        } else {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE)
            Current.Operacion
        }
    }

    @Suppress("DEPRECATION")
    fun addPoint (i: Int) {
        tfPassword.text = "${tfPassword.text}$i"
    }

    @Suppress("DEPRECATION")
    fun removePoint () {
        if(tfPassword.text.isNotEmpty()) {
            tfPassword.text = tfPassword.text.subSequence(0, tfPassword.text.length - 1).toString()
        }
    }

    private fun setPassword() {
        val operacion = SLabel(2, 2, ImageIcon("resources/pantallaContrasena.png"))
        add(operacion)

        tfPassword.text = ""
        add(tfPassword)
    }

    private fun setOperacion () {
        val operacion = SLabel(2, 2, ImageIcon("resources/pantallaOperacion.png"))
        add(operacion)
    }

    private fun setBienvenido() {
        val bienvenido = SLabel(2, 2, ImageIcon("resources/pantallaBienvenido.png"))
        add(bienvenido)
    }

}

enum class Current {
    Apagado, Bienvenido, Operacion, Password, Monto
}