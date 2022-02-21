package com.picpay.desafio.android.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.ui.userlist.UserListAdapter
import com.picpay.desafio.android.utils.helper.FailureType
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private val adapter: UserListAdapter by lazy { UserListAdapter() }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startObservers()

        binding.apply {
            recyclerView.adapter = adapter
        }
    }

    private fun startObservers() {

        fun usersListObserver() {
            viewModel.usersList.observe(this) {
                usersListObserver(it)
            }
        }

        fun loadingObserver() {
            viewModel.loading.observe(this) {
                loadingObserver(it)
            }
        }

        fun loadingErrorObserver() {
            viewModel.loadingError.observe(this) {
                loadingErrorObserver(it)
            }
        }

        loadingErrorObserver()
        usersListObserver()
        loadingObserver()
    }

    private fun usersListObserver(userList: List<User>) {
        adapter.users = userList
    }

    private fun loadingObserver(isLoading: Boolean) {
        if (isLoading) binding.showProgressBar() else binding.hideProgressBar()
    }

    private fun loadingErrorObserver(failureType: FailureType) {
        when (failureType) {
            FailureType.NO_DATA -> {
                showSnackbarWithMessage(getString(R.string.generic_error))
            }

            FailureType.GRACEFULLY -> {
                showSnackbarWithMessage(getString(R.string.cached_data))
            }

            else -> { /*nothing*/ }
        }
    }

    private fun showSnackbarWithMessage(message: String) {
        Snackbar.make(binding.parentView,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

}
