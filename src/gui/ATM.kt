package gui

import lib.sRAD.swingRAD.black
import lib.sRAD.swingRAD.sComponents.SLabel
import lib.sRAD.swingRAD.setProperties
import java.awt.*
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JButton

class ATM: JFrame() {

    init {
        createATM()
        addOptionButtons()
        addWindow()
        addKeyBoard()
        addImpresora()
        addDispensador()
        addLectorTarjeta()
        addBExit()

        setProperties()
    }

    private fun addBExit() {
        val bExit = object: JButton() {

            init {
                val lExit = SLabel(12, 20, 40, 20, "EXIT", foreground = black)
                add(lExit)

                layout = null
            }

            override fun paintComponent(g: Graphics){
                super.paintComponents(g)
                val g2d = g as Graphics2D
                g2d.paint = GradientPaint(0F, 0F, Color(245, 245, 245), 0F, 60F, Color(102, 102, 102))
                g2d.fillRect(0, 0, 60, 60)
            }

        }
        bExit.setProperties(1200, 629, 60, 60)
        bExit.addActionListener { System.exit(0) }
        add(bExit)
    }

    private fun addLectorTarjeta() {
        add(LectorTarjeta())
    }

    private fun addDispensador() {
        add(Dispensador())
    }

    private fun addImpresora() {
        add(Impresora())
    }

    private fun addKeyBoard() {
        add(KeyBoard())
    }

    private fun addWindow() {
        add(Window())
    }

    private fun addOptionButtons() {
        for (i in 0 until 6) {
            val optionButton = object: JButton()  {
                override fun paintComponent(g: Graphics) {
                    super.paintComponents(g)
                    val g2d = g as Graphics2D
                    g2d.paint = GradientPaint(0F, 0F, Color(245, 245, 245), 0F, 60F, Color(102, 102, 102))
                    g2d.fillRect(0, 0, 120, 60)
                }
            }
            optionButton.setProperties(
                if(i<3) 20 else 1140,
                when(i % 3){0 -> 30; 1 -> 200; else -> 360 },
                120,
                60
            )
            add(optionButton)
        }
    }

    private fun createATM() {
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