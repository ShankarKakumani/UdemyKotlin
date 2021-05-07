package com.shankar.udemykotlin.music_player

import android.content.pm.PackageManager
import android.database.Cursor
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.ActivityMusicPlayerBinding
import java.util.concurrent.TimeUnit

class MusicPlayerActivity : AppCompatActivity(), ItemClick {

    override fun itemClicked(position: Int) {

        mediaPlayer?.stop()
        this.currPosition = position
        play(currPosition)
    }

    companion object {
        const val REQUEST_CODE_READ_STORAGE = 34
    }

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var musicList: MutableList<Music>
    private lateinit var binding: ActivityMusicPlayerBinding

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var musicAdapter: MusicAdapter

    private var currPosition: Int = 0
    private var state = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMusicPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        musicList = mutableListOf()

        linearLayoutManager = LinearLayoutManager(this)
        musicAdapter = MusicAdapter(musicList, this)

        binding.musicRecyclerView.layoutManager = linearLayoutManager
        binding.musicRecyclerView.adapter = musicAdapter


        if (Build.VERSION.SDK_INT >= 23) {
            checkPermissions()
        }

        binding.fabPlay.setOnClickListener {
            play(currPosition)
        }
        binding.fabNext.setOnClickListener {
            mediaPlayer?.stop()
            state = false

            if(currPosition < musicList.size - 1)
            {
                currPosition += 1
            }
            play(currPosition)
        }


        binding.fabPrevious.setOnClickListener {
            mediaPlayer?.stop()
            state = false

            if(currPosition > 0) {
                currPosition -= 1
            }

            play(currPosition)
        }

        binding.seekBar.apply {

            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser){
                        mediaPlayer?.seekTo(progress*1000)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }

            })
        }

    }

    private fun play(currPosition: Int) {

        if (!state) {

            binding.fabPlay.setImageDrawable(resources.getDrawable(R.drawable.ic_stop, null))

            state = true
            mediaPlayer = MediaPlayer().apply {

                setAudioStreamType(AudioManager.STREAM_MUSIC)
                setDataSource(this@MusicPlayerActivity, Uri.parse(musicList[currPosition].songUri))
                prepare()
                start()
            }

            val handler = Handler()
            this@MusicPlayerActivity.runOnUiThread(object : Runnable {
                override fun run() {
                    val playerPosition = mediaPlayer?.currentPosition!! / 1000
                    val totalDuration = mediaPlayer?.duration!! / 1000

                    binding.seekBar.apply {
                        max = totalDuration
                        progress = playerPosition
                    }

                    binding.pastTextView.text = timerFormat(playerPosition.toLong())
//                    binding.remainTextView.text = timerFormat((totalDuration - playerPosition).toLong())
                    binding.remainTextView.text = timerFormat(totalDuration.toLong())
                    handler.postDelayed(this, 1000)

                }

            })
        } else {
            state = false
            mediaPlayer?.stop()
            binding.fabPlay.setImageDrawable(resources.getDrawable(R.drawable.ic_play_arrow, null))

        }
    }

    private fun timerFormat(time: Long): String {

        val result = String.format(
            "%02d:%02d", TimeUnit.SECONDS.toMinutes(time),
            TimeUnit.SECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(time)))

        var convert = ""

        for (element in result) {
            convert += element
        }

        return convert
    }

    private fun getSongs() {
        val selection = MediaStore.Audio.Media.IS_MUSIC
        val projection = arrayOf(
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA
        )

        val cursor: Cursor? = contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )

        while (cursor!!.moveToNext()) {

            musicList.add(Music(cursor.getString(0), cursor.getString(1), cursor.getString(2)))
        }

        cursor.close()
        musicAdapter.notifyDataSetChanged()
    }

    private fun checkPermissions() {

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getSongs()
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                Toast.makeText(this, "Music player needs access to your files", Toast.LENGTH_SHORT)
                    .show()
            }
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE_READ_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when (requestCode) {
            REQUEST_CODE_READ_STORAGE -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getSongs()
            } else {
                Toast.makeText(this, "Permission is not granted", Toast.LENGTH_SHORT).show()
            }

            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }


}