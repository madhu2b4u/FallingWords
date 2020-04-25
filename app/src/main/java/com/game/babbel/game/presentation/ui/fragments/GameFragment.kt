package com.game.babbel.game.presentation.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.game.babbel.R
import com.game.babbel.ViewModelFactory
import com.game.babbel.game.presentation.viewmodel.GameViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class GameFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    private lateinit var mGameViewModel: GameViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }


    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            try {
                mGameViewModel =
                    ViewModelProviders.of(activity!!, viewModelFactory)
                        .get(GameViewModel::class.java)

            } finally {

            }
        }
    }


}
