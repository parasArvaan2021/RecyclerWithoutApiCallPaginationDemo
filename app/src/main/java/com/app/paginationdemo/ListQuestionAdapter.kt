package com.app.paginationdemo

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListQuestionAdapter(
    private val context: Context,
    private val list: ArrayList<QuestionListDataClass>
) :
    RecyclerView.Adapter<ListQuestionAdapter.QuestionListViewHolder>() {

    private var newList: ArrayList<QuestionListDataClass> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionListViewHolder {
        return QuestionListViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_question, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: QuestionListViewHolder, position: Int) {
        val currentData = newList[position]

        holder.answerRightOrNot.visibility = View.GONE
        holder.tvQuestion.text = currentData.question
        holder.rbChoiceOne.text = "A) " + currentData.choiceOne
        holder.rbChoiceTwo.text = "B) " + currentData.choiceTwo
        holder.rbChoiceThree.text = "C) " + currentData.choiceThree
        holder.rbChoiceFour.text = "D) " + currentData.choiceFour
        holder.tvQuestionCount.text = "${position + 1})."

        Log.i("TAG", "currentData: $currentData")
        if (currentData.selectedAnswer != "") {
            when (currentData.selectedAnswer) {
                holder.rbChoiceOne.text.toString().substringAfter(" ") -> {
                    holder.rbChoiceOne.isChecked = true
                }
                holder.rbChoiceTwo.text.toString().substringAfter(" ") -> {
                    holder.rbChoiceTwo.isChecked = true
                }
                holder.rbChoiceThree.text.toString().substringAfter(" ") -> {
                    holder.rbChoiceThree.isChecked = true
                }
                holder.rbChoiceFour.text.toString().substringAfter(" ") -> {
                    holder.rbChoiceFour.isChecked = true
                }
                else -> {
                    holder.radioGroup.clearCheck()
                }
            }
        } else {
            holder.radioGroup.clearCheck()
        }

        holder.rbChoiceOne.setOnClickListener {

            val selectedAnswer = holder.rbChoiceOne.text.toString()
            Log.i("TAG", "onBindViewHolder:$position $selectedAnswer")
            currentData.selectedAnswer = selectedAnswer.substringAfter(" ")

            Log.e("TAG", "onBindViewHolderAns:${currentData.selectedAnswer}")
            changesInMainList(currentData, position, selectedAnswer)
        }
        holder.rbChoiceTwo.setOnClickListener {
            val selectedAnswer = holder.rbChoiceTwo.text.toString()
            Log.i("TAG", "onBindViewHolder:$position $selectedAnswer")
            currentData.selectedAnswer = selectedAnswer.substringAfter(" ")

            Log.e("TAG", "onBindViewHolderAns:${currentData.selectedAnswer}")
            changesInMainList(currentData, position, selectedAnswer)
        }
        holder.rbChoiceThree.setOnClickListener {
            val selectedAnswer = holder.rbChoiceThree.text.toString()
            Log.i("TAG", "onBindViewHolder:$position $selectedAnswer")
            currentData.selectedAnswer = selectedAnswer.substringAfter(" ")

            Log.e("TAG", "onBindViewHolderAns:${currentData.selectedAnswer}")
            changesInMainList(currentData, position, selectedAnswer)
        }
        holder.rbChoiceFour.setOnClickListener {
            val selectedAnswer = holder.rbChoiceFour.text.toString()
            Log.i("TAG", "onBindViewHolder:$position $selectedAnswer")
            currentData.selectedAnswer = selectedAnswer.substringAfter(" ")

            Log.e("TAG", "onBindViewHolderAns:${currentData.selectedAnswer}")
            changesInMainList(currentData, position, selectedAnswer)
        }

        Log.e("TAG", "current:${currentData.answer}   $position")


    }


    override fun getItemCount(): Int {
        return newList.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun setUpListData(newList: ArrayList<QuestionListDataClass>) {
        this.newList = newList
        notifyDataSetChanged()
    }

    fun emptyData() {
        this.newList = arrayListOf()
        notifyDataSetChanged()
    }

    inner class QuestionListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQuestion: TextView = itemView.findViewById(R.id.tvRvQuestion)
        val rbChoiceOne: RadioButton = itemView.findViewById(R.id.rbChoiceOne)
        val rbChoiceTwo: RadioButton = itemView.findViewById(R.id.rbChoiceTwo)
        val rbChoiceThree: RadioButton = itemView.findViewById(R.id.rbChoiceThree)
        val rbChoiceFour: RadioButton = itemView.findViewById(R.id.rbChoicefour)
        val tvQuestionCount: TextView = itemView.findViewById(R.id.tvQuestionCount)
        val radioGroup: RadioGroup = itemView.findViewById(R.id.radioGroup)
        val answerRightOrNot: ImageView = itemView.findViewById(R.id.ivShowRightOrWrong)
    }

    fun changesInMainList(
        currentData: QuestionListDataClass,
        position: Int,
        selectedAnswer: String
    ) {
        val pageNo = currentData.pageNumber
        val mainArrayPosition = ((pageNo - 1) * newList.size) + position
        val currentMainData = list[mainArrayPosition]
        currentMainData.selectedAnswer = selectedAnswer.substringAfter(" ")
    }
}