package com.fox.downloadwebcontent

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.fox.downloadwebcontent.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")

    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnDisplay.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val connection = URL(MAILRU).openConnection() as HttpURLConnection
                val data = connection.inputStream.bufferedReader().readText()
                val intent = DisplayUrlActivity.newIntent(this@MainActivity, data)
                startActivity(intent)
            }
        }

        binding.btnImage.setOnClickListener {
            getRandomImage()
        }
    }

    private fun getRandomImage() {
        GlideApp.with(this)
            .asBitmap()
            .load(IMAGES)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
//            .placeholder(R.drawable.ic_action_point)
            .into(binding.imageView)
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val UNSPLASH = "https://source.unsplash.com/random/800*600"

        private const val MAILRU = "https://mail.ru/"

        private const val IMAGES = "https://picsum.photos/200/300"
    }
}