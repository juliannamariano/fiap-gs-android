package com.example.globalsolution.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.globalsolution.R
import com.example.globalsolution.model.Evento

class EventoAdapter(
    private val listaEventos: MutableList<Evento>,
    private val onExcluirClick: (Int) -> Unit
) : RecyclerView.Adapter<EventoAdapter.EventoViewHolder>() {

    class EventoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val local: TextView = view.findViewById(R.id.textLocal)
        val tipo: TextView = view.findViewById(R.id.textTipo)
        val impacto: TextView = view.findViewById(R.id.textImpacto)
        val data: TextView = view.findViewById(R.id.textData)
        val pessoas: TextView = view.findViewById(R.id.textPessoas)
        val btnExcluir: Button = view.findViewById(R.id.btnExcluir)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_evento, parent, false)
        return EventoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val evento = listaEventos[position]
        holder.local.text = "Local: ${evento.local}"
        holder.tipo.text = "Tipo: ${evento.tipo}"
        holder.impacto.text = "Impacto: ${evento.impacto}"
        holder.data.text = "Data: ${evento.data}"
        holder.pessoas.text = "Pessoas Afetadas: ${evento.pessoasAfetadas}"

        holder.btnExcluir.setOnClickListener {
            onExcluirClick(position)
        }
    }

    override fun getItemCount(): Int = listaEventos.size
}
