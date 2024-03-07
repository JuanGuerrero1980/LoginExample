package com.jg.examplelogin

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.jg.examplelogin.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity: Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.login.setOnClickListener {
            login(binding.userName.text.toString(), binding.password.text.toString())
        }
        setContentView(binding.root)
    }


    private fun login(user: String, pass: String) {
        if(!validateFields()) return
        binding.progressBar.isVisible = true
        enableFields(false)
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            runOnUiThread {
                binding.progressBar.isVisible = false
                Toast.makeText(this@MainActivity, getString(R.string.success), Toast.LENGTH_LONG).show()
                enableFields(true)
            }
        }
    }

    private fun validateFields(): Boolean {
        if (binding.userName.text.toString().isEmpty()) {
            binding.userName.error = getString(R.string.userEmptyError)
            return false
        }
        if (binding.password.text.toString().isEmpty()) {
            binding.password.error = getString(R.string.userEmptyError)
            return false
        }
        return true
    }

    private fun enableFields(enable: Boolean) {
        binding.userName.isEnabled = enable
        binding.password.isEnabled = enable
    }
}