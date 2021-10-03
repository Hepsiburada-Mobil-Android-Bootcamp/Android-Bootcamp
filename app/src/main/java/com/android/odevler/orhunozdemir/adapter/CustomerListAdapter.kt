package com.android.odevler.orhunozdemir.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.orhunozdemir.data.Customer

class CustomerListAdapter(var mContext: Context, var dataSet:ArrayList<Customer>):RecyclerView.Adapter<CustomerListAdapter.CustomerCardViewHolder>()
{

   inner class CustomerCardViewHolder(view: View):RecyclerView.ViewHolder(view)
   {
     val customerName:TextView
     val customerSurname:TextView
     val customerAge:TextView
     init {

         customerName=view.findViewById<TextView>(R.id.textViewCustomerName)
         customerSurname=view.findViewById<TextView>(R.id.textViewCustomerSurname)
         customerAge=view.findViewById<TextView>(R.id.textViewCustomerAge)

     }


   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerCardViewHolder {
        val cardview:View=LayoutInflater.from(parent.context).inflate(R.layout.item_customer,parent,false)
        return CustomerCardViewHolder(cardview)
    }

    override fun onBindViewHolder(holder: CustomerCardViewHolder, position: Int)
    {
     val customer=dataSet[position]
        holder.customerName.text=customer.name
        holder.customerSurname.text=customer.surname
        holder.customerAge.text=customer.age.toString()


    }

    override fun getItemCount(): Int =dataSet.size


}