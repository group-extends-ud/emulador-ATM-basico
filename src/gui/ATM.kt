package gui

import lib.sRAD.swingRAD.setProperties
import java.awt.Graphics
import javax.swing.JFrame
import javax.swing.JPanel
import java.awt.Color

import java.awt.GradientPaint

import java.awt.Graphics2D




class ATM: JFrame() {

    init {
        crearDegradado()

        setProperties()
    }

    private fun crearDegradado() {
        val degradado = object: JPanel() {
            override fun paintComponent(g: Graphics) {
                super.paintComponents(g)
                val g2d = g as Graphics2D
                g2d.paint = GradientPaint(0F, 0F, Color(218, 232, 252), 0F, 720F, Color(36, 61, 179))
                g2d.fillRect(0, 0, 1300, 720)
            }
        }
        contentPane = degradado
    }

}