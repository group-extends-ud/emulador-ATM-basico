package app

import lib.sRAD.logic.SClip

private val cashRegisterSound = SClip("resources/sound/cashRegister.wav")
private val clickSound = SClip("resources/sound/clickSound.wav")
private val keyboardReleaseSound = SClip("resources/sound/KeyboardReleaseSound.wav")
private val retirarFacturaSound = SClip("resources/sound/retirarFactura.wav")
private val winXpErrorSound = SClip("resources/sound/winXpErrorSound.wav")
private val keyboardPressSound = SClip("resources/sound/KeyboardPressSound.wav")
private val imprimirFactura = SClip("resources/sound/imprimirFactura.wav")

fun playImprimirFactura() {
    imprimirFactura.play()
}
fun playKeyboardPress() {
    keyboardPressSound.play()
}

fun playCashRegister() {
    cashRegisterSound.play()
}

fun playClickSound() {
    clickSound.play()
}

fun playKeyboardRelease() {
    keyboardReleaseSound.play()
}

fun playRetirarFactura() {
    retirarFacturaSound.play()
}

fun playWinXpErrorSound() {
    winXpErrorSound.play()
}
