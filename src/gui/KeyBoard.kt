package gui

import lib.sRAD.swingRAD.sComponents.SPanel
import java.awt.Color
import java.awt.GradientPaint
import java.awt.Graphics
import java.awt.Graphics2D

class KeyBoard: SPanel(450, 440, 385, 270) {

    override fun paintComponent(g: Graphics){
        super.paintComponents(g)
        val g2d = g as Graphics2D
        g2d.paint = GradientPaint(0F, 0F, Color(245, 245, 245), 0F, 270F, Color(102, 102, 102))
        g2d.fillRect(0, 0, 385, 270)
    }

}