package com.picpay.desafio.android.ui.main

import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.utils.extensions.gone
import com.picpay.desafio.android.utils.extensions.visible

fun ActivityMainBinding.showProgressBar() {
    userListProgressBar.visible()
}

fun ActivityMainBinding.hideProgressBar() {
    userListProgressBar.gone()
}
