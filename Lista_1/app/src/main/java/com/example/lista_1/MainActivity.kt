package com.example.lista_1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lista_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //private val showCount: TextView by lazy{findViewById(R.id.show_count)}
    //private val increaseButton: Button by lazy { findViewById(R.id.increase_button) }
    private var count = 0

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter_state", count)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        if (savedInstanceState != null)
            count = savedInstanceState.getInt("counter_state")

        //showCount.text = count.toString()
        binding.showCount.text = count.toString()

        //increaseButton.setOnClickListener {
        binding.increaseButton.setOnClickListener {
            count++
            //showCount.text = count.toString()
            binding.showCount.text = count.toString()
        }
    }




}