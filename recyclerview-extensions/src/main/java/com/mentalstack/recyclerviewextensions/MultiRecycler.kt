package com.mentalstack.recyclerviewextensions

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

/**
 * Created by aleksandrovdenis on 13.01.2018.
 */
class MultiRecycler : RecyclerView {
    constructor(context: Context) : super(context) {
        initThis()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        parseAttrs(attrs)
        initThis()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        parseAttrs(attrs)
        initThis()
    }

    val adapter = RecyclerAdapter()

    private fun initThis() {
        setAdapter(adapter)
    }

    private fun parseAttrs(attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs,
                R.styleable.MultiRecycler, 0, 0)

        val direction = a.getInt(R.styleable.MultiRecycler_direction, 0)
        val cells = a.getInt(R.styleable.MultiRecycler_cells, 1)
        val reverse = a.getBoolean(R.styleable.MultiRecycler_reverse, false)
        val sensitive = a.getInt(R.styleable.MultiRecycler_pagination_sensitive, 1)

        a.getResourceId(R.styleable.MultiRecycler_cell_preloader, 0).let {
            if (it > 0)
                adapter.setPreloader(it)
        }

        a.getResourceId(R.styleable.MultiRecycler_cell_end, 0).let {
            if (it > 0)
                adapter.setEndList(it)
        }

        a.getResourceId(R.styleable.MultiRecycler_cell_error, 0).let {
            if (it > 0)
                adapter.setError(it)
        }

        adapter.paginationSensitive = sensitive

        when (direction) {
            1 -> setVertical(cells, reverse)
            2 -> setHorizontal(cells, reverse)
        }

        a.recycle()
    }

}