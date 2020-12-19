package app

import lib.sRAD.gui.resource.*
import lib.sRAD.gui.sComponent.SButton
import lib.sRAD.gui.sComponent.SLabel
import lib.sRAD.gui.sComponent.SPanel
import lib.sRAD.gui.tool.setProperties
import server.Banco
import java.awt.Color
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.JTextField

abstract class LectorTarjeta: SPanel(900, 469, 340, 140, background = Color(43, 43, 43), border = blackBorder) {

    private var numeroTarjeta = ""

    init {
        val laser = SButton(2, 70, 336, 10, background = ta7, border = transparentBorder, backgroundEntered = mustard)
        laser.addActionListener {
            this@LectorTarjeta.parent.parent.parent.parent.isEnabled = false

            val ventana = JFrame()
            ventana.setProperties(450, 290, background = Color(245, 245, 245))

            val title = SLabel(150, 30, 150, 30, "Datos de tarjeta", fontTitle2, foreground = black)
            ventana.add(title)

            val num = SLabel(70, 90, 60, 20, "Numero", fontTitleMini, black)
            ventana.add(num)

            val btnCerrar = SButton(
                250, 195, 128, 50, "CERRAR", background = ta5, backgroundEntered = mustard, border = ta6Border, foreground = black
            )
            btnCerrar.addActionListener {
                this@LectorTarjeta.parent.parent.parent.parent.isEnabled = true
                ventana.dispose()
            }
            ventana.add(btnCerrar)

            if(numeroTarjeta == "") {
                val tfNum = JTextField()
                tfNum.setProperties(70, 115, 310, 25, background = white, foreground = black, hAlignment = JTextField.RIGHT)
                ventana.add(tfNum)

                val btnInsertar = SButton(
                    70,
                    195,
                    128,
                    50,
                    "INSERTAR",
                    background = ta5,
                    backgroundEntered = mustard,
                    border = ta6Border,
                    foreground = black
                )
                btnInsertar.addActionListener {
                    if (Banco.validarTarjeta(tfNum.text)) {
                        numeroTarjeta = tfNum.text
                        JOptionPane.showMessageDialog(
                            null, "Su tarjeta ha sido validada exitosamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE
                        )
                        this@LectorTarjeta.parent.parent.parent.parent.isEnabled = true
                        tarjetaIngresada()
                        ventana.dispose()
                    } else {
                        JOptionPane.showMessageDialog(
                            null, "La tarjeta ingresada no se pudo validar, verifique el valor ingresado", "ERROR",
                            JOptionPane.ERROR_MESSAGE
                        )
                    }
                }
                ventana.add(btnInsertar)
            }
            else {
                val lNum = SLabel(
                    70, 115, 310, 25, numeroTarjeta, background = white, foreground = black, hAlignment = JTextField.RIGHT
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
                    numeroTarjeta = ""
                    JOptionPane.showMessageDialog(
                        null, "Su tarjeta ha sido retirada exitosamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE
                    )
                    this@LectorTarjeta.parent.parent.parent.parent.isEnabled = true
                    tarjetaRetirada()
                    ventana.dispose()
                }
                ventana.add(btnRetirar)
            }

        }
        add(laser)
    }

    abstract fun tarjetaIngresada()

    abstract fun tarjetaRetirada()

}