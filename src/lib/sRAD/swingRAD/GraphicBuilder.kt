package lib.sRAD.swingRAD

import lib.sRAD.swingRAD.mainBar.MainBar
import lib.sRAD.swingRAD.sComponents.SLabel
import java.awt.*
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.*
import javax.swing.border.Border
import javax.swing.table.DefaultTableModel

//JFrame-----------------------------------------------------------------------------------------
fun JFrame.setProperties(width: Int = 1280, height: Int = 720, background: Color? = megaDarkGray, undecorated: Boolean = true,
         border: Border? = blackBorderTransparent, relativeLocation: Component? = null, visible: Boolean = true,
         layout: LayoutManager? = null) {
    this.setSize(width, height)
    this.setLocationRelativeTo(relativeLocation)
    this.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    this.contentPane.background = background
    this.isUndecorated = undecorated
    this.rootPane.border = border
    this.layout = layout
    this.isVisible = visible
}

fun JFrame.setMainBar(title: String, pathLogo: String = "resources/exampleLogo.png") {
    val mainBar = MainBar(this)
    mainBar.setTitle(title)
    mainBar.setLogo(ImageIcon(pathLogo))
    this.add(mainBar)
}

fun JFrame.setBackground(path: String) {
    val background = SLabel(0, 0, ImageIcon(path))
    this.add(background)
}

//JTextArea-----------------------------------------------------------------------------------------
fun JTextArea.setProperties(x: Int, y: Int, width: Int, height: Int, editable: Boolean = true, lineWrap: Boolean = true, text: String? = "",
                            foreground: Color? = darkWhite, background: Color? = darkGray, font: Font? = fontText, border: Border? = semiDarkGray2Border) {
    this.setBounds(x, y, width, height)
    this.text = text
    this.isEditable = editable
    this.foreground = foreground
    this.font = font
    this.background = background
    this.caretColor = foreground
    this.border = border
    this.wrapStyleWord = lineWrap
    this.lineWrap = lineWrap
}

//JTextField-----------------------------------------------------------------------------------------
fun JTextField.setProperties(x: Int, y: Int, width: Int, height: Int = 28, editable: Boolean = true, text: String? = "", foreground: Color? = darkWhite,
                             background: Color? = darkGray, font: Font? = fontText, border: Border? = semiDarkGray2Border,
                             hAlignment: Int = JTextField.LEFT) {
    this.setBounds(x, y, width, height)
    this.text = text
    this.isEditable = editable
    this.foreground = foreground
    this.font = font
    this.background = background
    this.caretColor = foreground
    this.border = border
    this.horizontalAlignment = hAlignment
}

//JScrollPane----------------------------------------------------------------------------------------
fun JScrollPane.setProperties(x: Int, y: Int, width: Int, height: Int, background: Color? = semiDarkGrayBlue, border: Border? = semiDarkGray2Border){
    this.setBounds(x, y, width, height)
    this.verticalScrollBar.setUI(getCustomScroll())
    this.horizontalScrollBar.setUI(getCustomScroll())
    this.background = background
    this.border = border
}
