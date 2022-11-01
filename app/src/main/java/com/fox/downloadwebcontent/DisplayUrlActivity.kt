package com.fox.downloadwebcontent

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fox.downloadwebcontent.databinding.ActivityDisplayUrlBinding

class DisplayUrlActivity : AppCompatActivity() {
    private var _binding: ActivityDisplayUrlBinding? = null
    private val binding get() = _binding?: throw RuntimeException("ActivityDisplayUrlBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDisplayUrlBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvDisplay.text = intent.getStringExtra(EXTRA)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val EXTRA = "extra"

        fun newIntent(context: Context, string: String): Intent {
            return Intent(context, DisplayUrlActivity::class.java).apply{
                putExtra(EXTRA,string)
            }
        }
    }
}