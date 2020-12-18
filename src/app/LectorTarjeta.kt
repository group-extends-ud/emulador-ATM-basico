package app

import lib.sRAD.gui.resource.*
import lib.sRAD.gui.sComponent.SButton
import lib.sRAD.gui.sComponent.SPanel
import java.awt.Color

class LectorTarjeta: SPanel(900, 469, 340, 140, background = Color(43, 43, 43), border = blackBorder) {

    init {
        val laser = SButton(2, 70, 336, 10, background = ta7, border = transparentBorder, backgroundEntered = mustard)
        add(laser)
    }

}