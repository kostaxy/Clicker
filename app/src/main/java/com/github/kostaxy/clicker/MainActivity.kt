package com.github.kostaxy.clicker

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    // name settings-file
    val APP_PREFERENCES = "mysettings"
    val APP_PREFERENCES_COUNTER = "counter"

    lateinit var preferences:  SharedPreferences

    var count = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        btn_add.setOnClickListener(){
            count++
            tv_count.text = (count.toString())
        }
    }

    override fun onPause() {
        super.onPause()

        //Save count
        val editor = preferences.edit();
        editor.putInt(APP_PREFERENCES_COUNTER, count)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()

        //load settings
        if(preferences.contains(APP_PREFERENCES_COUNTER)){
            count = preferences.getInt(APP_PREFERENCES_COUNTER,0)
            tv_count.text = count.toString();
        }
    }
}
