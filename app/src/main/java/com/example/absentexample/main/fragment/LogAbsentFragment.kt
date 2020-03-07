package com.example.absentexample.main.fragment


import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.absentexample.BaseApp.Companion.BASE_URL
import com.example.absentexample.R
import com.example.absentexample.data.LogItem
import com.example.absentexample.main.MainActivity
import com.example.absentexample.main.adapter.LogProfileRvAdapter
import kotlinx.android.synthetic.main.fragment_log_absent.*
import org.json.JSONObject

class LogAbsentFragment : Fragment() {
    private var items: MutableList<LogItem> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_absent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

    }

    private fun initData(){
        val baseurl = "$BASE_URL/absensi/public/api/user"
        val sharedPreferences = context?.getSharedPreferences("login", MODE_PRIVATE)
        val sharedPreferencesEditor = context?.getSharedPreferences("login", MODE_PRIVATE)?.edit()

        val id = sharedPreferences?.getString("id", "")
        val token = sharedPreferences?.getString("token", "")
        val email = sharedPreferences?.getString("email", "")
        val password = sharedPreferences?.getString("password", "")

        Log.d("debuga", "$id $token $email $password")


        AndroidNetworking.get("$baseurl/$id")
            .addHeaders("Content-Type", "application/x-www-form-urlencoded")
            .addHeaders("Authorization", "Bearer $token")
            .addQueryParameter("token", token)
            .addPathParameter("email", email)
            .addPathParameter("password", password)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{

                override fun onResponse(response: JSONObject?) {
                    Log.d("debuga", "berhasil dapat data ${response.toString()}")

                    val userData = response?.getJSONObject("user")
                    val absensi = userData?.getJSONArray("absensi")
                    items.clear()


                    val namaUser = userData?.getString("name")
                    val emailUser = userData?.getString("email")
                    sharedPreferencesEditor?.putString("name", namaUser)?.putString("email", emailUser)?.apply()

                    //mengirim info user ke activity
                    val activity = activity as MainActivity
                    activity.setUserInfo(namaUser, emailUser)


                    //menyimpan data2 log absen
                    for(i in 0 until (absensi?.length() ?: 0)){
                        val timeIn = absensi?.getJSONObject(i)?.getString("tap_masuk")
                        val timeOut = absensi?.getJSONObject(i)?.getString("tap_keluar")
                        val date = timeIn?.substring(0, 10)

                        Log.d("debuga", "$timeIn $timeOut $date")
                        items.add(LogItem(date, timeIn, timeOut))
                    }

                    initRv()
                }

                override fun onError(anError: ANError?) {
                    Log.d("debuga", "error ${anError?.message.toString()} ${anError?.errorBody.toString()}")

                }

            })
    }

    fun initRv(){
        rv_log_absent.layoutManager = LinearLayoutManager(context)
        rv_log_absent.adapter = LogProfileRvAdapter(context!!, items)
    }


}
