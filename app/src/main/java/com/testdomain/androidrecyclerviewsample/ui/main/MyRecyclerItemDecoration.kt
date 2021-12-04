package com.testdomain.androidrecyclerviewsample.ui.main


import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class MyRecyclerItemDecoration(
        private val spaceLeft:Int,
        private val spaceTop:Int,
        private val spaceBottom:Int,
        private val spaceRight:Int

        ) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = spaceTop
        outRect.left = spaceLeft
        outRect.right = spaceRight
        outRect.bottom = spaceBottom
    }

}