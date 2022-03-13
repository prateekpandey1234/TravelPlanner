package com.example.travelplanner.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.travelplanner.R
import java.util.*


class ViewPagerAdapter :PagerAdapter() {
    // Context object
    var context: Context? = null

    // Array of images
    lateinit var images: Array<Int>

    // Layout Inflater
    var mLayoutInflater: LayoutInflater? = null
    fun ViewPagerAdapter(context: Context, images: Array<Int>) {
        this.context = context
        this.images = images
        this.mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override  fun instantiateItem(container: ViewGroup, position: Int): Any {
        // inflating the item.xml
        val itemView = mLayoutInflater?.inflate(R.layout.layout_image_pager,container,false)

        // referencing the image view from the item.xml file
        val imageView: ImageView = itemView?.findViewById<View>(R.id.imageViewpager) as ImageView

        // setting the image in the imageView
        imageView.setImageResource(images[position])

        // Adding the View
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

     override  fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout?)
    }
}