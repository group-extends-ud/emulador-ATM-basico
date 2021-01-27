package app

import lib.sRAD.gui.resource.*
import lib.sRAD.gui.sComponent.SButton
import lib.sRAD.gui.sComponent.SLabel
import lib.sRAD.gui.sComponent.SPanel
import lib.sRAD.gui.tool.setProperties
import java.awt.Color
import java.awt.GradientPaint
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JTextField

var efectivo = 0

open class Dispensador: SPanel(30, 590, 400, 80), MouseListener {

    private val btDinero: SButton
    private val pAmarillo: SPanel

    init {
        val pDecorador = SButton(40, 35, 330, 10, cursor = defaultCursor, background = Color(100, 118, 135))
        add(pDecorador)

        pAmarillo = SPanel(60, 40, 280, 30, transparentMustard)
        pAmarillo.addMouseListener(this)
        pAmarillo.isVisible = false
        add(pAmarillo)

        btDinero = SButton(60, 40, ImageIcon("resources/image/cash.png"))
        btDinero.addMouseListener(this)
        add(btDinero)

        actualizarEfectivo()
    }

    private fun openPopUpEfectivo() {
        parent.parent.parent.parent.isEnabled = false

        val ventana = JFrame()
        ventana.setProperties(450, 290, background = Color(245, 245, 245))

        val title = SLabel(100, 30, 250, 30, "Dispensador de efectivo", fontTitle2, foreground = black)
        ventana.add(title)

        val num = SLabel(70, 90, 60, 20, "Valor", fontTitleMini, black)
        ventana.add(num)

        val btnCerrar = SButton(
            250, 195, 128, 50, "CERRAR", background = ta5, backgroundEntered = mustard, border = ta6Border, foreground = black
        )
        btnCerrar.addActionListener {
            parent.parent.parent.parent.isEnabled = true
            ventana.dispose()
        }
        btnCerrar.addMouseListener(ButtonListener)
        ventana.add(btnCerrar)

        val lNum = SLabel(
            70, 115, 310, 25, efectivo.toString(), background = white, foreground = black, hAlignment = JTextField.RIGHT
        )
        ventana.add(lNum)

        val btnRetirar = SButton(
            50,
            195,
            128,
            50,
            "RETIRAR",
            background = ta5,
            backgroundEntered = mustard,
            border = ta6Border,
            foreground = black
        )
        btnRetirar.addActionListener {
            efectivo = 0
            Altavoz.playRetirarFactura()
            actualizarEfectivo()
            parent.parent.parent.parent.isEnabled = true
            ventana.dispose()
        }
        btnRetirar.addMouseListener(ButtonListener)
        ventana.add(btnRetirar)
    }

    fun actualizarEfectivo() {
        btDinero.isVisible = efectivo > 0
    }

    override fun paintComponent(g: Graphics){
        super.paintComponents(g)
        val g2d = g as Graphics2D
        g2d.paint = GradientPaint(0F, 0F, Color(245, 245, 245), 0F, 80F, Color(102, 102, 102))
        g2d.fillRect(0, 0, 400, 80)
    }

    override fun mouseClicked(e: MouseEvent?) {
        Altavoz.playKeyboardPress()
        openPopUpEfectivo()
    }

    override fun mousePressed(e: MouseEvent?) {
    }

    override fun mouseReleased(e: MouseEvent?) {
    }

    override fun mouseEntered(e: MouseEvent?) {
        Altavoz.playKeyboardRelease()
        pAmarillo.isVisible = true
    }

    override fun mouseExited(e: MouseEvent?) {
        pAmarillo.isVisible = false
    }

}