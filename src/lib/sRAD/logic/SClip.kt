package lib.sRAD.logic

import java.io.File
import javax.sound.sampled.*

class SClip {
    private var playCompleted = false
    private val audioClip: Clip
    private val audioStream: AudioInputStream

    //clip method
    constructor(path: String) {
        //create an AudioInputStream from a given sound file
        val audioFile = File(path)
        audioStream = AudioSystem.getAudioInputStream(audioFile)

        //acquire audio format and create a DataLine.Info object
        val format = audioStream.format
        val info = DataLine.Info(Clip::class.java, format)

        //obtain the Clip
        audioClip = AudioSystem.getLine(info) as Clip
        audioClip.addLineListener { event ->
            if (event.type == LineEvent.Type.STOP) {
                playCompleted = true
            }
        }
    }

    fun play() {
        //open the AudioInputStream and start playing
        playCompleted = false
        audioClip.open(audioStream)
        audioClip.start()

        //when finish...
        while (!playCompleted) {
            // wait for the playback completes
            try {
                Thread.sleep(500)
            } catch (ex: InterruptedException) {
                ex.printStackTrace()
            }
        }

        //close and release resources acquired
        audioClip.close()
        audioStream.close()
    }

}