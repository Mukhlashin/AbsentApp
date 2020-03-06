package com.example.absentexample

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

class AbsentAdapter : RecyclerView.Adapter<AbsentAdapter.AbsentViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsentAdapter.AbsentViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: AbsentAdapter.AbsentViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class AbsentViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView!!), LayoutContainer {

    }
}