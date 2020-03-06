package com.example.absentexample

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.absentexample.main.MainActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    lateinit var editor: SharedPreferences.Editor
    lateinit var myPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        myPrefs = getSharedPreferences("login", Context.MODE_PRIVATE)
        editor = getSharedPreferences("login", Context.MODE_PRIVATE).edit()

        var button = findViewById<Button>(R.id.btn_login)
        button.setOnClickListener {
            login()
        }
    }

    fun login(){
//        Toast.makeText(this, "login..", Toast.LENGTH_SHORT).show()

        if (edt_email.text.toString().isEmpty()) {
            edt_email.error = "Email tidak boleh kosong!"
        } else if (edt_password.text.isEmpty()) {
            edt_password.error = "Password tidak boleh kosong!"
        }

        if(!edt_email.text.toString().isEmpty() && !edt_password.text.toString().isEmpty()){
            AndroidNetworking.post("http:/192.168.43.241/absensi/public/api/login")
                .addBodyParameter("email", edt_email.text.toString())
                .addBodyParameter("password", edt_password.text.toString())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
//                        Toast.makeText(applicationContext, "Respon!", Toast.LENGTH_SHORT).show()
                        if (response != null) {
                            editor.putString("email", edt_email.text.toString())
                            editor.putString("password", edt_password.text.toString())
                            editor.putString("id", response.getString("id"))
                            editor.putString("token", response.getString("token"))
                            editor.apply()
                            val goToHome = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(goToHome)
                            Toast.makeText(applicationContext, "Berhasil login", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(applicationContext, "Ada yang salah", Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onError(anError: ANError?) {
                        Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show()
                        Log.d("debuga", anError.toString())

                    }
                })
        } else {
            Toast.makeText(this, "Silakah isi data", Toast.LENGTH_SHORT).show()
        }
    }
}
