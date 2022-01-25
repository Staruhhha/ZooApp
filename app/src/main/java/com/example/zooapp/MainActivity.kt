package com.example.zooapp

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var  imageView: ImageView
    lateinit var play: Button
    lateinit var playAgain : Button
    lateinit var changeTheme : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.nameAnim)
        imageView = findViewById(R.id.imageAnim)

        play = findViewById(R.id.play)
        playAgain = findViewById(R.id.playAgain)
        changeTheme = findViewById(R.id.change_theme)

        var listAnimals : ArrayList<Animals> = arrayListOf (
            Animals(
                "Дельфин",
                R.drawable.dolph
            ),
            Animals(
                "Жираф",
                R.drawable.gir
            ),
            Animals(
                "Кит",
                R.drawable.kit
            ),
            Animals(
                "Медуза",
                R.drawable.meduz
            ),
            Animals(
                "Акула",
                R.drawable.akula
            ),
            Animals(
                "Лев",
                R.drawable.lion
            ),
            Animals(
                "Тигр",
                R.drawable.tigr
            ),
            Animals(
                "Гепард",
                R.drawable.gep
            ),
            Animals(
                "Олень",
                R.drawable.olen
            ),
            Animals(
                "Попугай",
                R.drawable.popug
            )
        )

        play.setOnClickListener {
            val random = (0..9).random()
            for (i in 0..listAnimals.size-1){
                if (i == random){
                    textView.text = listAnimals[i].name
                    imageView.setImageResource(listAnimals[i].imgSrc)
                    play.isEnabled = false
                    playAgain.isEnabled = true
                }
            }
        }
        playAgain.setOnClickListener {
            play.isEnabled = true
            playAgain.isEnabled = false
        }

        val sharedPreferences : SharedPreferences = getSharedPreferences("AppSetting", 0)
        val sharedPreferencesEdit : SharedPreferences.Editor = sharedPreferences.edit()
        val nightMode : Boolean = sharedPreferences.getBoolean("themeNight", false)

        if (nightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            textView.setTextColor(Color.WHITE)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        changeTheme.setOnClickListener {
            if (nightMode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPreferencesEdit.putBoolean("themeNight", false)
                sharedPreferencesEdit.apply()
            } else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPreferencesEdit.putBoolean("themeNight", true)
                sharedPreferencesEdit.apply()
                textView.setTextColor(Color.WHITE)
            }

        }

    }
}