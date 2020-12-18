package app

import lib.sRAD.gui.sComponent.SPanel
import java.awt.Color
import java.awt.GradientPaint
import java.awt.Graphics
import java.awt.Graphics2D

class Impresora: SPanel(110, 470, 240, 80) {

    override fun paintComponent(g: Graphics){
        super.paintComponents(g)
        val g2d = g as Graphics2D
        g2d.paint = GradientPaint(0F, 0F, Color(245, 245, 245), 0F, 80F, Color(102, 102, 102))
        g2d.fillRect(0, 0, 240, 80)
    }

}