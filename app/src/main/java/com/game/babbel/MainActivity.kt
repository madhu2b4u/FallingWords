package com.game.babbel

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.game.babbel.common.Utils
import com.game.babbel.game.presentation.ui.activity.GameActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val a: Animation = AnimationUtils.loadAnimation(this, R.anim.scale)
        a.reset()
        tvTitle.clearAnimation()
        tvTitle.startAnimation(a)
        Utils().transparentStatusAndNavigation(window)
        Handler().postDelayed({
            startActivity(Intent(this, GameActivity::class.java))
            finish()
        }, 2000)

    }
}
