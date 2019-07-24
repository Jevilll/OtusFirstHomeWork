package ru.jevil.otuskotlinhomework.service

import ru.jevil.otuskotlinhomework.utils.Constants

object ApiFactory {
    val foodApi : FoodApi = RetrofitFactory.retrofit(Constants.BASE_URL)
        .create(FoodApi::class.java)
}