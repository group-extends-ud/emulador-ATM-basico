package lib.sRAD.swingRAD.sComponents

import lib.sRAD.swingRAD.*
import java.awt.Color
import java.awt.Cursor
import java.awt.Font
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.Icon
import javax.swing.JButton
import javax.swing.SwingConstants
import javax.swing.border.Border

open class SButton: JButton {

    constructor()

    constructor(x: Int, y: Int, width: Int, height: Int, text: String? = "", cursor: Cursor? = handCursor, font: Font? = fontTitleMini,
                background: Color? = darkGray, foreground: Color? = darkWhite, border: Border? = semiDarkGray2Border,
                hAlignment: String? = "CENTER", isSolid: Boolean = true, backgroundEntered: Color = semiDarkGray
    ) {
        setProperties(x, y, width, height, text, cursor, font, background, foreground, border, hAlignment, isSolid, backgroundEntered)
    }

    /**
     * Icon button
     */
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun setProperties(x: Int, y: Int, icon: Icon?, cursor: Cursor? = handCursor) {
        this.setLocation(x, y)
        this.isContentAreaFilled = false
        this.border = null
        this.cursor = cursor
        this.isFocusable = false
        if (icon != null) {
            this.setSize(icon.iconWidth, icon.iconHeight)
            this.icon = icon
        }
    }

    /**
     * text button
     */
    fun setProperties(x: Int, y: Int, width: Int, height: Int, text: String? = "", cursor: Cursor? = handCursor, font: Font? = fontTitleMini,
                              background: Color? = darkGray, foreground: Color? = darkWhite, border: Border? = semiDarkGray2Border,
                              hAlignment: String? = "CENTER", isSolid: Boolean = true, backgroundEntered: Color = semiDarkGray
    ) {
        setProperties(x, y, width, height, cursor, background, isSolid)
        this.text = text
        this.font = font
        this.foreground = foreground
        this.border = border
        this.horizontalAlignment = when (hAlignment) {
            "LEFT" -> SwingConstants.LEFT
            "RIGHT" -> SwingConstants.RIGHT
            else -> SwingConstants.CENTER
        }

        this.addMouseListener(object : MouseListener {
            override fun mouseClicked(e: MouseEvent) {}

            override fun mousePressed(e: MouseEvent) {}

            override fun mouseReleased(e: MouseEvent) {}

            override fun mouseEntered(e: MouseEvent) {
                this@SButton.background = backgroundEntered
            }

            override fun mouseExited(e: MouseEvent) {
                this@SButton.background = background
            }
        })
    }

    /**
     * text button filled with icon
     */
    fun setProperties(x: Int, y: Int, width: Int, height: Int, cursor: Cursor?, background: Color?, isSolid: Boolean, icon: Icon? = null) {
        this.setProperties(x, y, icon, cursor)
        this.setSize(width, height)
        this.background = background
        this.isContentAreaFilled = isSolid
    }

}