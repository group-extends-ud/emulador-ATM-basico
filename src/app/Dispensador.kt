package app

import lib.sRAD.gui.sComponent.SPanel
import java.awt.Color
import java.awt.GradientPaint
import java.awt.Graphics
import java.awt.Graphics2D

class Dispensador: SPanel(30, 590, 400, 80) {

    init {
        val pDecorador = SPanel(40, 35, 330, 10, Color(100, 118, 135))
        add(pDecorador)
    }

    override fun paintComponent(g: Graphics){
        super.paintComponents(g)
        val g2d = g as Graphics2D
        g2d.paint = GradientPaint(0F, 0F, Color(245, 245, 245), 0F, 80F, Color(102, 102, 102))
        g2d.fillRect(0, 0, 400, 80)
    }

}