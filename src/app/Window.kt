package app

import lib.sRAD.gui.sComponent.SLabel
import lib.sRAD.gui.sComponent.SPanel
import javax.swing.ImageIcon

class Window: SPanel(150, 25, 980, 410) {

    init {
        val bienvenido = SLabel(2, 2, ImageIcon("resources/pantallaBienvenida.png"))
        add(bienvenido)
    }

}