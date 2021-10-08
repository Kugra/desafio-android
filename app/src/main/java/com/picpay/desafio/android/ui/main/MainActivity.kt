package com.picpay.desafio.android.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.ui.userlist.UserListAdapter
import com.picpay.desafio.android.utils.extensions.gone
import com.picpay.desafio.android.utils.extensions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private val adapter: UserListAdapter by lazy { UserListAdapter() }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.userLiveData.observe(this, ::userListObserver)

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
