package com.example.ehealthapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ehealthapp.R
import com.example.ehealthapp.model.Health

class HealthListAdapter(
    private val onItemClickListener: (Health) -> Unit
): ListAdapter<Health, HealthListAdapter.HealthViewHolder>(WORDS_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthViewHolder {
        return HealthViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HealthViewHolder, position: Int) {
        val health = getItem(position)
        holder.bind(health)
        holder.itemView.setOnClickListener{
            onItemClickListener(health)
        }
    }
    class HealthViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView =itemView.findViewById(R.id.nameTextView)
        private val addressTextView: TextView =itemView.findViewById(R.id.addressTextView)
        private val phonenumberTextView: TextView =itemView.findViewById(R.id.phonenumberTextView)

        fun bind(health: Health?) {
            nameTextView.text = health?.name
            addressTextView.text = health?.address
            phonenumberTextView.text = health?.phonenumber
        }

        companion object {
            fun create(parent: ViewGroup): HealthListAdapter.HealthViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_health, parent, false)
                return HealthViewHolder(view)
            }
        }
    }

    companion object{
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Health>(){
            override fun areItemsTheSame(oldItem: Health, newItem: Health): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Health, newItem: Health): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}