package com.example.myapplication.ui.fragments

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.ui.activities.HomeActivity
import com.example.myapplication.viewmodel.FireBaseViewModel
import kotlinx.android.synthetic.main.fragment_settings.view.*
import java.util.*


@Suppress("DEPRECATION")
class SettingsFragment : Fragment() {
   private lateinit var fireBaseViewModel: FireBaseViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view : View = inflater.inflate(R.layout.fragment_settings, container, false)
        fireBaseViewModel = ViewModelProvider(
            this, ViewModelProvider
                .AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(FireBaseViewModel::class.java)


       // setLocale(requireActivity(),"ar")



        view.btn_profile.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                var dialog: Dialog = Dialog(requireActivity())
                dialog.setContentView(R.layout.credintals_layout)
                dialog.setCancelable(false)
                dialog.show()
                val emailTxt: EditText = dialog.findViewById(R.id.editText)
                val passWordTxt: EditText = dialog.findViewById(R.id.editText2)
                val btnUpdateEmail: Button = dialog.findViewById(R.id.btn_update_mail)
                val btnUpdtePassWord: Button = dialog.findViewById(R.id.btn_password_update)
                val btnBack: Button = dialog.findViewById(R.id.btn_cancel)
                val showPassWordBtn: ImageButton = dialog.findViewById(R.id.show_password_btn)
                val prefrences: SharedPreferences = requireActivity()
                    .getSharedPreferences("credintals", Context.MODE_PRIVATE)
                val mail: String? = prefrences.getString("email", "")
                val passWord: String? = prefrences.getString("passWord", "")
                emailTxt.setText(mail)
                passWordTxt.setText(passWord)

                showPassWordBtn.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(view: View?) {
                        if (view!!.id == R.id.show_password_btn) {
                            if (passWordTxt.getTransformationMethod()
                                    .equals(PasswordTransformationMethod.getInstance())
                            ) {
                                (view as ImageView).setImageResource(R.drawable.ic_visibility_off)
                                //Show Password
                                passWordTxt.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                            } else {
                                (view as ImageView).setImageResource(R.drawable.ic_visibility_on)
                                //Hide Password
                                passWordTxt.setTransformationMethod(PasswordTransformationMethod.getInstance())
                            }
                        }
                    }

                })

                btnUpdateEmail.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(p0: View?) {
                        val updatedMail: String = emailTxt.text.toString()
                        fireBaseViewModel.UserCredintalsforEmail(mail!!, updatedMail, passWord!!)
                    }

                })
                btnUpdtePassWord.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(p0: View?) {
                        val updatePassWord: String = passWordTxt.text.toString()
                        fireBaseViewModel.UserCredintalsforPassWord(
                            mail!!,
                            passWord!!,
                            updatePassWord
                        )
                    }

                })
                btnBack.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(p0: View?) {
                        dialog.dismiss()
                    }

                })

            }

        })
        view.btn_log_out.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                fireBaseViewModel.logOut()
                activity!!.finish()
                val preferences: SharedPreferences = activity!!.getSharedPreferences(
                    "checkbox",
                    Context.MODE_PRIVATE
                )
                val editor = preferences.edit()
                editor.putString("remember", "false")
                editor.apply()
            }

        })
        view.btn_language.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                var dialog: Dialog = Dialog(requireActivity())
                dialog.setContentView(R.layout.language_dialog)
                val buttonArabic: Button = dialog.findViewById(R.id.btn_arabic)
                val btnEnglish: Button = dialog.findViewById(R.id.btn_english)
                val btnBack : Button = dialog.findViewById(R.id.button_back)
                dialog.setCancelable(false)
                dialog.show()
                btnEnglish.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(p0: View?) {
                        val languageToLoad = "en"
                        val locale = Locale(languageToLoad)
                        Locale.setDefault(locale)
                        val config = Configuration()
                        config.locale = locale
                        context!!.resources.updateConfiguration(
                            config,
                            context!!.resources.displayMetrics
                        )

                        val intent = Intent(requireActivity(), HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)


                    }

                })
                buttonArabic.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(p0: View?) {
                        val languageToLoad = "ar"
                        val locale = Locale(languageToLoad)
                        Locale.setDefault(locale)
                        val config = Configuration()
                        config.locale = locale
                        context!!.resources.updateConfiguration(
                            config,
                            context!!.resources.displayMetrics
                        )

                        val intent = Intent(requireActivity(), HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    }

                })
                btnBack.setOnClickListener(object : View.OnClickListener{
                    override fun onClick(p0: View?) {
                        dialog.dismiss()
                    }

                })


            }

        })

        return view
    }


}
