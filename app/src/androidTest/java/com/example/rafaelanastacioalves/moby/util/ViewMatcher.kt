package com.example.rafaelanastacioalves.moby.util

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.entitymainlisting.MainEntityViewHolder
import org.hamcrest.Description
import org.hamcrest.Matcher


 class ViewMatcher {
     companion object {
         fun showMainItemWithTitle(title: String, position: Int): Matcher<in View?>? {
             return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
                 override fun describeTo(description: Description) {
                     description.appendText("Item with title " + title + " at position " +
                             position + ".")
                 }

                 override fun matchesSafely(item: RecyclerView): Boolean {
                     val checkedViewHolder = item.findViewHolderForAdapterPosition(position) as MainEntityViewHolder?
                     val checkedTitleTextView = checkedViewHolder!!.itemView.findViewById<TextView>(R.id.entity_detail_title_textview)
                     return checkedTitleTextView.text.toString() == title &&
                             checkedTitleTextView.visibility == View.VISIBLE
                 }
             }
         }
     }
 }