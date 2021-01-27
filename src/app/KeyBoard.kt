package app

import lib.sRAD.gui.resource.*
import lib.sRAD.gui.sComponent.SButton
import lib.sRAD.gui.sComponent.SPanel
import java.awt.Color
import java.awt.GradientPaint
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

abstract class KeyBoard: SPanel(450, 440, 385, 270) {

    init {
        //numbers
        for (i in 1 .. 9) {
            val btnNumberI = SButton(
                25 + ((i-1)%3)*74, 140 - ((i-1)/3)*60,
                60, 50, i.toString(), background = white, foreground = black, backgroundEntered = mustard
            )
            btnNumberI.addActionListener { pressNumber(i) }
            btnNumberI.addMouseListener(ButtonListener)
            add(btnNumberI)
        }
        val btnNumber0 = SButton(
            99, 200, 60, 50, "0", background = white, foreground = black, backgroundEntered = mustard
        )
        btnNumber0.addActionListener { pressNumber(0) }
        btnNumber0.addMouseListener(ButtonListener)
        add(btnNumber0)

        //del button
        val btnDel = SButton(
            245, 20, 120, 50, "DEL", background = ta1, foreground = black, backgroundEntered = mustard,
            border = ta2Border
        )
        btnDel.addActionListener { pressDel() }
        btnDel.addMouseListener(ButtonListener)
        add(btnDel)

        //cancel button
        val btnCancel = SButton(
            245, 80, 120, 50, "CANCEL", background = ta3, foreground = black, backgroundEntered = mustard,
            border = ta4Border
        )
        btnCancel.addActionListener { pressCancel() }
        btnCancel.addMouseListener(ButtonListener)
        add(btnCancel)

        //enter button
        val btnEnter = SButton(
            245, 140, 120, 110, "ENTER", background = ta5, foreground = black, backgroundEntered = mustard,
            border = ta6Border
        )
        btnEnter.addActionListener { pressEnter() }
        btnEnter.addMouseListener(ButtonListener)
        add(btnEnter)
    }

    abstract fun pressCancel()

    abstract fun pressDel()

    abstract fun pressNumber(num: Int)

    abstract fun pressEnter()

    override fun paintComponent(g: Graphics){
        super.paintComponents(g)
        val g2d = g as Graphics2D
        g2d.paint = GradientPaint(0F, 0F, Color(245, 245, 245), 0F, 270F, Color(102, 102, 102))
        g2d.fillRect(0, 0, 385, 270)
    }

}

object ButtonListener: MouseListener {

    override fun mouseClicked(e: MouseEvent?) {
    }

    override fun mousePressed(e: MouseEvent?) {
        Altavoz.playKeyboardPress()
    }

    override fun mouseReleased(e: MouseEvent?) {
    }

    override fun mouseEntered(e: MouseEvent?) {
        Altavoz.playKeyboardRelease()
    }

    override fun mouseExited(e: MouseEvent?) {
    }

}
