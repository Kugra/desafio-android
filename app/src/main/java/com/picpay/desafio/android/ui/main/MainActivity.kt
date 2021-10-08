package com.picpay.desafio.android.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.ui.userlist.UserListAdapter
import com.picpay.desafio.android.utils.extensions.gone
import com.picpay.desafio.android.utils.extensions.visible
import com.picpay.desafio.android.utils.helper.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private val adapter: UserListAdapter by lazy { UserListAdapter() }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.liveData.observe(this, {
            userListObserver(it)
        })

        viewModel.loading.observe(this, { isLoading ->
            if (isLoading) binding.showProgressBar() else binding.hideProgressBar()
        })

        binding.apply {
            recyclerView.adapter = adapter
        }
    }

    private fun userListObserver(userList: List<User>) {
        adapter.users = userList
    }

}


fun ActivityMainBinding.showProgressBar() {
    userListProgressBar.visible()
}

fun ActivityMainBinding.hideProgressBar() {
    userListProgressBar.gone()
}
