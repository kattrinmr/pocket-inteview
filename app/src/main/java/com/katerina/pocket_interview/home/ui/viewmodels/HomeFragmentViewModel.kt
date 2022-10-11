package com.katerina.pocket_interview.home.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.katerina.pocket_interview.home.domain.interactors.HomeInteractor
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(val homeInteractor: HomeInteractor)
    : ViewModel() {

        

}