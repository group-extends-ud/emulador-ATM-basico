package app

import lib.sRAD.gui.resource.mustard
import lib.sRAD.gui.sComponent.SButton
import java.awt.Color
import java.awt.GradientPaint
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

abstract class OptionButton(value: Int): SButton(), MouseListener  {
    private var color1 = Color(245, 245, 245)
    private var color2 = Color(102, 102, 102)

    init {
        addMouseListener(this)
        setProperties(
            if(value<3) 20 else 1140,
            when(value % 3){0 -> 30; 1 -> 200; else -> 360 },
            120,
            60
        )
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponents(g)
        val g2d = g as Graphics2D
        g2d.paint = GradientPaint(0F, 0F, color1, 0F, 70F, color2)
        g2d.fillRect(0, 0, 120, 60)
    }

    abstract override fun mouseClicked(e: MouseEvent?)

    override fun mousePressed(e: MouseEvent?) { }

    override fun mouseReleased(e: MouseEvent?) { }

    override fun mouseEntered(e: MouseEvent?) {
        color1 = mustard
        color2 = Color(109, 109, 29)
    }

    override fun mouseExited(e: MouseEvent?) {
        color1 = Color(245, 245, 245)
        color2 = Color(102, 102, 102)
    }
}