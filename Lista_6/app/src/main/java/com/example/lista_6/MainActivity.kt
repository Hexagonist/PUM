package com.example.lista_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Question(
    val question: String,
    val answers: List<String>,
    val correctAns: Int
)

class MainActivity : ComponentActivity() {

    private val questions = listOf(
        Question(
        "Jak nazywa się najgłębszy punkt na Ziemi?",
    listOf(
        "Rów Atakamski",
        "Rów Filipiński",
        "Rów Tonga",
        "Rów Mariański"),
    4),

        Question(
        "Jakie jest największe zwierzę lądowe?",
    listOf(
    "Lew",
    "Nosorożec",
    "Słoń afrykański",
    "Żyrafa",
    ),
    3),

        Question(
        "Który pierwiastek chemiczny ma symbol O?",
    listOf(
    "Wodór",
    "Tlen",
    "Azot",
    "Węgiel",
    ),
    2),

        Question(
        "Ile jest planet w Układzie Słonecznym (bez Plutona)?",
    listOf(
    "7",
    "8",
    "9",
    "10",
    ),

    2),

        Question(
        "Który z tych instrumentów jest instrumentem strunowym?",
    listOf(
    "Fortepian",
    "Flet",
    "Perkusja",
    "Saksofon",
    ),
    1),

        Question(
        "Kto był pierwszym człowiekiem w kosmosie?",
    listOf(
    "Neil Armstrong",
    "Yuri Gagarin",
    "Buzz Aldrin",
    "John Glenn",
    ),

    1),

        Question(
        "Jakie miasto jest stolicą Włoch?",
    listOf(
    "Mediolan",
    "Neapol",
    "Rzym",
    "Florencja",
    ),
    3),

        Question(
        "Który z tych krajów nie leży w Europie?",
    listOf(
    "Francja",
    "Egipt",
    "Niemcy",
    "Polska",
    ),

    2),

        Question(
        "Jak nazywa się najwyższy szczyt świata?",
    listOf(
    "K2",
    "Lhotse",
    "Kangczendzonga",
    "Mount Everest",
    ),
    4),

        Question(
        "Jak nazywa się stolica Polski?",
    listOf(
    "Paryż",
    "Honolulu",
    "Warszawa",
    "Sosnowiec",
    ),

    3))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizApp(questions)
        }
    }
}

@Composable
fun QuizApp(questions: List<Question>) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var showResult by remember { mutableStateOf(false) }

    if (showResult) {
        ResultScreen(score, questions.size)
    } else {
        QuestionScreen(
            question = questions[currentQuestionIndex],
            questionNumber = currentQuestionIndex + 1,
            totalQuestions = questions.size,
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it },
            onNextClicked = {
                if (selectedOption == questions[currentQuestionIndex].correctAns) {
                    score++
                }
                if (currentQuestionIndex + 1 < questions.size) {
                    currentQuestionIndex++
                    selectedOption = null
                } else {
                    showResult = true
                }
            }
        )
    }
}

@Composable
fun QuestionScreen(
    question: Question,
    questionNumber: Int,
    totalQuestions: Int,
    selectedOption: Int?,
    onOptionSelected: (Int) -> Unit,
    onNextClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Pytanie $questionNumber/$totalQuestions",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 16.dp)
        )

        LinearProgressIndicator(
            progress = { questionNumber.toFloat() / totalQuestions },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            color = Color(0xFF006600),
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(Color(0xFFD9D9D9))
        ) {
            Text(
                text = question.question,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(Color(0xFFFFE6E6))
        ) {
            Column {
                question.answers.forEachIndexed { index, answer ->
                    RadioButtonWithText(
                        text = answer,
                        isSelected = selectedOption == index + 1,
                        onClick = { onOptionSelected(index + 1) }
                    )
                }
            }
        }

        Button(
            onClick = {
                if (selectedOption != null) {
                    onNextClicked()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF66CC99))
        ) {
            Text(
                text = "Następne",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun RadioButtonWithText(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .background(Color(0xFFD9D9D9))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = isSelected, onClick = onClick)
        Text(
            text = text,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 8.dp),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ResultScreen(score: Int, totalQuestions: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Gratulacje!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "Zdobyłeś ${score * 10} pkt",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuizApp(
        listOf(
            Question(
                "Jak nazywa się najgłębszy punkt na Ziemi?",
                listOf("Rów Atakamski", "Rów Filipiński", "Rów Tonga", "Rów Mariański"),
                4
            )
        )
    )
}