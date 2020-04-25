package com.game.babbel.game.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.game.babbel.R
import dagger.android.support.DaggerAppCompatActivity

class GameActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }
}
