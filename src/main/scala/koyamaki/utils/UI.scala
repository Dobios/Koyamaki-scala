package koyamaki.utils

import scala.swing._

class UI(width: Int, height: Int) extends MainFrame {
    title = "GUI Program #1"
    preferredSize = new Dimension(width, height)
    contents = new Label("Here is the contents!")
}