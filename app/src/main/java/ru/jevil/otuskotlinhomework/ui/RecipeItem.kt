package ru.jevil.otuskotlinhomework.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import ru.jevil.otuskotlinhomework.R

class RecipeItem(
    val title: String?,
    val url: String? = null,
    val id: String
) : AbstractItem<RecipeItem.ViewHolder>() {

    /** defines the type defining this item. must be unique. preferably an id */
    override val type: Int
        get() = R.id.fastadapter_item

    /** defines the layout which will be used for this item in the list  */
    override val layoutRes: Int
        get() = R.layout.recipe_item

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : FastAdapter.ViewHolder<RecipeItem>(view) {

        var tvTitle: TextView = view.findViewById(R.id.tvTitle)
        var ivImage: ImageView = view.findViewById(R.id.iv_cat)

        override fun bindView(item: RecipeItem, payloads: MutableList<Any>) {
            tvTitle.text = item.title

            Glide.with(ivImage.context).load(item.url).into(ivImage)

        }

        override fun unbindView(item: RecipeItem) {
            tvTitle.text = null
            ivImage.setImageBitmap(null)
        }
    }
}