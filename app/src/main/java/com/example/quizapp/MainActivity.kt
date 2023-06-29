package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val QUIZ_COUNT = 10

    private val quizData = mutableListOf(
        // questão, resposta correta, escolha1, escolha2, escolha3
        mutableListOf("Qual é uma linguagem de programação orientada a objetos?", "Python", "C", "Assembly", "SQL"),
        mutableListOf("Qual das seguintes linguagens é usada principalmente para desenvolvimento web?", "Java", "Ruby", "C++", "Swift"),
        mutableListOf("Qual das seguintes linguagens é uma linguagem de programação funcional?", "Haskell", "JavaScript", "PHP", "Objective-C"),
        mutableListOf("Qual das seguintes linguagens é usada para desenvolver aplicativos iOS?", "Objective-C", "Go", "Kotlin", "#"),
        mutableListOf("Qual das seguintes linguagens é uma linguagem de script usada para desenvolvimento de páginas web interativas?", "JavaScript", "HTML", "CSS", "XML"),
        mutableListOf("Qual das seguintes linguagens é usada principalmente para desenvolvimento de jogos?", "C++", "Java", "Python", "Ruby"),
        mutableListOf("Qual das seguintes linguagens é usada para análise e manipulação de dados?", "R", "Swift", "Pascal", "COBOL"),
        mutableListOf("Qual das seguintes linguagens é usada para desenvolvimento de aplicativos Android?", "Kotlin", "Objective-C", "C#", "Ruby"),
        mutableListOf("Qual das seguintes linguagens é usada para consultas em bancos de dados relacionais?", "SQL", "JavaScript", "PHP", "Python"),
        mutableListOf("Qual das seguintes linguagens é usada para desenvolvimento de inteligência artificial?", "Python", "C++", "Java", "Swift"),
        mutableListOf("Qual das seguintes linguagens é uma linguagem compilada?", "C", "Ruby", "Python", "Perl"),
        mutableListOf("Qual das seguintes linguagens é usada para desenvolvimento de aplicativos desktop?", "C#", "JavaScript", "PHP", "Go"),
        mutableListOf("Qual das seguintes linguagens é usada para criação de conteúdo web estático?", "HTML", "CSS", "JavaScript", "Ruby"),
        mutableListOf("Qual das seguintes linguagens é usada para desenvolvimento de aplicativos financeiros?", "COBOL", "R", "Swift", "Pascal"),
        mutableListOf("Qual das seguintes linguagens é usada para desenvolvimento de aplicativos de realidade virtual?", "C++", "Python", "Java", "Ruby"),
        mutableListOf("Qual das seguintes linguagens é usada para desenvolvimento de aplicativos de jogos mobile?", "Kotlin", "Objective-C", "C#", "Ruby"),
        mutableListOf("Qual das seguintes linguagens é usada para consultas em bancos de dados não relacionais?", "MongoDB Query Language (MQL)", "JavaScript", "PHP", "SQL"),
        mutableListOf("Qual das seguintes linguagens é usada para desenvolvimento de aplicativos de aprendizado de máquina?", "Python", "Java", "C++", "Swift"),
        mutableListOf("Qual das seguintes linguagens é usada para criação de páginas web estilizadas?", "CSS", "HTML", "JavaScript", "XML")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Embaralhar quiz.
        quizData.shuffle()


        showNextQuiz()
    }

    fun showNextQuiz() {
        // Atualizar countLabel
        binding.countLabel.text = getString(R.string.count_label, quizCount)

        // Escolher conjunto de perguntas
        val quiz = quizData[0]

        // Definir pergunta
        binding.questionLabel.text = quiz[0]
        rightAnswer = quiz[1]

        // Remover questão do questionário
        quiz.removeAt(0)

        // Embaralhar respostas e escolhas
        quiz.shuffle()

        // Definir escolhas
        binding.answerBtn1.text = quiz[0]
        binding.answerBtn2.text = quiz[1]
        binding.answerBtn3.text = quiz[2]
        binding.answerBtn4.text = quiz[3]

        // Remover este questionário do quizData
        quizData.removeAt(0)

    }

    fun checkAnswer(view: View) {
        // Obter botão clicado
        val answerBtn: Button = findViewById(view.id)
        val btntext = answerBtn.text.toString()

        val alertTitle: String
        if (btntext == rightAnswer) {
            // Acertou
            alertTitle = "Correto!"
            rightAnswerCount++
        } else {
            // Errou
            alertTitle = "Errou..."
        }

        // Criar Alert
        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("Resposta: $rightAnswer")
            .setPositiveButton("OK") {dialogInterface, i ->
                checkQuizCount()
            }
            .setCancelable(false)
            .show()

    }

    fun checkQuizCount() {
        if (quizCount == QUIZ_COUNT) {
            // Mostrar resultado
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount)
            startActivity(intent)

        } else {
            quizCount++
            showNextQuiz()
        }
    }
}













