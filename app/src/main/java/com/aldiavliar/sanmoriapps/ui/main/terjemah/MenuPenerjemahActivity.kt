package com.aldiavliar.sanmoriapps.ui.main.terjemah

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.ui.dataMorse.MorseUtils
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MenuPenerjemahActivity : AppCompatActivity() {

    private lateinit var morseTextEditText: TextInputEditText
    private lateinit var alphaNumericTextEditText: TextInputEditText
    private lateinit var morseTextField: TextInputLayout
    private lateinit var alphaNumericTextField: TextInputLayout
    private lateinit var back : ImageButton
    private var pesanMorse: String = ""
    private var pesanAlphaNumeric: String = ""
    private lateinit var dotButton : FloatingActionButton
    private lateinit var dashButton : FloatingActionButton
    private lateinit var spaceButton : ExtendedFloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_penerjemah)

        back =  findViewById(R.id.onPressedBack)
        back.setOnClickListener {
            super.onBackPressed()
        }

        setAlphaNumericField()
        setMorseField()

        dotButton = findViewById(R.id.dotButton)
        dotButton.setOnClickListener{
            hideSoftKeyboard()

            pesanMorse = pesanMorse.plus("•")
            morseTextField.editText?.text = Editable.Factory.getInstance().newEditable(pesanMorse)
            decoding(pesanMorse)
        }

        dashButton = findViewById(R.id.dashButton)
        dashButton.setOnClickListener {
            hideSoftKeyboard()

            pesanMorse = pesanMorse.plus("–")
            morseTextField.editText?.text = Editable.Factory.getInstance().newEditable(pesanMorse)
            decoding(pesanMorse)
        }

        alphaNumericTextField = findViewById(R.id.alphaNumericTextField)
        morseTextEditText = findViewById(R.id.morseTextEditText)
        alphaNumericTextEditText = findViewById(R.id.alphaNumericTextEditText)
        spaceButton = findViewById(R.id.spaceButton)
        spaceButton.setOnClickListener {
            hideSoftKeyboard()

            pesanMorse = pesanMorse.plus(" ")
            morseTextField.editText?.text = Editable.Factory.getInstance().newEditable(pesanMorse)
            alphaNumericTextEditText.setText(alphaNumericTextField.editText?.text.toString().plus(" "))
            decoding(pesanMorse)
        }

    }

    private fun hideSoftKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    private fun decoding(inputText: String) {
        var translatedString = ""

        for (morseCharacter in inputText.split(" ")) {
            val decodedBit = MorseUtils().decodeTranslatableBit(morseCharacter)
            translatedString += decodedBit
        }

        alphaNumericTextEditText.setText(translatedString)
    }

    private fun setMorseField() {
        morseTextField = findViewById(R.id.morseTextField)
        morseTextEditText = findViewById(R.id.morseTextEditText)
        morseTextEditText.isEnabled = false

        morseTextField.setEndIconOnClickListener {
            clearField()
        }
    }

    private fun clearField() {
        pesanMorse = ""
        morseTextField.editText?.text?.clear()
        alphaNumericTextField.editText?.text?.clear()
    }

    private fun setAlphaNumericField() {
        morseTextEditText = findViewById(R.id.morseTextEditText)
        alphaNumericTextField = findViewById(R.id.alphaNumericTextField)
        alphaNumericTextField.editText?.doOnTextChanged { inputText, _, _, _ ->

            var translatedString = ""
            for (character in inputText.toString().toCharArray()) {
                val morseCharacter = MorseUtils().getMorseValue(character)
                translatedString += morseCharacter.plus(" ")
            }

            morseTextEditText.setText(translatedString)
        }

        alphaNumericTextField.editText?.doAfterTextChanged {
            alphaNumericTextEditText.setSelection(alphaNumericTextEditText.text?.length ?: 0)
        }

        alphaNumericTextField.setEndIconOnClickListener {
            clearField()
        }
    }
}