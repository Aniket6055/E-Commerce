package com.demo.ecommerceapp.view.adapters

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.content.Context
import android.view.View
import android.widget.TextView
import com.demo.ecommerceapp.R


class ExpandableListAdapter(
    private val context: Context, private val listDataHeader: List<String>,
    private val listDataChild: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {

    override fun getChild(groupPosition: Int, childPosititon: Int): String {
        return this.listDataChild[this.listDataHeader[groupPosition]]!!.get(childPosititon)
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int,
        isLastChild: Boolean, convertView: View?, parent: ViewGroup
    ): View {
        var convertView = convertView

        val childText = getChild(groupPosition, childPosition)

        if (convertView == null) {
            val layoutInflater = this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.subcategory, null)
        }

        val txtListChild = convertView!!.findViewById<TextView>(R.id.lblListItem)

        txtListChild.text=childText
        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {

        return if (this.listDataChild[this.listDataHeader[groupPosition]] == null)
            0
        else
            this.listDataChild[this.listDataHeader[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): String {
        return this.listDataHeader[groupPosition]
    }

    override fun getGroupCount(): Int {
        return this.listDataHeader.size

    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(
        groupPosition: Int, isExpanded: Boolean,
        convertView: View?, parent: ViewGroup
    ): View {
        var convertView = convertView
        val headerTitle = getGroup(groupPosition)
        if (convertView == null) {
            val layoutInflater= this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.category, null)
        }

        val lblListHeader = convertView!!.findViewById<TextView>(R.id.lblListHeader)
        lblListHeader.setTypeface(null, Typeface.BOLD)
        lblListHeader.text=headerTitle

        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}