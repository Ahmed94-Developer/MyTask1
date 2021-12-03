package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.ui.activities.LoginActivity
import com.example.myapplication.viewmodel.FireBaseViewModel
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_register.view.*


class RegisterFragment : Fragment(),View.OnClickListener {
    private lateinit var fireBaseViewModel: FireBaseViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_register, container, false)
        fireBaseViewModel = ViewModelProvider(this
            ,ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))
            .get(FireBaseViewModel::class.java)
        val userNameTxt : EditText = view.findViewById(R.id.editText3)
        val passWordTxt : EditText = view.findViewById(R.id.editText4)
        val confirmPassWordTxt : EditText = view.findViewById(R.id.editText5)
        val btnBack : Button = view.findViewById(R.id.btn_back)
        btnBack.setOnClickListener(this)
        val btnRegister : Button = view.findViewById(R.id.button3)

        btnRegister.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
               val userName : String = userNameTxt.text.toString()
                val passWord : String = passWordTxt.text.toString()
                val confrimPassWord : String = confirmPassWordTxt.text.toString()

                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord) || TextUtils.isEmpty(confrimPassWord)){
                    Toast.makeText(activity,"Some Fields are required",Toast.LENGTH_SHORT).show()
                }else if (!TextUtils.equals(passWord,confrimPassWord)){
                    Toast.makeText(activity,"passWord are mismatched",Toast.LENGTH_SHORT).show()
                }else{
                    fireBaseViewModel.register(userName,passWord)
                }

            }

        })
        view.imageButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (p0!!.id == R.id.imageButton) {
                    if (view.editText4.getTransformationMethod()
                            .equals(PasswordTransformationMethod.getInstance())
                    ) {
                        (p0 as ImageView).setImageResource(R.drawable.ic_visibility_off)
                        //Show Password
                        view.editText4.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                    } else {
                        (p0 as ImageView).setImageResource(R.drawable.ic_visibility_on)
                        //Hide Password
                        view.editText4.setTransformationMethod(PasswordTransformationMethod.getInstance())
                    }
                }
            }

        })
        view.imageButton2.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if (p0!!.id == R.id.imageButton2) {
                    if (view.editText5.getTransformationMethod()
                            .equals(PasswordTransformationMethod.getInstance())
                    ) {
                        (p0 as ImageView).setImageResource(R.drawable.ic_visibility_off)
                        //Show Password
                        view.editText5.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                    } else {
                        (p0 as ImageView).setImageResource(R.drawable.ic_visibility_on)
                        //Hide Password
                        view.editText5.setTransformationMethod(PasswordTransformationMethod.getInstance())
                    }
                }
            }

        })

        return view
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            (R.id.btn_signup)->{

            }
            (R.id.btn_back)->{
               val loginFragment : LoginFragment = LoginFragment()
               val loginActivity : LoginActivity = activity as LoginActivity
                loginActivity.replaceFragment(loginFragment)
            }
        }
    }


}
