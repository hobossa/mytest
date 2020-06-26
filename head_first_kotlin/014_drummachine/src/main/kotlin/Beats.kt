import java.io.File
import javax.sound.sampled.AudioSystem
import kotlinx.coroutines.*

suspend fun playBeats(beats: String, file: String) {
    val parts = beats.split("x")
    var count = 0
    for (part in parts) {
        count += part.length + 1
        if (part == "") {
            playSound(file)
        } else {
            //Thread.sleep(100 * (part.length + 1L))
            delay(100 * (part.length +1L))
            if (count < beats.length) {
                playSound(file)
            }
        }
    }
}

fun playSound(file: String) {
    val clip = AudioSystem.getClip()
    val audioInputStream = AudioSystem.getAudioInputStream(
        File(
            file
        )
    )
    clip.open(audioInputStream)
    clip.start()
}

// mark main with suspend so that it can call the playBeats function
suspend fun main(args: Array<String>) {
    // Launch a coroutine in the background.
    // GlobalScope.launch { playBeats("x-x-x-x-x-x-", "toms.aiff") }
    // playBeats("x-----x-----", "crash_cymbal.aiff")

    // if you want your code to run in the same thread but in the separate coroutines,
    // you can use the runBlocking function.
    // using delay in playBeats, so even these routines run in the same thread.
    // they can run "asynchronously"
    runBlocking {
        launch { playBeats("x-x-x-x-x-x-", "toms.aiff") }
        playBeats("x-----x-----", "crash_cymbal.aiff")
    }
}
