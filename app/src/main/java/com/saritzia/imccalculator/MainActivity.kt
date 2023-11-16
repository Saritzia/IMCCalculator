package com.saritzia.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saritzia.imccalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var height = "0"
    private var weight = "0"
    private lateinit var gender : Gender
    private enum class Gender {
        HOMBRE,
        MUJER
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurateButtons()
    }
    private fun getHeight(){
        binding.heightEditText.setOnClickListener {
            height = binding.heightEditText.text.toString()
        }
    }
    private fun getWeight(){
        binding.weightEditText.setOnClickListener {
            height = binding.weightEditText.text.toString()
        }
    }
    private fun getGender(){
        binding.menRadioButton.setOnClickListener {
            gender = Gender.HOMBRE
        }
        binding.womenRadioButton.setOnClickListener {
            gender = Gender.MUJER
        }
    }
    private fun didTapValidateButton() {
        binding.validateButton.setOnClickListener {
            val intent = Intent(applicationContext, DetailActivity::class.java)
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)
            intent.putExtra("gender",gender)
            startActivity(intent)
        }
    }
    private fun configurateButtons(){
        getHeight()
        getWeight()
        getGender()
        didTapValidateButton()
    }
}