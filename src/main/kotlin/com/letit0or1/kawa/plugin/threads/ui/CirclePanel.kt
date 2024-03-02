package com.letit0or1.kawa.plugin.threads.ui

import java.awt.Graphics
import javax.swing.JPanel

class CirclePanel : JPanel() {
    override fun paintComponent(g: Graphics) {
        //Adding  super.paintComponent....
        super.paintComponent(g)

        g.drawOval(0, 0, g.clipBounds.width, g.clipBounds.height)
    }
}
