package tech.kicky.common.adapter

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

/**
 * ViewBinding Adapter
 * author: yidong
 * 2021-11-26
 */
abstract class ViewBindingAdapter<T>(context: Context) : RecyclerView.Adapter<BindingViewHolder>() {
    private val mData = mutableListOf<T>()
    protected val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount(): Int = mData.size

    fun setData(data: List<T>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T = mData[position]
}