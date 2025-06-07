package com.example.globalsolution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globalsolution.adapter.EventoAdapter
import com.example.globalsolution.model.Evento

class MainActivity : AppCompatActivity() {

    private lateinit var editLocal: EditText
    private lateinit var editTipo: EditText
    private lateinit var editImpacto: EditText
    private lateinit var editData: EditText
    private lateinit var editPessoas: EditText
    private lateinit var btnIncluir: Button
    private lateinit var recyclerView: RecyclerView

    private val listaEventos = mutableListOf<Evento>()
    private lateinit var adapter: EventoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editLocal = findViewById(R.id.editLocal)
        editTipo = findViewById(R.id.editTipo)
        editImpacto = findViewById(R.id.editImpacto)
        editData = findViewById(R.id.editData)
        editPessoas = findViewById(R.id.editPessoas)
        btnIncluir = findViewById(R.id.btnIncluir)
        recyclerView = findViewById(R.id.recyclerEventos)


        adapter = EventoAdapter(listaEventos) { position ->
            removerEvento(position)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        btnIncluir.setOnClickListener {
            adicionarEvento()


        }

        val btnIdentificacao: Button = findViewById(R.id.btnIdentificacao)

        btnIdentificacao.setOnClickListener {
            val intent = Intent(this, IdentificacaoActivity::class.java)
            startActivity(intent)
        }

    }

    private fun adicionarEvento() {
        val local = editLocal.text.toString()
        val tipo = editTipo.text.toString()
        val impacto = editImpacto.text.toString()
        val data = editData.text.toString()
        val pessoasTexto = editPessoas.text.toString()


        if (local.isBlank() || tipo.isBlank() || impacto.isBlank() || data.isBlank() || pessoasTexto.isBlank()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        val pessoasAfetadas = pessoasTexto.toIntOrNull()
        if (pessoasAfetadas == null || pessoasAfetadas <= 0) {
            Toast.makeText(this, "Número de pessoas deve ser maior que 0", Toast.LENGTH_SHORT).show()
            return
        }


        val evento = Evento(local, tipo, impacto, data, pessoasAfetadas)
        listaEventos.add(evento)
        adapter.notifyItemInserted(listaEventos.size - 1)

        editLocal.text.clear()
        editTipo.text.clear()
        editImpacto.text.clear()
        editData.text.clear()
        editPessoas.text.clear()
    }

    private fun removerEvento(position: Int) {
        listaEventos.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}
