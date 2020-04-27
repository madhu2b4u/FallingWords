package com.game.babbel.game.presentation.ui.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.game.babbel.R
import com.game.babbel.ViewModelFactory
import com.game.babbel.common.Status
import com.game.babbel.common.Utils
import com.game.babbel.game.data.models.QuestionsModel
import com.game.babbel.game.data.models.WordsListModelItem
import com.game.babbel.game.presentation.viewmodel.GameViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_game.*
import org.jetbrains.anko.toast
import javax.inject.Inject

const val result ="result"
class GameFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var mGameViewModel: GameViewModel

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
                mGameViewModel = ViewModelProviders.of(this@GameFragment, viewModelFactory).get(GameViewModel::class.java)

                mGameViewModel.wordsResult.observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Status.LOADING -> {
                            Log.e("loading","loading")
                        }
                        Status.ERROR -> {
                            context?.toast(getString(R.string.error))
                        }

                        Status.SUCCESS -> {
                            it.data?.let { words ->
                                mGameViewModel.listOfWords = words as ArrayList<WordsListModelItem>
                                mGameViewModel.listOfWords.shuffle()
                                mGameViewModel.sendNewWord()
                            }
                        }
                    }
                })

                observers()

                btnCorrect.setOnClickListener {
                    cancelAnimation()
                    mGameViewModel.onCorrectTransaltionClicked()
                }

                btnWrong.setOnClickListener {
                    cancelAnimation()
                    mGameViewModel.onWrongTranslationClicked()
                }


            } finally {

            }
        }
    }

    private fun observers() {
        //observes new questions i.e words and translations
        mGameViewModel.questionLiveData.observe(this@GameFragment, Observer {
            startAnimation(it)
        })

        //observes score updates
        mGameViewModel.scoreLiveData.observe(this@GameFragment, Observer { score ->
            tvScore.text = score.toString()
        })

        //observes number of lives remaining
        mGameViewModel.livesLiveData.observe(this@GameFragment, Observer { lives ->
            tvLives.text = lives.toString()
        })

        //observes when game is over
        mGameViewModel.gameOverLiveData.observe(this@GameFragment, Observer { score ->
            cancelAnimation()
            val bundle = Bundle()
            bundle.putString(result, score.toString())
            view?.let {
                Navigation.findNavController(it).navigate(R.id.action_navigate_result, bundle)
            }
        })

        //observe error messages
        mGameViewModel.errorLiveData.observe(this@GameFragment, Observer {
            context?.toast(getString(R.string.error))
        })
    }


    private fun startAnimation(question: QuestionsModel){

        val animation: Animation = AnimationUtils.loadAnimation(activity, R.anim.fall_down)
        animation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {
                //empty
            }

            override fun onAnimationEnd(p0: Animation?) {
                //onAnimationEnd means user didn't choose any answer
                cancelAnimation()
                mGameViewModel.onWrongAnswer()
            }

            override fun onAnimationStart(p0: Animation?) {
                //empty
            }
        })

        tvWord.text = question.question
        tvTranslation.text = question.translation
        tvTranslation.visibility = View.VISIBLE

        //disable animation in Espresso testing
        if(!Utils().isRunningTest)
            tvTranslation.startAnimation(animation)
    }

    private fun cancelAnimation(){
        tvTranslation.visibility = View.GONE
        tvTranslation.text = ""
        tvTranslation.animation?.setAnimationListener(null)
        tvTranslation.clearAnimation()
    }





}
