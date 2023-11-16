package com.saritzia.imccalculator

import android.os.Bundle
import android.text.Editable
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.saritzia.imccalculator.databinding.ActivityDetailBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.pow

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    private var height = "0"
    private var weight = "0"
    private var imc = 0.0
    private var gender = ""
    private lateinit var group : RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        group = findViewById(binding.radioGroup1.id)
        configurateActivityTexts()
        didTapCalculateButton()
}

    private fun configurateActivityTexts() {
        height = intent.extras?.getString("height") ?: ""
        weight = intent.extras?.getString("weight") ?: ""
        gender = intent.extras?.getString("gender") ?: ""
        binding.heightEditText.setText(height)
        binding.weightEditText.setText(weight)
        binding.menRadioButton.isChecked = gender.equals("Hombre",true)
        binding.womenRadioButton.isChecked = gender.equals("Mujer",true)
    }
    private fun getHeight(){
        height = binding.heightEditText.text.toString()
    }
    private fun getWeight(){
        weight = binding.weightEditText.text.toString()
    }
    private fun getGender(){
        if(group.checkedRadioButtonId == binding.menRadioButton.id){
            gender = "Hombre"
        }else{
            gender = "Mujer"
        }
    }
    private fun calculateIMC(){
        imc = weight.toDouble()/(height.toDouble().pow(2))
    }
    private fun didTapCalculateButton() {
        binding.validateButton.setOnClickListener {
            getHeight()
            getWeight()
            getGender()
            calculateIMC()
            var text = "Su IMC=${String.format("%.2f",imc)}"
            binding.imcText.text = text
            setTextDependingOnIMC()
            setImageDependingOnIMC()
        }
    }

    private fun setTextDependingOnIMC(){
        if(gender.equals("Hombre",true)){
            binding.descriptionText.text = when(imc){
                in 0.0 ..18.4 -> "Bajo peso"
                in 18.5..24.9 -> "Peso normal"
                in 25.0..29.9 -> "Sobrepeso"
                in 30.0..34.9 -> "Obesidad leve"
                in 35.0..39.9 -> "Obesidad moderada"
                else -> "Obesidad severa"
            }
        } else {
            binding.descriptionText.text = when(imc){
                in 0.0 ..16.4 -> "Bajo peso"
                in 16.5..22.9 -> "Peso normal"
                in 23.0..25.9 -> "Sobrepeso"
                in 26.0..30.9 -> "Obesidad leve"
                in 31.0..33.9 -> "Obesidad moderada"
                else-> "Obesidad severa"
            }
        }
    }
    private fun setImageDependingOnIMC(){
        val image = binding.imageView
        if(gender.equals("Hombre",true)){
            when(imc){
                in 0.0 ..18.4 -> image.setImageResource(R.drawable.estado1)
                in 18.5..24.9 -> image.setImageResource(R.drawable.estado2)
                in 25.0..29.9 -> image.setImageResource(R.drawable.estado1)
                in 30.0..34.9 -> image.setImageResource(R.drawable.estado6)
                in 35.0..39.9 -> image.setImageResource(R.drawable.estado5)
                else -> image.setImageResource(R.drawable.estado3)
            }
        } else {
            when(imc){
                in 0.0 ..16.4 -> image.setImageResource(R.drawable.estado1)
                in 16.5..22.9 -> image.setImageResource(R.drawable.estado2)
                in 23.0..25.9 -> image.setImageResource(R.drawable.estado1)
                in 26.0..30.9 -> image.setImageResource(R.drawable.estado6)
                in 31.0..33.9 -> image.setImageResource(R.drawable.estado5)
                else-> image.setImageResource(R.drawable.estado3)
            }
        }
    }
}