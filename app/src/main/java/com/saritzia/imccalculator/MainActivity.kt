package com.saritzia.imccalculator

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.saritzia.imccalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var gender = ""
    private lateinit var group : RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        group = findViewById(binding.radioGroup1.id)
        didTapValidateButton()
    }
    private fun getGender(){
        gender = if(group.checkedRadioButtonId == binding.menRadioButton.id){
            "Hombre"
        }else{
            "Mujer"
        }
    }
    private fun didTapValidateButton() {
        binding.validateButton.setOnClickListener {
            val height = binding.heightEditText.text.toString()
            val weight = binding.weightEditText.text.toString()
            getGender()
            if(height.isEmpty() || weight.isEmpty() || gender.isEmpty()) {
                createSnackBar()
            }else{
                navigateToNextActivity(height,weight,gender)
            }
        }
    }
    private fun createSnackBar(){
        Snackbar.make(binding.root, "Rellene los campos, por favor",Snackbar.LENGTH_LONG).show()
    }
    private fun navigateToNextActivity(height: String,weight: String, gender: String){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("height", height)
        intent.putExtra("weight", weight)
        intent.putExtra("gender",gender)
        startActivity(intent)
    }
}