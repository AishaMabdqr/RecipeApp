package com.example.recipeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_view.view.*
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter (val itemList : List<RecipeDetails.Details>) : RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {

        var imageView : ImageView
        var tvTitle : TextView
        var tvAuthor : TextView

        init {
            imageView = itemView.findViewById(R.id.imageView)
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvAuthor = itemView.findViewById(R.id.tvAuthor)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_view,
                parent,
                false
            )

        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val items = itemList[position]

        holder.itemView.apply {
            holder.tvTitle.text = "Title :${items.title}"
            holder.tvAuthor.text ="Author :${items.author}"

            cardView.setOnClickListener{
                clCardView.isVisible = false
                FullCard.isVisible = true
                tvtitle2.text = "Title: ${items.title}"
                tvAuthor2.text = "Author: ${items.author}"
                tvIng2.text = "Ingredients: ${items.ingredients}"
                tvInstr2.text = "Instructions: ${items.instructions}"

                bOk.setOnClickListener {
                    FullCard.isVisible = false
                    clCardView.isVisible = true
                }
            }
        }
    }

    override fun getItemCount() = itemList.size
}