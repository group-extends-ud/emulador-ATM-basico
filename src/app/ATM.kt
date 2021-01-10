package app

import lib.sRAD.gui.resource.*
import lib.sRAD.gui.sComponent.SButton
import lib.sRAD.gui.sComponent.SLabel
import lib.sRAD.gui.tool.setProperties
import server.Banco
import java.awt.*
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JTextArea
import javax.swing.JTextField
import kotlin.system.exitProcess

class ATM: JFrame() {
    val window = Window()
    private val impresora = Impresora()
    private val dispensador = Dispensador()

    init {
        createATM()
        addWindow()
        addOptionButtons()
        addKeyBoard()
        addImpresora()
        addDispensador()
        addLectorTarjeta()
        addBExit()

        val btHelp = object: SButton() {
            var color1 = Color(245, 245, 245)
            var color2 = Color(102, 102, 102)

            init {
                addMouseListener(object: MouseListener {
                    override fun mouseClicked(e: MouseEvent?) { }

                    override fun mousePressed(e: MouseEvent?) { }

                    override fun mouseReleased(e: MouseEvent?) { }

                    override fun mouseEntered(e: MouseEvent?) {
                        playKeyboardRelease()
                        color1 = Color(29, 245, 29)
                        color2 = Color(29, 109, 29)
                    }

                    override fun mouseExited(e: MouseEvent?) {
                        color1 = Color(245, 245, 245)
                        color2 = Color(102, 102, 102)
                    }
                })

                val lHelp = SLabel(7, 20, 50, 20, "HELP", foreground = black)
                add(lHelp)

                layout = null
            }

            override fun paintComponent(g: Graphics){
                super.paintComponents(g)
                val g2d = g as Graphics2D
                g2d.paint = GradientPaint(0F, 0F, color1, 0F, 60F, color2)
                g2d.fillRect(0, 0, 60, 60)
            }
        }
        btHelp.setProperties(1120, 629, 60, 60)
        btHelp.addActionListener { openPopUpHelp() }
        add(btHelp)

        setProperties()
    }

    private fun openPopUpHelp() {
        isEnabled = false

        val ventana = JFrame()
        ventana.setProperties(600, 520, background = Color(245, 245, 245))

        //acerca de
        val title = SLabel(250, 30, 150, 30, "Acerca de", fontTitle2, foreground = black)
        ventana.add(title)

        val text = JTextArea()
        text.setProperties(40, 60, 530, 40, false,
            text = "El emulador ATM básico es un simulador básico de un cajero automático (ATM en inglés).\n",
            font = fontTitleMini, foreground =  black, background = null, border = null
        )
        ventana.add(text)

        //requisitos
        val title1 = SLabel(250, 100, 150, 30, "Requisitos", fontTitle2, foreground = black)
        ventana.add(title1)

        val text1 = JTextArea()
        text1.setProperties(40, 130, 530, 40, false,
            text = "Para usarlo deberá tener al menos una cuenta y tarjeta registradas en la API desarrollada para simular el banco.\n",
            font = fontTitleMini, foreground =  black, background = null, border = null
        )
        ventana.add(text1)

        //Pasos para realizar una operación
        val title2 = SLabel(215, 170, 250, 30, "Realizar operación", fontTitle2, foreground = black)
        ventana.add(title2)

        val text2 = JTextArea()
        text2.setProperties(40, 200, 530, 120, false,
            text =  "1) Primero ingrese la tarjeta (haciendo clic en el láser ubicado a la derecha del teclado y digitando los datos).\n" +
                    "2) Seleccione operación (con los seis botones ubicados al rededor de la ventana del ATM).\n" +
                    "3) Realice su transacción (con el uso del teclado y los botones de opciones).\n" +
                    "4) Retire su tarjeta o seleccione la opción \"No\" cuando le aparezca \"¿Desea realizar otra operación?\".\n",
            font = fontTitleMini, foreground =  black, background = null, border = null
        )
        ventana.add(text2)

        //Créditos
        val title3 = SLabel(250, 320, 250, 30, "Créditos", fontTitle2, foreground = black)
        ventana.add(title3)

        val text3 = JTextArea()
        text3.setProperties(40, 350, 530, 70, false,
            text =  "Jean Carlos Santoya Cabrera - 20191020156.\n" +
                    "Luis Alejandro Forigua Rojas - 20171020115.\n" +
                    "Jesús Manuel Leiva Bermúdez - 20191020132.\n",
            font = fontTitleMini, foreground =  black, background = null, border = null
        )
        ventana.add(text3)


        val btnCerrar = SButton(
            230, 435, 128, 50, "CERRAR", background = ta5, backgroundEntered = mustard, border = ta6Border, foreground = black
        )
        btnCerrar.addActionListener {
            isEnabled = true
            ventana.dispose()
        }
        btnCerrar.addMouseListener(buttonListener)
        ventana.add(btnCerrar)

    }

    private fun addDispensador() {
        add(dispensador)
    }

    private fun addImpresora() {
        add(impresora)
    }

    private fun addKeyBoard() {
        add(object: KeyBoard() {
            override fun pressCancel() {
                if(window.current != Current.Bienvenido && window.current != Current.Operacion) {
                    window.current = Current.Operacion
                }
            }

            override fun pressDel() {
                if (window.current == Current.Password || window.current == Current.CustomMonto) {
                    window.removePoint()
                }
            }

            override fun pressNumber(num: Int) {
                if (window.current == Current.Password || window.current == Current.CustomMonto) {
                    window.addPoint(num)
                }
            }

            override fun pressEnter() {
                if(window.current == Current.Password) {
                    window.validar()
                }
                if(window.current == Current.CustomMonto) {
                    verificarDisponibilidadSaldo(window.obtenerSaldo())
                }
            }
        })
    }

    private fun verificarDisponibilidadSaldo(saldo: Int) {
        /*
        if(saldo <= 0) {
            JOptionPane.showMessageDialog(null, "Valor ingresado inválido", "ERROR", JOptionPane.ERROR_MESSAGE)
        }
        else if(Banco.verificarDisponibilidadSaldo(saldo)) {
         */
        if(saldo>0 && Banco.verificarDisponibilidadSaldo(saldo)) {
            Banco.retirar(saldo)
            playCashRegister()
            efectivo += saldo
            dispensador.actualizarEfectivo()
            window.current = Current.Factura
        }
        else {
            /*
            JOptionPane.showMessageDialog(
                null, "No posee el saldo requerido para el retiro", "Mensaje", JOptionPane.INFORMATION_MESSAGE
            )
             */
            playWinXpErrorSound()
            window.current = Current.Operacion
        }
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
                            color1 = mustard
                            color2 = Color(109, 109, 29)
                        }

                        override fun mouseExited(e: MouseEvent?) {
                            color1 = Color(245, 245, 245)
                            color2 = Color(102, 102, 102)
                        }
                    })
                    addActionListener { option(i) }
                }

                override fun paintComponent(g: Graphics) {
                    super.paintComponents(g)
                    val g2d = g as Graphics2D
                    g2d.paint = GradientPaint(0F, 0F, color1, 0F, 70F, color2)
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

    private fun option(opcion: Int) {
        if(window.current == Current.Operacion) {
            if (opcion == 0) {
                window.next = Current.Monto
                window.current = Current.Password
            }
        }
        else if (window.current == Current.Monto) {
            when (opcion) {
                0 -> verificarDisponibilidadSaldo(10000)
                1 -> verificarDisponibilidadSaldo(50000)
                2 -> verificarDisponibilidadSaldo(200000)
                3 -> verificarDisponibilidadSaldo(400000)
                4 -> verificarDisponibilidadSaldo(600000)
                5 -> window.current = Current.CustomMonto
            }
        }
        else if (window.current == Current.Factura) {
            if (opcion == 0){
                impresora.generarFactura()
                window.current = Current.Final
            }
            else if (opcion == 3) {
                window.current = Current.Final
            }
        }
        else if (window.current == Current.Final) {
            if (opcion == 0){
                window.current = Current.Operacion
            }
            else if (opcion == 3) {
                numeroTarjeta = ""
                /*
                JOptionPane.showMessageDialog(
                    null, "Su sesión ha finalizado exitosamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE
                )
                 */
                window.current = Current.Bienvenido
            }
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

    private fun addBExit() {
        val bExit = object: SButton() {
            var color1 = Color(245, 245, 245)
            var color2 = Color(102, 102, 102)

            init {
                addMouseListener(object: MouseListener {
                    override fun mouseClicked(e: MouseEvent?) { }

                    override fun mousePressed(e: MouseEvent?) { }

                    override fun mouseReleased(e: MouseEvent?) { }

                    override fun mouseEntered(e: MouseEvent?) {
                        playKeyboardRelease()
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
        add(object: LectorTarjeta() {
            override fun tarjetaIngresada() {
                window.current = Current.Operacion
            }

            override fun tarjetaRetirada() {
                window.current = Current.Bienvenido
            }
        })
    }

    private fun addWindow() {
        add(window)
    }

}