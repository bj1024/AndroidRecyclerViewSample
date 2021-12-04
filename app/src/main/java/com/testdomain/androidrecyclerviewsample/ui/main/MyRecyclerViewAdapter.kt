package com.testdomain.androidrecyclerviewsample.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.testdomain.androidrecyclerviewsample.R

// RecyclerView.ViewHolder(ViewHolderの Abstract Class) を返すAdapterを定義。
// - アイテムによって、ViewHolderを返す。
// -


class MyRecyclerViewAdapter(
    private var myDataList: MutableList<MyData> = mutableListOf(),
    private var isVirtical: Boolean = true,

//    var selectionTracker: SelectionTracker<String>? = null
//            private val viewModel: MainViewModel

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType(val rawValue: Int) {
        Text(1),
        ImageText(2);

        companion object {
            fun fromInt(value: Int) = ViewType.values().first { it.rawValue == value }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position % 2 == 0) {
            return ViewType.Text.rawValue
        } else {
            return ViewType.ImageText.rawValue
        }
        return super.getItemViewType(position)
    }

    /**
     * アイテム数を返す。
     *
     * @return アイテム数
     */
    override fun getItemCount(): Int = myDataList.size

    /**
     * positionのITEMを返す。
     *
     * @param position featureListの添字
     */
    fun getItem(position: Int) = myDataList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vtype: ViewType = ViewType.fromInt(viewType)


        when (vtype) {

            ViewType.Text -> {
                val res = if (isVirtical) R.layout.my_text_item_row else R.layout.my_text_item_tile

                val view = LayoutInflater.from(parent.context)
                    .inflate(res, parent, false)
                return ViewTextHolder(view)
            }
            ViewType.ImageText -> {
                val res =
                    if (isVirtical) R.layout.my_imagetext_item_row else R.layout.my_imagetext_item_tile

                val view = LayoutInflater.from(parent.context)
                    .inflate(res, parent, false)
                return ViewImageTextHolder(view)
            }
        }
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mydata = myDataList[position]

        if (holder is MyViewHolderInterface) {
            holder.setData(position,mydata)
        }
    }

    // ViewHolderサブクラスに、setDataを保持することを強制するためのInterface。
    interface MyViewHolderInterface {
        fun setData(position: Int, data: MyData)
    }

    /**
     * 1要素分のViewを管理するViewHolder Class
     */
    inner class ViewTextHolder(view: View) : RecyclerView.ViewHolder(view), MyViewHolderInterface {
        val titleView: TextView = view.findViewById(R.id.mytext_item_title)
        val descView: TextView = view.findViewById(R.id.mytext_item_description)


        override fun setData(position: Int, data: MyData) {
            titleView.text = "${position + 1} ${data.title}"
            descView.text = data.desc
        }

    }

    /**
     * 1要素分のViewを管理するViewHolder Class
     */
    inner class ViewImageTextHolder(view: View) : RecyclerView.ViewHolder(view),
        MyViewHolderInterface {
        val imageView: ImageView = view.findViewById(R.id.myimagetext_item_image)
        val titleView: TextView = view.findViewById(R.id.myimagetext_item_title)
        val descView: TextView = view.findViewById(R.id.myimagetext_item_description)



        override fun setData(position: Int, data: MyData) {

            imageView.setImageResource(data.imageID)

            titleView.text = "${position + 1} ${data.title}"

            descView.text = data.desc
        }


    }
}

