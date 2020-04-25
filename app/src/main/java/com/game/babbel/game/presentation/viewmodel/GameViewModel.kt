package com.game.babbel.game.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.game.babbel.game.domain.GameUseCase


class GameViewModel @Inject constructor(
    private val mGameUseCase: GameUseCase
) : ViewModel() {


}