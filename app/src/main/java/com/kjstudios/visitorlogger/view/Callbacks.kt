package com.kjstudios.visitorlogger.view

import android.graphics.Canvas
import android.graphics.Color
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.kjstudios.visitorlogger.R
import com.kjstudios.visitorlogger.model.Visitor
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


abstract class SwipeToDelete : ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
){
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        RecyclerViewSwipeDecorator.Builder(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            .addBackgroundColor(Color.RED)
            .addActionIcon(R.drawable.delete)
            .create()
            .decorate()
        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}

val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<Visitor>() {
    override fun areItemsTheSame(oldVisitor: Visitor, newVisitor: Visitor): Boolean {
        return oldVisitor.id == newVisitor.id
    }

    override fun areContentsTheSame(oldVisitor: Visitor, newVisitor: Visitor): Boolean {
        return oldVisitor == newVisitor
    }
}