package com.game.babbel

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.game.babbel.common.Utils
import com.game.babbel.game.presentation.ui.activity.GameActivity
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Utils().transparentStatusAndNavigation(window)
        Handler().postDelayed({
            startActivity(Intent(this, GameActivity::class.java))
            finish()
        }, 2000)

    }
}
