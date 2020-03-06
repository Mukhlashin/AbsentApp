package com.example.absentexample.main.fragment


import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking

import com.example.absentexample.R
import com.example.absentexample.data.LogItem
import com.example.absentexample.main.adapter.LogProfileRvAdapter
import kotlinx.android.synthetic.main.fragment_log_absent.*

/**
 * A simple [Fragment] subclass.
 */
class LogAbsentFragment : Fragment() {
    private var items: MutableList<LogItem> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_absent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDumyData()
        rv_log_absent.layoutManager = LinearLayoutManager(context)
        rv_log_absent.adapter = LogProfileRvAdapter(context!!, items)
    }

    private fun initData(){
        val baseurl = "http://192.168.43.241/absensi/public/api/user/"
        val sharedPreferences = context?.getSharedPreferences("login", MODE_PRIVATE)
        val id = sharedPreferences?.getString("id", "")
        val token = sharedPreferences?.getString("token", "")
        val email = sharedPreferences?.getString("email", "")
        val password = sharedPreferences?.getString("password", "")

        AndroidNetworking.get("$baseurl/$id")
            .addHeaders("Content-Type", "application/x-www-form-urlencoded")
            .addHeaders("Authorization", "Bearer $token")
            .addQueryParameter("token", token)
            .addPathParameter("email", email)
            .addPathParameter("password", password)

    }

    private fun initDumyData(){
        val date = resources.getStringArray(R.array.dumy_log_date)
        val log_in = resources.getStringArray(R.array.dumy_log_in)
        val log_out = resources.getStringArray(R.array.dumy_log_out)
        items.clear()

        for(i in date.indices){
            items.add(LogItem(date[i], log_in[i], log_out[i]))
        }

    }


}
