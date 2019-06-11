package com.demo.ecommerceapp.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.demo.ecommerceapp.R
import com.demo.ecommerceapp.view.viewmodels.LoginSignUpViewModel
import kotlinx.android.synthetic.main.activity_main.*

class LoginSignUp : AppCompatActivity() {

    private lateinit var loginSignUpViewModel: LoginSignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginSignUpViewModel = ViewModelProvider.AndroidViewModelFactory
            .getInstance(application).create(LoginSignUpViewModel::class.java)

        tvHaveAccount.setOnClickListener { view ->
            if (phoneRelative.visibility == View.VISIBLE) {
                phoneRelative.visibility = View.GONE
                emailRelative.visibility = View.GONE
                tvOr.visibility = View.GONE
                relativeFacebook.visibility = View.GONE
            } else {
                phoneRelative.visibility = View.VISIBLE
                emailRelative.visibility = View.VISIBLE
                tvOr.visibility = View.VISIBLE
                relativeFacebook.visibility = View.VISIBLE
            }
        }

        relativeFacebook.setOnClickListener{view ->
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }
}
