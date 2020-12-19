package app

import lib.sRAD.gui.sComponent.SLabel
import lib.sRAD.gui.sComponent.SPanel
import javax.swing.ImageIcon

class Window: SPanel(150, 25, 980, 410) {
    var current: Current = Current.Apagado
        set(value) {
            removeAll()
            when (value) {
                Current.Operacion -> setOperacion()
                Current.Bienvenido -> setBienvenido()
            }
            field = value
        }

    init {
        current = Current.Bienvenido
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
    Apagado, Bienvenido, Operacion
}