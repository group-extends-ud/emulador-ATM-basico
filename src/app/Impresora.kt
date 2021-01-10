package app

import lib.sRAD.gui.resource.*
import lib.sRAD.gui.sComponent.SButton
import lib.sRAD.gui.sComponent.SLabel
import lib.sRAD.gui.sComponent.SPanel
import lib.sRAD.gui.tool.setProperties
import server.Banco
import java.awt.Color
import java.awt.GradientPaint
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.JTextArea

class Impresora: SPanel(110, 470, 240, 80) {

    private var factura = SButton(
        170, 514, 120, 60,"-----------", background = white, backgroundEntered = mustard, foreground = black
    )

    private var datosFactura = ""

    init {
        val pDecorador = SButton(30, 35, 180, 10,cursor = defaultCursor, background = Color(100, 118, 135))
        add(pDecorador)

        factura.addActionListener {
            this@Impresora.parent.parent.parent.parent.isEnabled = false

            val ventana = JFrame()
            ventana.setProperties(450, 490, background = Color(245, 245, 245))

            val title = SLabel(150, 30, 150, 30, "Factura", fontTitle2, foreground = black)
            ventana.add(title)

            val btnCerrar = SButton(
                250, 395, 128, 50, "CERRAR", background = ta5, backgroundEntered = mustard, border = ta6Border, foreground = black
            )
            btnCerrar.addActionListener {
                this@Impresora.parent.parent.parent.parent.isEnabled = true
                ventana.dispose()
            }
            ventana.add(btnCerrar)

            val datos = JTextArea()
            datos.setProperties(
                70, 85, 310, 270,false, background = Color(245, 245, 245), foreground =  black,
                text = datosFactura, border = null
            )
            ventana.add(datos)

            val btnRetirar = SButton(
                70,
                395,
                128,
                50,
                "RETIRAR",
                background = ta5,
                backgroundEntered = mustard,
                border = ta6Border,
                foreground = black
            )
            btnRetirar.addActionListener {
                this@Impresora.parent.parent.remove(factura)
                /*
                JOptionPane.showMessageDialog(
                    null, "Su factura ha sido retirada exitosamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE
                )*/
                playRetirarFactura()
                this@Impresora.parent.parent.parent.parent.isEnabled = true
                ventana.dispose()
                this@Impresora.parent.parent.repaint()
            }
            ventana.add(btnRetirar)
        }
    }

    override fun paintComponent(g: Graphics){
        super.paintComponents(g)
        val g2d = g as Graphics2D
        g2d.paint = GradientPaint(0F, 0F, Color(245, 245, 245), 0F, 80F, Color(102, 102, 102))
        g2d.fillRect(0, 0, 240, 80)
    }

    fun generarFactura() {
        playImprimirFactura()
        parent.parent.remove(factura)
        parent.parent.add(factura)
        parent.parent.repaint()
        actualizarFactura()
    }

    private fun actualizarFactura() {
        datosFactura = Banco.datosUltimaOperacion(numeroTarjeta)
    }

}