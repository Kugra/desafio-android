package com.picpay.desafio.android.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
                Toast.makeText(this,
                    "An error occurred",
                    Toast.LENGTH_LONG
                ).show()
            }

            FailureType.GRACEFULLY -> {
                Toast.makeText(
                    this,
                    "This is old data",
                    Toast.LENGTH_LONG
                ).show()
            }

            else -> { /*nothing*/ }
        }
    }

}
