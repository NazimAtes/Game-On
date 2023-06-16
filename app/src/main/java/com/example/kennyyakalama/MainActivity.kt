package com.example.kennyyakalama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.kennyyakalama.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var score=0
    var imageArray= ArrayList<ImageView>()
    var handler= Handler()
    var runnable= Runnable {  }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //ImageArray
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)

        hideİmages()

        //CountDown Timer
        object : CountDownTimer(15000,1000){
            override fun onTick(millisUntilFinished: Long) {

                binding.timeText.text="Time:"+millisUntilFinished/1000
            }

            override fun onFinish() {
                binding.timeText.text="Time: 0"

                handler.removeCallbacks(runnable)

                for (image in imageArray)
                    image.visibility=View.INVISIBLE



                //alert (Uyarı mesajı)

                val alert=AlertDialog.Builder(this@MainActivity) //UYARI MESAJI
                alert.setTitle("Oyun Bitti")
                alert.setMessage("Baştan Başlayalım mı?")
                alert.setPositiveButton("Evet"){dialog,whitch->  // Uyarı mesajında Pozitif butona basınca ne olsun?

                    //OYNU RESTART ETMEK İÇİN:
                    val intent= intent
                    finish()
                    startActivity(intent)
                }

                alert.setNegativeButton("Hayır"){dialog,whitch->  //Uyarı mesajında Negatif butona basınca ne olsun
                    Toast.makeText(this@MainActivity,"Oyun Bitti",Toast.LENGTH_LONG).show()
                }
                alert.show()

            }

        }.start()

    }

    fun hideİmages(){

        runnable= object : Runnable{
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                val random=Random()
                val randomIndex=random.nextInt(9)
                imageArray[randomIndex].visibility=View.VISIBLE

                handler.postDelayed(runnable,400)

            }
        }
        handler.post(runnable)


    }
    fun increaseScore(view:View){
        score= score+1
        binding.scoreText.text="Skor: $score"

    }
}