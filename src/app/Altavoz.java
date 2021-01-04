package app;

import lib.sRAD.logic.SClip;

class Altavoz {
    private final  static SClip cashRegisterSound = new SClip("resources/sound/cashRegister.wav"),
     clickSound = new SClip("resources/sound/clickSound.wav"),
     keyboardReleaseSound = new SClip("resources/sound/KeyboardReleaseSound.wav"),
     retirarFacturaSound = new SClip("resources/sound/retirarFactura.wav"),
     winXpErrorSound = new SClip("resources/sound/winXpErrorSound.wav"),
     keyboardPressSound = new SClip("resources/sound/KeyboardPressSound.wav");

    static void playKeyboardPress() {
        keyboardPressSound.play();
    }

    static void playCashRegister() {
        cashRegisterSound.play();
    }

    static void playClickSound() {
        clickSound.play();
    }

    static void playKeyboardRelease() {
        keyboardReleaseSound.play();
    }

    static void playRetirarFactura() {
        retirarFacturaSound.play();
    }

    void playWinXpErrorSound() {
        winXpErrorSound.play();
    }

}