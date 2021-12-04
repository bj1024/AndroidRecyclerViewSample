package com.testdomain.androidrecyclerviewsample.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testdomain.androidrecyclerviewsample.R

class MainFragment : Fragment() {


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false).apply {

            val imageids = arrayOf(
                    R.drawable.ic_baseline_apartment_24,
                    R.drawable.ic_baseline_audiotrack_24,
                    R.drawable.ic_baseline_cottage_24,
                    R.drawable.ic_baseline_error_outline_24,
                    R.drawable.ic_baseline_inbox_24,
                    R.drawable.ic_baseline_outlet_24,
                    R.drawable.ic_baseline_pedal_bike_24,
                    R.drawable.ic_launcher_foreground
            )


            val myDataList = mutableListOf<MyData>()
            repeat((0..99).count()) {
                myDataList.add(
                        MyData("No.${it+1} ",
                                "test",
                                imageids.random()
                        ))
            }


            val recyclerViewListH = findViewById<RecyclerView>(R.id.recycler_list_h)
            val recyclerViewAdapterListH = MyRecyclerViewAdapter(myDataList,false)

            val lmh = LinearLayoutManager(context)
            lmh.orientation = LinearLayoutManager.HORIZONTAL // 横スクロール

            // RecyclerViewの初期設定
            recyclerViewListH.apply {
                layoutManager= lmh

//            layoutManager = LinearLayoutManager(context)
//            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                addItemDecoration(MyRecyclerItemDecoration(0,0,0,16))
                adapter = recyclerViewAdapterListH
            }

            val lmv = LinearLayoutManager(context)
            val recyclerViewListV = findViewById<RecyclerView>(R.id.recycler_list_v)
            val recyclerViewAdapterListV = MyRecyclerViewAdapter(myDataList,true)
            recyclerViewListV.apply {
                layoutManager= lmv

//            layoutManager = LinearLayoutManager(context)
//            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                addItemDecoration(MyRecyclerItemDecoration(0,0,16,0))
                adapter = recyclerViewAdapterListV
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}