package com.tolgahantutar.bexworkfloww

import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.text.TextUtils
import android.view.View

class MyInputMethodService : KeyboardView.OnKeyboardActionListener, InputMethodService() {

    override fun onCreateInputView(): View {
        val keyboardView: KeyboardView = layoutInflater.inflate(R.layout.keyboard_view,null) as KeyboardView
        val keyboard = Keyboard(this,R.xml.number_pad)
        keyboardView.keyboard = keyboard
        keyboardView.setOnKeyboardActionListener(this)
        return keyboardView
    }

    override fun onPress(p0: Int) {

    }

    override fun onRelease(p0: Int) {

    }

    override fun onKey(primatyCode: Int, keyCodes: IntArray?) {
        val inputConnection = currentInputConnection
        if (inputConnection != null){
            when(primatyCode){
                Keyboard.KEYCODE_DELETE -> {
                    val selectedText: CharSequence = inputConnection.getSelectedText(0)

                    if(TextUtils.isEmpty(selectedText)){
                            inputConnection.deleteSurroundingText(1,0)
                    }else{
                        inputConnection.commitText("",1)
                    }
                }else ->{
                val code: Char = primatyCode as Char
                inputConnection.commitText(code.toString(),1)
            }
            }
        }
    }

    override fun onText(p0: CharSequence?) {
    }

    override fun swipeLeft() {
    }

    override fun swipeRight() {
    }

    override fun swipeDown() {
    }

    override fun swipeUp() {
    }
}