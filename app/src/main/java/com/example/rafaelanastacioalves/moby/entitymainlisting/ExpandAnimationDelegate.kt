package com.example.rafaelanastacioalves.moby.entitymainlisting

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_entity_viewholder.view.*

class ExpandAnimationDelegate {

        private lateinit var recyclerView: RecyclerView
        private var originalHeight: Int = -1
        private var additionalHeight: Int = -1
        private var expandedPosition: Int = -1
        private var toBeCollapsedPosition: Int = -1

        fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            this.recyclerView = recyclerView
        }
        fun onClick(position: Int) {
            if (expandedPosition < 0) {
                expandViewHolderAtPosition(position)
            } else {
                if (position.compareTo(expandedPosition) == 0) {
                    toBeCollapsedPosition = expandedPosition
                    collapseViewHolderAtPosition(toBeCollapsedPosition)
                    toBeCollapsedPosition = -1
                    expandedPosition = -1
                } else {
                    if (recyclerView.findViewHolderForAdapterPosition(expandedPosition) != null) {
                        toBeCollapsedPosition = expandedPosition
                        collapseViewHolderAtPosition(toBeCollapsedPosition)
                        expandedPosition = position
                        expandViewHolderAtPosition(expandedPosition)
                    } else {
                        toBeCollapsedPosition = expandedPosition
                        expandedPosition = position
                        expandViewHolderAtPosition(expandedPosition)
                    }

                }
            }
        }

        fun onViewAttachedToWindow(holder: MainEntityViewHolder) {
            if (holder.adapterPosition.compareTo(toBeCollapsedPosition) == 0) {
                resetHeight(holder)
                toBeCollapsedPosition = -1
            }
        }

        private fun resetHeight(holder: MainEntityViewHolder) {
            holder.containerView.layoutParams.height = originalHeight
            holder.containerView.requestLayout()
        }

        fun calculateMeasures(holder: MainEntityViewHolder) {
            if (additionalHeight < 0) {

                holder.containerView.doOnLayout { container ->
                    val container = container.detail_container
                    val additionalViewContainer = container.additionalViewContainer
                    additionalHeight = 0
                    originalHeight = container.height
                    additionalViewContainer.isVisible = true

                    additionalViewContainer.doOnPreDraw { view ->
                        additionalHeight = view.height + view.marginTop + view.marginBottom
                        view.isVisible = false
                    }
                }
            }
        }

        private fun expandViewHolderAtPosition(position: Int) {
            expandedPosition = position

            val holder = recyclerView.findViewHolderForAdapterPosition(position) as MainEntityViewHolder

            val animator = getValueAnimator(true, 300L, AccelerateDecelerateInterpolator()) { progress ->
                holder.containerView.layoutParams.height = originalHeight + ((additionalHeight) * progress).toInt()
//            Log.d("Expanding", "additionalHeight:" + additionalHeight.toString())
//            Log.d("Expanding", "originalHeight:" + originalHeight.toString())

                holder.containerView.requestLayout()
            }
            animator.doOnStart { holder.containerView.additionalViewContainer.isVisible = true }
            animator.start()
        }

        private fun collapseViewHolderAtPosition(position: Int) {
            val holder = recyclerView.findViewHolderForAdapterPosition(position) as MainEntityViewHolder

            val animator = getValueAnimator(true, 300L, AccelerateDecelerateInterpolator()) { progress ->
                holder.containerView.layoutParams.height = originalHeight + ((additionalHeight) * (1 - progress)).toInt()
                Log.d("Collapsing", "progress: $progress")
                holder.containerView.requestLayout()
            }
            animator.doOnEnd { holder.containerView.additionalViewContainer.isVisible = false }
            animator.start()
        }

        private inline fun getValueAnimator(
                forward: Boolean = true,
                duration: Long,
                interpolator: TimeInterpolator,
                crossinline updateListener: (progress: Float) -> Unit
        ): ValueAnimator {
            val a =
                    if (forward) ValueAnimator.ofFloat(0f, 1f)
                    else ValueAnimator.ofFloat(1f, 0f)
            a.addUpdateListener { updateListener(it.animatedValue as Float) }
            a.duration = duration
            a.interpolator = interpolator
            return a
        }

    }
