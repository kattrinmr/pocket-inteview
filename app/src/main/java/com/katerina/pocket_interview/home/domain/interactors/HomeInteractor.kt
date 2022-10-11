package com.katerina.pocket_interview.home.domain.interactors

import com.katerina.pocket_interview.home.domain.repositories.HomeRepository
import javax.inject.Inject

class HomeInteractor @Inject constructor(
    val homeRepository: HomeRepository
) {
}