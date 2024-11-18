package com.example.lista_1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lista_1.databinding.ActivityMainBinding

class Quetion (var quetion : String = "",
               var answers : Array<String> = arrayOf(""),
               var correct_ans : Int = 0)


class MainActivity : AppCompatActivity() {
    private var count = 1
    private var points: Int = 0
    private var selectedRadioButtonId: Int = -1

    private val all_questions = arrayOf(
        Quetion(
        "Jak nazywa się najgłębszy punkt na Ziemi?",
        arrayOf(
            "Rów Atakamski",
            "Rów Filipiński",
            "Rów Tonga",
            "Rów Mariański",
        ),
        4),
        Quetion(
        "Jakie jest największe zwierzę lądowe?",
        arrayOf(
            "Lew",
            "Nosorożec",
            "Słoń afrykański",
            "Żyrafa",
        ),

        3),
        Quetion(
            "Który pierwiastek chemiczny ma symbol O?",
            arrayOf(
                "Wodór",
                "Tlen",
                "Azot",
                "Węgiel",
            ),
            2),
        Quetion(
            "Ile jest planet w Układzie Słonecznym (bez Plutona)?",
            arrayOf(
                "7",
                "8",
                "9",
                "10",
            ),

            2),
        Quetion(
            "Który z tych instrumentów jest instrumentem strunowym?",
            arrayOf(
                "Fortepian",
                "Flet",
                "Perkusja",
                "Saksofon",
            ),
            1),
        Quetion(
            "Kto był pierwszym człowiekiem w kosmosie?",
            arrayOf(
                "Neil Armstrong",
                "Yuri Gagarin",
                "Buzz Aldrin",
                "John Glenn",
            ),

            1),
        Quetion(
            "Jakie miasto jest stolicą Włoch?",
            arrayOf(
                "Mediolan",
                "Neapol",
                "Rzym",
                "Florencja",
            ),
            3),
        Quetion(
            "Który z tych krajów nie leży w Europie?",
            arrayOf(
                "Francja",
                "Egipt",
                "Niemcy",
                "Polska",
            ),

            2),
        Quetion(
            "Jak nazywa się najwyższy szczyt świata?",
            arrayOf(
                "K2",
                "Lhotse",
                "Kangczendzonga",
                "Mount Everest",
            ),
            4),
        Quetion(
            "Jak nazywa się stolica Polski?",
            arrayOf(
                "Paryż",
                "Honolulu",
                "Warszawa",
                "Sosnowiec",
            ),

            3))

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter_state", count)
        outState.putInt("points_state", points)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        // Uncheck all radio buttons
        binding.answersGroup.clearCheck()

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("counter_state")
            points = savedInstanceState.getInt("points_state")
        }

        binding.quetionCounter.text = "Pytanie " + count.toString() + "/10"
        if(count == 1) {
            binding.quetionText.text = all_questions[0].quetion
            binding.answer0.text = all_questions[0].answers[0]
            binding.answer1.text = all_questions[0].answers[1]
            binding.answer2.text = all_questions[0].answers[2]
            binding.answer3.text = all_questions[0].answers[3]
            binding.progressBar.progress = count*10
        }


        binding.answersGroup.setOnCheckedChangeListener { _, checkedId ->
            selectedRadioButtonId = checkedId // Store the selected radio button ID
        }
        //increaseButton.setOnClickListener {
        binding.buttonNext.setOnClickListener {
            val correct : Int = all_questions[count-1].correct_ans
            val selectedButton = when (selectedRadioButtonId) {
                binding.answer0.id -> 1
                binding.answer1.id -> 2
                binding.answer2.id -> 3
                binding.answer3.id -> 4
                else -> -1
            }
            println("Points = $points")
            println("Selected: $selectedButton")
            println("Correct: $correct")

            if(selectedButton == correct) {
                points++
                println("Points++")
            }

            count++
            //showCount.text = count.toString()
            if(count<=10) {
                binding.quetionText.text = all_questions[count-1].quetion
                binding.answer0.text = all_questions[count-1].answers[0]
                binding.answer1.text = all_questions[count-1].answers[1]
                binding.answer2.text = all_questions[count-1].answers[2]
                binding.answer3.text = all_questions[count-1].answers[3]
                binding.progressBar.progress = count*10
            }
            binding.quetionCounter.text = "Pytanie " + count.toString() + "/10"



            binding.answersGroup.clearCheck()


            // Finish quiz
            if(count>10){
                binding.progressBar.visibility = View.GONE
                binding.cardViewQuestion.visibility = View.GONE
                binding.answersGroup.visibility = View.GONE
                binding.buttonNext.visibility = View.GONE


                binding.finalPointsText.visibility = View.VISIBLE
                binding.quetionCounter.text = "Gratulacje"
                binding.finalPointsText.text = "Zdobyłeś " + (points*10).toString() + " pkt"

            }
        }
    }




}