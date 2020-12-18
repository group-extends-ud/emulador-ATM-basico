package lib.sRAD.gui.sComponent

import lib.sRAD.gui.resource.semiDarkGray2Border
import lib.sRAD.gui.resource.semiDarkGrayBlue
import java.awt.Color
import java.awt.LayoutManager
import javax.swing.JPanel
import javax.swing.border.Border

open class SPanel: JPanel {

    constructor(x: Int=0, y: Int=0, width: Int=0, height: Int=0, background: Color? = semiDarkGrayBlue, border: Border? = semiDarkGray2Border,
                layout: LayoutManager? = null) {
        setProperties(x, y, width, height, background, border, layout)
    }

    constructor()

    fun setProperties(x: Int=0, y: Int=0, width: Int=0, height: Int=0, background: Color? = semiDarkGrayBlue, border: Border? = semiDarkGray2Border,
                      layout: LayoutManager? = null) {
        this.setBounds(x, y, width, height)
        this.background = background
        this.border = border
        this.layout = layout
    }
}