//package com.example.lista_7
//
//import androidx.lifecycle.ViewModel
//import kotlin.math.round
//import kotlin.random.Random
//
//class UsersViewModel : ViewModel() {
//    lateinit var exerciseListList: List<ExerciseList>
//
//    fun generateRandomLoremIpsum(minWords: Int = 5, maxWords: Int = 20): String {
//        val loremIpsumWords = listOf(
//            "Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit",
//            "sed", "do", "eiusmod", "tempor", "incididunt", "ut", "labore", "et", "dolore",
//            "magna", "aliqua", "Ut", "enim", "ad", "minim", "veniam", "quis", "nostrud",
//            "exercitation", "ullamco", "laboris", "nisi", "ut", "aliquip", "ex", "ea", "commodo",
//            "consequat"
//        )
//        return List(Random.nextInt(minWords, maxWords + 1)) { loremIpsumWords.random() }.joinToString(" ")
//    }
//
//    fun generateExerciseLists(numberOfLists: Int) {
//        val subjects = listOf("Matematyka", "PUM", "Fizyka", "Elektronika", "Algorytmy")
//        var index = 0
//        exerciseListList = List(numberOfLists) {
//            ExerciseList(
//                exercises = MutableList(Random.nextInt(1, 11)) {
//                    Exercise(
//                        content = generateRandomLoremIpsum(),
//                        points = Random.nextInt(0, 11)
//                    )
//                },
//                subject = Subject(name = subjects.random()),
//                grade = Random.nextInt(5).let { 3.0f + it * 0.5f },
//                index = index++
//            )
//        }
//    }
//
//    fun getSubjectsSummaryList(): List<ExerciseList> {
//        val groupedBySubject = exerciseListList.groupBy { it.subject.name }
//        return groupedBySubject.map { (subjectName, lists) ->
//            val meanGrade = lists.map { it.grade }.average()
//            ExerciseList(
//                subject = Subject(name = subjectName),
//                grade = meanGrade.toFloat(),
//                listNum = lists.size
//            )
//        }
//    }
//}