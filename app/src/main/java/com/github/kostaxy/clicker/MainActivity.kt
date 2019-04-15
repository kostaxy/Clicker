package com.github.kostaxy.clicker

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
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

        btn_reset.setOnClickListener(){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Reset score")
            builder.setMessage("Are you really ready to reset score?")
                .setPositiveButton("Yes, I am cool " + ("\ud83d\ude0e"), { _, _ -> resetScore()})
                .setNegativeButton("No no no! ", { dialog, _ -> dialog.cancel()})

            val alert: AlertDialog = builder.create()
            alert.show()
        }
    }


    private fun resetScore() {
        count = 0
        tv_count.text = "0"
        Toast.makeText(applicationContext, "Score was reset",Toast.LENGTH_LONG).show()
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
