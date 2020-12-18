package app

import lib.sRAD.gui.resource.black
import lib.sRAD.gui.sComponent.SButton
import lib.sRAD.gui.sComponent.SLabel
import lib.sRAD.gui.tool.setProperties
import java.awt.*
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JFrame
import javax.swing.JPanel
import kotlin.system.exitProcess

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
        val bExit = object: SButton() {
            var color1 = Color(245, 245, 245)
            var color2 = Color(102, 102, 102)

            init {
                addMouseListener(object: MouseListener{
                    override fun mouseClicked(e: MouseEvent?) { }

                    override fun mousePressed(e: MouseEvent?) { }

                    override fun mouseReleased(e: MouseEvent?) { }

                    override fun mouseEntered(e: MouseEvent?) {
                        color1 = Color(245, 29, 29)
                        color2 = Color(109, 29, 29)
                    }

                    override fun mouseExited(e: MouseEvent?) {
                        color1 = Color(245, 245, 245)
                        color2 = Color(102, 102, 102)
                    }
                })
                val lExit = SLabel(12, 20, 40, 20, "EXIT", foreground = black)
                add(lExit)

                layout = null
            }

            override fun paintComponent(g: Graphics){
                super.paintComponents(g)
                val g2d = g as Graphics2D
                g2d.paint = GradientPaint(0F, 0F, color1, 0F, 60F, color2)
                g2d.fillRect(0, 0, 60, 60)
            }

        }
        bExit.setProperties(1200, 629, 60, 60)
        bExit.addActionListener { exitProcess(0) }
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
            val optionButton = object: SButton()  {
                var color1 = Color(245, 245, 245)
                var color2 = Color(102, 102, 102)
                init {
                    addMouseListener(object: MouseListener{
                        override fun mouseClicked(e: MouseEvent?) { }

                        override fun mousePressed(e: MouseEvent?) { }

                        override fun mouseReleased(e: MouseEvent?) { }

                        override fun mouseEntered(e: MouseEvent?) {
                            color1 = Color(245, 245, 29)
                            color2 = Color(109, 109, 29)
                        }

                        override fun mouseExited(e: MouseEvent?) {
                            color1 = Color(245, 245, 245)
                            color2 = Color(102, 102, 102)
                        }
                    })
                }

                override fun paintComponent(g: Graphics) {
                    super.paintComponents(g)
                    val g2d = g as Graphics2D
                    g2d.paint = GradientPaint(0F, 0F, color1, 0F, 60F, color2)
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