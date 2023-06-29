package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_result)

        // Instância de ActivityResultBinding que contém as referências aos elementos do layout.
        binding = ActivityResultBinding.inflate(layoutInflater)

        // Raiz do layout inflado
        val view = binding.root

        // Define a View principal do layout como o conteúdo da atividade.
        setContentView(view)

        // Obtendo um valor chamado "RIGHT_ANSWER_COUNT" e, se ele não estiver presente, o valor padrão será 0.
        val score = intent.getIntExtra("RIGHT_ANSWER_COUNT", 0)

        // Atribuindo um texto à propriedade text do elemento resultLabel do layout.
        // O texto é obtido a partir do recurso de string result_score com um valor
        // substituído usando a função getString. O valor substituído é o score que obtivemos anteriormente.
        binding.resultLabel.text = getString(R.string.result_score, score)

        // Configurando um OnClickListener para o botão tryAgainBtn definido no layout. Quando o botão
        // for clicado, será executado o código dentro do bloco {}.
        // Criando uma nova Intent para iniciar a MainActivity e, em seguida, iniciando essa Intent com
        // a função startActivity.
        binding.tryAgainBtn.setOnClickListener {
            startActivity(Intent(this@ResultActivity, MainActivity::class.java))
        }
    }
}