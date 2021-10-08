package com.android.camp.firebase.auth.email_auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import com.android.camp.R
import com.android.camp.databinding.ActivityAddNewQuestionBinding
import com.android.camp.databinding.ActivitySplashScreenBinding
import com.android.camp.navigationcomponent.BilgiYarismasiActivity
import com.android.camp.question.AddNewQuestionActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {
    private var splashDuration = 7000
    private val auth by lazy { FirebaseAuth.getInstance() }
    private var binding: ActivitySplashScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val animasyon1 = AnimationUtils.loadAnimation(this,R.anim.animasyon1);
        val animasyon2 = AnimationUtils.loadAnimation(this,R.anim.animasyon2);
        val animasyon3 = AnimationUtils.loadAnimation(this,R.anim.animasyon3);

        val imageView = binding.imageView1
        val imageView2 = binding.imageGold
        val title = binding.yasinCetin
        val desc = binding.descriptionText
        val dummy = binding.dummyText

        imageView.animation = animasyon1
        imageView2.animation = animasyon2
        title.animation = animasyon3
        desc.animation = animasyon3
        dummy.animation = animasyon3




    }
    override fun onResume() {
        super.onResume()
        if (auth.currentUser != null) {
            Handler().postDelayed({
                startActivity(Intent(this, BilgiYarismasiActivity::class.java))
                finish()
            },splashDuration.toLong())

        }else{
            Handler().postDelayed({
                startActivity(Intent(this, EmailLoginActivity::class.java))
                finish()
            },splashDuration.toLong())

        }
    }
    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

}