package com.testdomain.androidrecyclerviewsample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import com.testdomain.androidrecyclerviewsample.R

class MainViewModel : ViewModel() {

    private val myDatas = mutableListOf<MyData>()

    private val _myDataList = MutableLiveData<List<MyData>>(emptyList())
    val myDataList: LiveData<List<MyData>> = _myDataList.distinctUntilChanged()


    init {
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


        // 初期データを作成する。
        repeat((0..99).count()) {
            myDatas.add(
                MyData(it,
                    "No.${it + 1} ",
                    "test",
                    imageids.random()
                ))
        }
        _myDataList.value = ArrayList(myDatas)


    }


}