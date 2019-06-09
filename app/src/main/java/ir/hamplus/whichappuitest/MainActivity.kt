package ir.hamplus.whichappuitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.activity_main.*
import android.view.animation.AccelerateInterpolator
import android.view.View
import android.widget.ImageView
import android.media.MediaPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/*  View Animation method */
class MainActivity : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img_circle.setOnClickListener {
            //when coins appears on screen sound
            mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.spinning_coin)
            mediaPlayer.start()

            //When coin will out of page sound
            mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.coinbunc)
            val randomRange = (1..50).random()
            txt_count.text = randomRange.toString()
            for (i in 1..randomRange) {
                createViewWithAnimate(i, randomRange)
            }
        }
    }

    private fun createViewWithAnimate(i: Int, count: Int) {
        /* Usiung Coroutines to handle how many clicks that user wants to do on circle
         & add coins objects process operations without any lag or failure with smooth ui*/
        GlobalScope.launch(Dispatchers.Main) {
            val newCoinImg = ImageView(this@MainActivity)
            newCoinImg.id = i
            main_layout.addView(newCoinImg)
            //animate view
            animateViewByLibrary(newCoinImg, "bounce", 300)
            newCoinImg.layoutParams.height = 200
            newCoinImg.layoutParams.width = 200
            val rnd = (100..300).random()
            //For add new coin imagesView objects on different locations
            newCoinImg.x = 300F + rnd
            newCoinImg.y = 500F + rnd
            newCoinImg.setImageResource(R.drawable.ic_coin_dollar)

            translateWithScale(newCoinImg, i, count)
        }
    }

    private fun translateWithScale(imgViewCoin: View?, i: Int, count: Int) {
        if (imgViewCoin == null) {
            return
        }
        val duration = 800L
        val scale = 0.355f
        // translation  object in Y axis calculation
        val transY = imgViewCoin.top + imgViewCoin.height / 2 - scale * main_layout.height / 2
        // translation  object in X axis calculation
        val transX = imgViewCoin.left + imgViewCoin.width / 2 - scale * main_layout.width / 2

        imgViewCoin.animate()
            //  Object  animates along a Path using two properties, using  float values
            .translationX(transX)
            .translationY(transY)
            /*   defines the rate of change of an animation.
                 This allows the basic animation effects (alpha, scale, translate, rotate) to be accelerated, decelerated, repeated, etc.*/
            .setInterpolator(AccelerateInterpolator())
            //i *100 for make a delay before each coin start to move
            .setStartDelay((i * 100).toLong()).duration = duration

        //After animation ended on object do some actions like play a sound & delete it
        imgViewCoin.animate().withEndAction {
            mediaPlayer.start()
            /* Due to improve performance :
                remove object from Main Layout  when get out of screen*/
            main_layout.removeView(imgViewCoin)
            txt_count.text = i.toString()
            if (i == count)
                animateViewByLibrary(txt_count, "wobble", 300)
            animateViewByLibrary(img_coin_count, "shake")

        }
    }

    //for avoid of repeating animate code & make it more flexible & usable
    private fun animateViewByLibrary(view: View, animateType: String, duration: Long = 200, repeatTimes: Int = 1) {
        when (animateType) {
            "bounce" -> {
                YoYo.with(Techniques.BounceIn)
                    .duration(duration)
                    .repeat(repeatTimes)
                    .playOn(view)
            }
            "shake" -> {
                YoYo.with(Techniques.Shake)
                    .duration(duration)
                    .repeat(repeatTimes)
                    .playOn(view)
            }
            "wobble" -> {
                YoYo.with(Techniques.Tada)
                    .duration(duration)
                    .repeat(repeatTimes)
                    .playOn(view)
            }
        }

    }
}
