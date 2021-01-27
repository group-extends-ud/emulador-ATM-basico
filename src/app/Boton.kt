package app

import lib.sRAD.gui.resource.black
import lib.sRAD.gui.resource.mustard
import lib.sRAD.gui.sComponent.SButton
import lib.sRAD.gui.sComponent.SLabel
import java.awt.Color
import java.awt.GradientPaint
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.ImageIcon
import kotlin.system.exitProcess

private val white = Color(245, 245, 245)
private val gray = Color(102, 102, 102)

abstract class Boton: SButton(), MouseListener {

    protected var color1 = white
    protected var color2 = gray

    override fun paintComponent(g: Graphics){
        super.paintComponents(g)
        val g2d = g as Graphics2D
        g2d.paint = GradientPaint(0F, 0F, color1, 0F, 60F, color2)
        g2d.fillRect(0, 0, 60, 60)
    }

    override fun mousePressed(e: MouseEvent?) { }

    override fun mouseReleased(e: MouseEvent?) { }

    override fun mouseExited(e: MouseEvent?) {
        color1 = Color(245, 245, 245)
        color2 = Color(102, 102, 102)
    }

    override fun mouseEntered(e: MouseEvent?) {
        Altavoz.playKeyboardRelease()
    }

}

open class ExitButton: Boton() {

    init {
        addMouseListener(this)

        val lExit = SLabel(12, 20, 40, 20, "EXIT", foreground = black)
        add(lExit)

        layout = null
        setProperties(1200, 629, 60, 60)
    }

    override fun mouseClicked(e: MouseEvent?) {
        exitProcess(0)
    }

    override fun mouseEntered(e: MouseEvent?) {
        super.mouseEntered(e)
        color1 = Color(245, 29, 29)
        color2 = Color(109, 29, 29)
    }

}

abstract class HelpButton: Boton() {
    init {
        setProperties(1120, 629, 60, 60)
        addMouseListener(this)

        val lHelp = SLabel(7, 20, 50, 20, "HELP", foreground = black)
        add(lHelp)

        layout = null
    }

    override fun mouseEntered(e: MouseEvent?) {
        super.mouseEntered(e)
        color1 = Color(29, 245, 29)
        color2 = Color(29, 109, 29)
    }

}

abstract class OptionButton(value: Int): Boton() {

    init {
        addMouseListener(this)
        setProperties(
            if(value<3) 20 else 1140,
            when(value % 3){0 -> 30; 1 -> 200; else -> 360 },
            120,
            60
        )
    }

    override fun paintComponent(g: Graphics){
        val g2d = g as Graphics2D
        g2d.paint = GradientPaint(0F, 0F, color1, 0F, 60F, color2)
        g2d.fillRect(0, 0, 120, 60)
    }

    override fun mouseEntered(e: MouseEvent?) {
        super.mouseEntered(e)
        color1 = mustard
        color2 = Color(109, 109, 29)
    }

}

class SoundButton: Boton(), MouseListener {

    private val soundOn = SLabel(0, 0, ImageIcon("resources/image/soundOn.png"))
    private val soundOff = SLabel(0, 0, ImageIcon("resources/image/soundOff.png"))

    init {
        layout = null

        add(soundOn)
        addMouseListener(this)
        setProperties(1040, 629, 60, 60)
    }

    override fun mouseClicked(e: MouseEvent?) {
        Altavoz.sonido = !Altavoz.sonido
        removeAll()
        add(if (Altavoz.sonido) soundOn else soundOff)
    }

    override fun mouseEntered(e: MouseEvent?) {
        super.mouseEntered(e)
        color1 = Color(29, 245, 238)
        color2 = Color(29, 109, 102)
    }

}
