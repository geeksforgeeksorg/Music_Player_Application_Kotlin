package org.geeksforgeeks.music_player_kotlin

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var music: MediaPlayer
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    private lateinit var stopButton: Button
    private lateinit var songTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        playButton = findViewById(R.id.play_button)
        pauseButton = findViewById(R.id.pause_button)
        stopButton = findViewById(R.id.stop_button)
        songTitle = findViewById(R.id.song_title) // Initialize songTitle

        // Create MediaPlayer instance with the song
        music = MediaPlayer.create(this, R.raw.song)

        // Set up button listeners
        playButton.setOnClickListener {
            if (!music.isPlaying) {
                music.start()
                songTitle.text = "Playing: Song"
            }
        }

        pauseButton.setOnClickListener {
            if (music.isPlaying) {
                music.pause()
                songTitle.text = "Paused: Song"
            }
        }

        stopButton.setOnClickListener {
            if (music.isPlaying) {
                music.stop()
                music.reset()
                music = MediaPlayer.create(this, R.raw.song) // Reset the MediaPlayer to the song
                songTitle.text = "Stopped: Enjoyed The Song?"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (music.isPlaying) {
            music.stop()
        }
        music.release() // Release the MediaPlayer resources
    }
}
