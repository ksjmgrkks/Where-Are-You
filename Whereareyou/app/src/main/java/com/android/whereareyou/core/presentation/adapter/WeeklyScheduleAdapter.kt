package com.android.whereareyou.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.whereareyou.core.presentation.model.schedule.weekly.UIWeeklySchedule
import com.android.whereareyou.databinding.WeeklyScheduleItemBinding

class WeeklyScheduleAdapter : ListAdapter<UIWeeklySchedule, WeeklyScheduleAdapter.WeeklyScheduleViewHolder>(ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyScheduleViewHolder {
        val binding = WeeklyScheduleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return WeeklyScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeeklyScheduleViewHolder, position: Int) {
        val item: UIWeeklySchedule = getItem(position)

        holder.bind(item)
    }

    inner class WeeklyScheduleViewHolder(
        private val binding: WeeklyScheduleItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UIWeeklySchedule) {
            // TODO 주간 스케줄 아이템 요소 바인딩
        }
    }
}

private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<UIWeeklySchedule>() {
    override fun areItemsTheSame(oldItem: UIWeeklySchedule, newItem: UIWeeklySchedule): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: UIWeeklySchedule, newItem: UIWeeklySchedule): Boolean {
        return oldItem == newItem
    }
}