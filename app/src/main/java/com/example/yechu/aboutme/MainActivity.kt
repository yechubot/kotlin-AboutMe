package com.example.yechu.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.yechu.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //data binding 의 사용
    private lateinit var binding: ActivityMainBinding // 이름 : activity main + binding

    private val myName: MyName = MyName("Yeeun Kim")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName

//        findViewById<Button>(R.id.done_btn).setOnClickListener {
//            addNickname(it) // it -> refers to the view ( the argument passed )
//        }
        binding.doneBtn.setOnClickListener { addNickname(it) }
    }

    private fun addNickname(view: View) {
        //data binding  사용 전
        /*
                val editText = findViewById<EditText>(R.id.name_edit)
               val nicknameText = findViewById<TextView>(R.id.nickname_text)

               nicknameText.text = editText.text
               editText.visibility = View.GONE
               nicknameText.visibility = View.VISIBLE
               view.visibility = View.GONE
               */

        // data binding 사용 후
        binding.apply {
            myName?.nickname = nameEdit.text.toString()
            invalidateAll() //Invalidates all binding expressions and requests a new rebind to refresh UI.
            nameEdit.visibility = View.GONE
            doneBtn.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        // hide the keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}