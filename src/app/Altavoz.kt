package app

import lib.sRAD.logic.sComponent.SClip

class Altavoz {
    companion object {

        var sonido = true

        private val cashRegisterSound = SClip("resources/sound/cashRegister.wav")
        private val clickSound = SClip("resources/sound/clickSound.wav")
        private val keyboardReleaseSound = SClip("resources/sound/KeyboardReleaseSound.wav")
        private val retirarFacturaSound = SClip("resources/sound/retirarFactura.wav")
        private val winXpErrorSound = SClip("resources/sound/winXpErrorSound.wav")
        private val keyboardPressSound = SClip("resources/sound/KeyboardPressSound.wav")
        private val imprimirFactura = SClip("resources/sound/imprimirFactura.wav")

        fun playImprimirFactura() {
            if(sonido)
                imprimirFactura.play()
        }

        fun playKeyboardPress() {
            if(sonido)
                keyboardPressSound.play()
        }

        fun playCashRegister() {
            if(sonido)
                cashRegisterSound.play()
        }

        fun playClickSound() {
            if(sonido)
                clickSound.play()
        }

        fun playKeyboardRelease() {
            if(sonido)
                keyboardReleaseSound.play()
        }

        fun playRetirarFactura() {
            if(sonido)
                retirarFacturaSound.play()
        }

        fun playWinXpErrorSound() {
            if(sonido)
                winXpErrorSound.play()
        }

    }
}
