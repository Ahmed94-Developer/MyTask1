package com.example.myapplication.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.ui.activities.LoginActivity
import com.example.myapplication.viewmodel.FireBaseViewModel
import kotlinx.android.synthetic.main.fragment_login.view.*


@Suppress("DEPRECATION")
class LoginFragment : Fragment(), CompoundButton.OnCheckedChangeListener{

    private val registerFragment : RegisterFragment = RegisterFragment()
    private lateinit var fireBaseViewModel : FireBaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_login, container, false)

        fireBaseViewModel = ViewModelProvider(
            this, ViewModelProvider
                .AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(FireBaseViewModel::class.java)

        view.btn_login.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                val email = view.email_txt.text.toString()
                val passWord = view.password_txt.text.toString()
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(passWord)) {
                    Toast.makeText(activity, "Some fields are required", Toast.LENGTH_SHORT).show()
                } else {
                    fireBaseViewModel.loginUser(email, passWord)
                    val editor: SharedPreferences.Editor = requireActivity().getSharedPreferences(
                        "credintals", Context.MODE_PRIVATE).edit()
                    editor.putString("email", email)
                    editor.putString("passWord", passWord)
                    editor.apply()

                }

            }

        })
        view.btn_signup.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val loginActivity = activity as LoginActivity
                loginActivity.replaceFragment(registerFragment)
            }

        })

        view.show_password_login.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (p0!!.id == R.id.show_password_login) {
                    if (view.password_txt.getTransformationMethod()
                            .equals(PasswordTransformationMethod.getInstance())
                    ) {
                        (p0 as ImageView).setImageResource(R.drawable.ic_visibility_off)
                        //Show Password
                        view.password_txt.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                    } else {
                        (p0 as ImageView).setImageResource(R.drawable.ic_visibility_on)
                        //Hide Password
                        view.password_txt.setTransformationMethod(PasswordTransformationMethod.getInstance())
                    }
                }
            }

        })
        view.checkBox.setOnCheckedChangeListener(this)



        return view
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        if (p0!!.isChecked()) {
            val preferences: SharedPreferences =
                requireActivity().getSharedPreferences("checkbox", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("remember", "true")
            editor.apply()
            Toast.makeText(
                requireActivity(), "Checked", Toast.LENGTH_SHORT
            ).show()
        } else if (!p0.isChecked()) {
            val preferences: SharedPreferences =
                requireActivity().getSharedPreferences("checkbox", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("remember", "false")
            editor.apply()
            Toast.makeText(
                requireActivity(), "Unchecked", Toast.LENGTH_SHORT
            ).show()
        }
    }

}

