package com.testdomain.androidrecyclerviewsample.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testdomain.androidrecyclerviewsample.R

class MainFragment : Fragment() {


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private val recyclerViewAdapterListH = MyRecyclerListAdapter(false, { data ->
        adapterOnClickH(data)
    })
    private val recyclerViewAdapterListV = MyRecyclerListAdapter(true, { data ->
        adapterOnClickV(data)
    })


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false).apply {


            val recyclerViewListH = findViewById<RecyclerView>(R.id.recycler_list_h)

            val lmh = LinearLayoutManager(context)
            lmh.orientation = LinearLayoutManager.HORIZONTAL // 横スクロール

            // RecyclerViewの初期設定
            recyclerViewListH.apply {
                layoutManager = lmh

//            layoutManager = LinearLayoutManager(context)
//            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                addItemDecoration(MyRecyclerItemDecoration(0, 0, 0, 16))
                adapter = recyclerViewAdapterListH
            }

            val lmv = LinearLayoutManager(context)
            val recyclerViewListV = findViewById<RecyclerView>(R.id.recycler_list_v)
            recyclerViewListV.apply {
                layoutManager = lmv

//            layoutManager = LinearLayoutManager(context)
//            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                addItemDecoration(MyRecyclerItemDecoration(0, 0, 16, 0))
                adapter = recyclerViewAdapterListV
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        viewModel.run {
            myDataList.observe(viewLifecycleOwner, {
                recyclerViewAdapterListH.submitList(it)
                recyclerViewAdapterListV.submitList(it)
            })

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun adapterOnClickH(data: MyData) {
        Log.d("MainFragment", "adapterOnClickH ${data.id}")
    }

    private fun adapterOnClickV(data: MyData) {
        Log.d("MainFragment", "adapterOnClickV ${data.id}")
    }

}