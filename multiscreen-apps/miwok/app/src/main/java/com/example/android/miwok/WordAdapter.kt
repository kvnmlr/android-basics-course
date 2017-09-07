package com.example.android.miwok

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class WordAdapter(context: Context?, objects: ArrayList<Word>, backgroundColor: Int) : ArrayAdapter<Word>(context, 0, objects) {
    private var backgroundColor = 0

    init {
        this.backgroundColor = backgroundColor
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView: View? = convertView

        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        val currentWord: Word = getItem(position)

        val originalTextView: TextView = listItemView?.findViewById(R.id.list_item_original) as TextView
        val translationTextView: TextView = listItemView.findViewById(R.id.list_item_translation) as TextView
        val imageView: ImageView = listItemView.findViewById(R.id.list_item_image) as ImageView

        originalTextView.text = currentWord.getMiwokTranslation()
        translationTextView.text = currentWord.getDefaultTranslation()

        val textContainer = listItemView.findViewById(R.id.text_container)
        val color = ContextCompat.getColor(context, backgroundColor)
        textContainer.setBackgroundColor(color)

        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageResourceID())
            imageView.visibility = View.VISIBLE
        } else {
            imageView.visibility = View.GONE
        }

        return listItemView
    }
}