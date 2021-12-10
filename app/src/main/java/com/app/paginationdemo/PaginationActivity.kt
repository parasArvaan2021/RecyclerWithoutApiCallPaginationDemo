package com.app.paginationdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class PaginationActivity : AppCompatActivity() {
    var list = arrayListOf<QuestionListDataClass>()
    lateinit var rvPagination: RecyclerView
    var pageDataListCount = 5
    var currentPage = 0
    var totalPage = 0
    lateinit var adapter: ListQuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagination)

        rvPagination = findViewById(R.id.rvPagination)
        dataSet()

        findViewById<Button>(R.id.btnPaginationSubmit).setOnClickListener {
            if (currentPage == totalPage)
                checkQuestionRightOrWrong()
            else
                refreshRecyclerView()
        }
    }

    private fun getRandomSubset(count: Int): MutableList<Int?> {

        val list = ArrayList<Int?>()
        for (i in 0..count) {
            list.add(i)
        }
        list.shuffle()
        return list.subList(0, count)
    }

    @SuppressLint("SetTextI18n")
    private fun checkQuestionRightOrWrong() {
        var attemptOrNot = 0
        var selectedAnsTrue = 0
        var selectedAnsFalse = 0
        for (i in 0 until list.size) {
            if (list[i].selectedAnswer == "") {
                attemptOrNot++
            } else {
                if (list[i].selectedAnswer == list[i].answer)
                    selectedAnsTrue++
                else
                    selectedAnsFalse++
            }
        }
        try {
            Log.e("TAG", "checkQuestionRightOrWrong: $selectedAnsTrue == ${list.size}")
            var percentage = 0f
            if (selectedAnsTrue != 0)
                percentage = (100 * selectedAnsTrue / list.size).toFloat()
            findViewById<TextView>(R.id.tvPaginationPercentage).text = "$percentage %"
            findViewById<TextView>(R.id.tvPaginationAttempt).text = "N/A::$attemptOrNot"
            findViewById<TextView>(R.id.tvPaginationRight).text = "True:$selectedAnsTrue "
            findViewById<TextView>(R.id.tvPaginationWrong).text = "False:$selectedAnsFalse "
            adapter.emptyData()
        } catch (e: ArithmeticException) {
            Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
        }
    }

    private fun dataSet() {
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val count = bundle.getInt("QUESTION")
            list = arrayListOf()

            for (i in 0 until count) {
                val randomForQuestion = Random().nextInt(count)
                val randomNumberArray = getRandomSubset(4)

                val listItem = QuestionListDataClass(
                    "Question ${i+1}",
                    "${randomNumberArray[0]}",
                    "${randomNumberArray[1]}",
                    "${randomNumberArray[2]}",
                    "${randomNumberArray[3]}",
                    "${randomNumberArray[Random().nextInt(4)]}",
                    "",
                    (i / pageDataListCount) + 1

                )

                if (i == count - 1) {
                    totalPage = listItem.pageNumber
                }

                list.add(listItem)
                Log.e("TAG", "current: $randomNumberArray")

            }
            rvPagination.layoutManager = LinearLayoutManager(this)
            adapter = ListQuestionAdapter(this, list)
            rvPagination.adapter = adapter
            rvPagination.setHasFixedSize(true)
            refreshRecyclerView()
        }
    }

    private fun refreshRecyclerView() {

        val newList = arrayListOf<QuestionListDataClass>()
        currentPage++

        list.forEach {
            if (it.pageNumber == currentPage)
                newList.add(it)
        }

        Log.e("TAG", "current: $list")

        adapter.setUpListData(newList)
    }
}
