package ir.hamplus.whichappuitest

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.ImageView
import android.media.MediaPlayer
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.doOnEnd

/*  Object animator method */
class MainActivity : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title ="Object Animator method (Branch) "

        img_circle.setOnClickListener {
            //when coins appears on screen sound
            mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.spinning_coin)
            mediaPlayer.start()

            //When coin will out of page sound
            mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.coinbunc)
            val randomRange = (1..50).random()
            txt_count.text = randomRange.toString()
            for (i in 1..randomRange) {
                val newCoinImg = ImageView(this)
                newCoinImg.id = i
                main_layout.addView(newCoinImg)

                //animate Coin Object when added to circle
                animateViewByLibrary(newCoinImg, "bounce", 300)
                newCoinImg.layoutParams.height = 200
                newCoinImg.layoutParams.width = 200
                val  rnd = (100..300).random()
                //For add new coin imagesView objects on different locations
                newCoinImg.x = 300F +rnd
                newCoinImg.y = 500F +rnd
                //Use Drawable to scaleUp & Down Smooth
                newCoinImg.setImageResource(R.drawable.ic_coin_dollar)

                translateWithScale(newCoinImg, i, randomRange)
            }
        }
    }


    private fun translateWithScale(imgViewCoin: View?, i: Int, count: Int) {
        if (imgViewCoin == null) {
            return
        }
        // params for animator
        val duration = 500L
        val scale = 0.6F

        // scale down  object in X axis
        val scaleDownX = ObjectAnimator.ofFloat(imgViewCoin, "scaleX", scale).setDuration(duration  )
        // scale down  object in Y axis
        val scaleDownY = ObjectAnimator.ofFloat(imgViewCoin, "scaleY", scale).setDuration(duration  )

        //
        val transY = imgViewCoin.top + imgViewCoin.height - scale
        val transX = imgViewCoin.left + imgViewCoin.width   - scale
        /*  ObjectAnimator.ofFloat: Constructs and returns an ObjectAnimator that animates
            coordinates along a Path using two properties using  float values*/
        val translateX = ObjectAnimator.ofFloat(imgViewCoin, "translationX", transX).setDuration(duration  )
        val translateY = ObjectAnimator.ofFloat(imgViewCoin, "translationY", transY).setDuration(duration )

        //plays a set of Animator objects in the specified order
        val set = AnimatorSet()
        /*   defines the rate of change of an animation.
            This allows the basic animation effects (alpha, scale, translate, rotate) to be accelerated, decelerated, repeated, etc.*/
        set.interpolator = AccelerateDecelerateInterpolator()

        //play all of animations to a AnimatorSet all at the same time
        set.playTogether(scaleDownX, scaleDownY, translateX, translateY)
        //Delay for starting play AnimatorSet on each object for going to left corner on page
        set.startDelay  = (100 * i).toLong()
        set.start()

        //After animation ended on object do some actions like play a sound & delete it
        set.doOnEnd {
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
