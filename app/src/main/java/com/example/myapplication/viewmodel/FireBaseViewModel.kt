package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.repository.AuthRepository

class FireBaseViewModel(application: Application) : AndroidViewModel(application) {
    var authRepository : AuthRepository = AuthRepository(application)
    fun loginUser(email : String,passWord : String){
        authRepository.login(email, passWord)

    }
   open fun UserCredintalsforEmail(email: String,updatedMail : String, passWord: String){
        authRepository.UserCredintalsforEmail(email, updatedMail,passWord)
    }
    open fun UserCredintalsforPassWord(email: String,passWord : String, UpdatePassWord: String){
        authRepository.UserCredintalsForPassWord(email,passWord,UpdatePassWord)
    }
    fun logOut(){
        authRepository.LogOut()
    }
    fun register(email: String?,passWord: String?){
        authRepository.register(email, passWord)

    }
}