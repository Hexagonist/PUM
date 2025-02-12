package com.example.lista2
import androidx.lifecycle.ViewModel
import kotlin.math.round
import kotlin.random.Random


class UsersViewModel : ViewModel() {
    lateinit var exerciseList_list: List<ExerciseList>

    // Function to generate random Lorem Ipsum text
    fun generateRandomLoremIpsum(minWords: Int = 5, maxWords: Int = 20): String {
        val loremIpsumWords = listOf(
            "Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit",
            "sed", "do", "eiusmod", "tempor", "incididunt", "ut", "labore", "et", "dolore",
            "magna", "aliqua", "Ut", "enim", "ad", "minim", "veniam", "quis", "nostrud",
            "exercitation", "ullamco", "laboris", "nisi", "ut", "aliquip", "ex", "ea", "commodo",
            "consequat"
        )

        val numberOfWords = Random.nextInt(minWords, maxWords + 1) // Generate word count
        return List(numberOfWords) { loremIpsumWords.random() }.joinToString(" ")
    }

    fun generateExerciseLists(numberOfLists: Int) {
        val subjects = listOf("Matematyka", "PUM", "Fizyka", "Elektronika", "Algorytmy")


        var index = 0
        var lists = List(numberOfLists) {
            val exerciseList = ExerciseList()

            // Assign a random subject from the list
            exerciseList.subject = Subject(name = subjects.random())

            // Assign a random grade between 3.0 and 5.0 with a step of 0.5
            exerciseList.grade = Random.nextInt(5).let { 3.0f + it * 0.5f }

            exerciseList.index = index
            index++

            // Create a random number of exercises (1 to 10)
            val numberOfExercises = Random.nextInt(1, 11) // Upper bound exclusive
            exerciseList.exercises = List(numberOfExercises) {
                Exercise(
                    content = generateRandomLoremIpsum(), // Generate random Lorem Ipsum content
                    points = Random.nextInt(0, 11) // Points between 0 and 10
                )
            }.toMutableList()



            exerciseList
        }
        exerciseList_list = lists
    }

    fun getList(): List<ExerciseList> = exerciseList_list



    fun getSubjectsSummaryList() : List<ExerciseList> {
        // Group ExerciseLists by subject name
        val groupedBySubject = exerciseList_list.groupBy { it.subject.name }

        // Create a single ExerciseList for each subject
        return groupedBySubject.map { (subjectName, lists) ->
            // Combine exercises and calculate mean grade
//            val combinedExercises = lists.flatMap { it.exercises }
            val meanGrade = lists.map { it.grade }.average()
            val listsCount = lists.count()

            // Create new ExerciseList
            ExerciseList().apply {
                subject = Subject(name = subjectName)
                grade = meanGrade.round(2).toFloat()
//                exercises = combinedExercises.toMutableList()
                listNum = listsCount
            }
        }
    }
}

//    fun addUser(user: User) {
//        users_list.add(user)
//    }

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}
