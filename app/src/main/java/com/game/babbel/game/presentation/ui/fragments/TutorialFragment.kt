package com.game.babbel.game.presentation.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.game.babbel.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_tutorial.*


class TutorialFragment : DaggerFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutorial, container, false)
    }


    override fun onStart() {
        super.onStart()

        btnStart.setOnClickListener {
            view?.let { Navigation.findNavController(it).navigate(R.id.action_navigate_game) }
        }
    }


}
