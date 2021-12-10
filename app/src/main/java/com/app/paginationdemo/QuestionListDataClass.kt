package com.app.paginationdemo

data class QuestionListDataClass(
    var question:String,
    var choiceOne:String,
    var choiceTwo:String,
    var choiceThree:String,
    var choiceFour:String,
    var answer:String,
    var selectedAnswer:String,
    var pageNumber:Int
)
