package com.android.odevler.ebraryucel.airplane

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.databinding.AirplaneRecyclerviewRowBinding
import com.android.odevler.ebraryucel.data.Airplane
import com.squareup.picasso.Picasso


//Firebase storage erişimi yetkisi olmadığından dolayı resim ekleme ile alakalı işlemler yorum satırına alındı.


class RecyclerAdapter(val airplanes:ArrayList<Airplane>) :RecyclerView.Adapter<RecyclerAdapter.AirplaneHolder>(){

    class AirplaneHolder(view: View):RecyclerView.ViewHolder(view){
        private val manifacturertext by lazy { itemView.findViewById<TextView>(R.id.textView5) }
        private val modeltext by lazy { itemView.findViewById<TextView>(R.id.textView6) }
        private val ownertext by lazy { itemView.findViewById<TextView>(R.id.textView9) }
        private val capacitytext by lazy { itemView.findViewById<TextView>(R.id.textView3) }
       // private val img by lazy { itemView.findViewById<ImageView>(R.id.imageView) }

        fun bind(airplane: Airplane) {
            manifacturertext.text = airplane.manufacturer
            modeltext.text = airplane.model
            ownertext.text = airplane.owner
            capacitytext.text=airplane.capacity.toString()
         //   Picasso.get().load(airplane.downloadUrl).into(img);
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirplaneHolder {
        val view=AirplaneRecyclerviewRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AirplaneHolder(view.root)
    }

    override fun onBindViewHolder(holder: AirplaneHolder, position: Int) {
         holder.bind(airplanes.get(position))
    }


    override fun getItemCount(): Int {
        return airplanes.size
    }

}