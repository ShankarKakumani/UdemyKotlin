package com.shankar.udemykotlin.music_player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.ActivityMusicPlayerBinding

class MusicPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMusicPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMusicPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}