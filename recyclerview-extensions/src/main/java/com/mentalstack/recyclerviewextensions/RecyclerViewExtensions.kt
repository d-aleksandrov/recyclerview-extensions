package com.mentalstack.recyclerviewextensions

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout

/**
 * Created by aleksandrovdenis on 13.01.2018.
 */
fun RecyclerView.setVertical(elementsInRow: Int, reverse: Boolean = false) {
    attachLayout(LinearLayout.VERTICAL, elementsInRow, reverse)
}

fun RecyclerView.setHorizontal(elementsInRow: Int, reverse: Boolean = false) {
    attachLayout(LinearLayout.HORIZONTAL, elementsInRow, reverse)
}

private fun RecyclerView.attachLayout(direction: Int, elementsInRow: Int, reverse: Boolean = false) {
    layoutManager = if (elementsInRow == 1)
        LinearLayoutManager(context, direction, reverse)
    else
        GridLayoutManager(context, elementsInRow, direction, reverse)

    setHasFixedSize(true)
}

fun RecyclerView.getDirection() =
        (layoutManager as? LinearLayoutManager)?.layoutDirection ?:
                (layoutManager as? GridLayoutManager)?.layoutDirection

fun RecyclerView.getVisibleIndexes(): Pair<Int, Int>? {
    return (layoutManager as? LinearLayoutManager)?.let {
        Pair(it.findFirstVisibleItemPosition(), it.findLastVisibleItemPosition())
    } ?: (layoutManager as? GridLayoutManager)?.let {
        Pair(it.findFirstVisibleItemPosition(), it.findLastVisibleItemPosition())
    }
}

fun RecyclerView.elementsInRow() =
        (layoutManager as? LinearLayoutManager)?.let { 1 } ?:
                (layoutManager as? GridLayoutManager)?.let { it.spanCount }

fun RecyclerView.lastVisibleIndex() =
        (layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition() ?:
                (layoutManager as? GridLayoutManager)?.findLastVisibleItemPosition()

fun RecyclerView.firstVibleIndex() =
        (layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition() ?:
                (layoutManager as? GridLayoutManager)?.findFirstVisibleItemPosition()
