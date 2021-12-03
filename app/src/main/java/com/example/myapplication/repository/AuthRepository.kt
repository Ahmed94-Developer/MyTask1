package com.example.myapplication.repository

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.ui.activities.HomeActivity
import com.example.myapplication.ui.activities.LoginActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


@Suppress("DEPRECATION")
class AuthRepository(private val application: Application) {
    private var userLiveData: MutableLiveData<FirebaseUser?> = MutableLiveData()
    private var loggedOutLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var mAuth = FirebaseAuth.getInstance()
    var firebaseUser = mAuth.currentUser
    var failedLogin: Boolean = false



    fun login(email: String?, passWord: String?) {
        failedLogin = false
        mAuth.signInWithEmailAndPassword(email!!, passWord!!).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                val i = Intent(application.applicationContext, HomeActivity::class.java)
                application.applicationContext.startActivity(i)

                Toast.makeText(
                    application.applicationContext,
                    "Login Successful",
                    Toast.LENGTH_LONG
                ).show()

               userLiveData.postValue(mAuth.currentUser)
                loggedOutLiveData.postValue(false)
            } else {
                Toast.makeText(application.applicationContext, "Login Failed", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun register(email: String?,passWord: String?) {
        mAuth.createUserWithEmailAndPassword(email!!, passWord!!).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val i = Intent(application.applicationContext, HomeActivity::class.java)
                application.applicationContext.startActivity(i)
                Toast.makeText(application.applicationContext,"User Created Successfully",Toast.LENGTH_SHORT).show()
                userLiveData!!.postValue(mAuth.currentUser)
            } else {
                Toast.makeText(
                    application.applicationContext,
                    ""+task.exception,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun LogOut() {
        mAuth.signOut()
        application.applicationContext.startActivity(
            Intent(
                application.applicationContext,
                LoginActivity::class.java
            )
        )
        loggedOutLiveData!!.postValue(true)
    }

    fun getUserLiveData(): MutableLiveData<FirebaseUser?>? {
        return userLiveData
    }

    fun getLoggedOutLiveData(): MutableLiveData<Boolean>? {
        return loggedOutLiveData
    }
    fun UserCredintalsforEmail(email: String, updatedMail: String, passWord: String){
        val credential = EmailAuthProvider
            .getCredential(email, passWord) // Current Login Credentials \\


        firebaseUser!!.reauthenticate(credential)
            .addOnCompleteListener(object : OnCompleteListener<Void?> {
                override fun onComplete(p0: com.google.android.gms.tasks.Task<Void?>) {

                    val user = FirebaseAuth.getInstance().currentUser
                    user!!.updateEmail(updatedMail)
                        .addOnCompleteListener(object : OnCompleteListener<Void?> {
                            override fun onComplete(p0: com.google.android.gms.tasks.Task<Void?>) {
                                Toast.makeText(
                                    application.applicationContext,
                                    "Mail Updated Successfully..!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        })
                }

            })
    }
    fun UserCredintalsForPassWord(email: String, passWord: String, updatepassWord: String){
        val credential = EmailAuthProvider
            .getCredential(email, passWord) // Current Login Credentials \\


        firebaseUser!!.reauthenticate(credential)
            .addOnCompleteListener(object : OnCompleteListener<Void?> {
                override fun onComplete(p0: com.google.android.gms.tasks.Task<Void?>) {

                    val user = FirebaseAuth.getInstance().currentUser
                    user!!.updatePassword(updatepassWord)
                        .addOnCompleteListener(object : OnCompleteListener<Void?> {
                            override fun onComplete(p0: com.google.android.gms.tasks.Task<Void?>) {
                                Toast.makeText(
                                    application.applicationContext,
                                    "PassWord Updated Successfully..!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        })
                }

            })
    }
}