package br.com.dengueefocoApp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.dengueefocoApp.R;
import br.com.dengueefocoApp.model.Antivetorial;
import br.com.dengueefocoApp.model.RegistroDiario;
import br.com.dengueefocoApp.util.Util;

public class RegistroDiarioAdapter extends RecyclerView.Adapter<RegistroDiarioAdapter.ViewHolder> {

    private List<RegistroDiario> lista;
    private RegistroDiarioAdapterCallback callback;

    public RegistroDiarioAdapter(List<RegistroDiario> lista, RegistroDiarioAdapterCallback callback) {
        this.lista = lista;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                                      .inflate(R.layout.item_registro_diario, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final RegistroDiario registroDiario = lista.get(position);
        View itemView = viewHolder.itemView;
        itemView.setOnClickListener(v -> callback.onClick(registroDiario));
        itemView.setOnLongClickListener(v -> {
            callback.onLongClick(registroDiario);
            return true;
        });
        viewHolder.bindView(registroDiario);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    void atualizaLista(List<RegistroDiario> novaLista) {
        this.lista.clear();
        this.lista.addAll(novaLista);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        void bindView(RegistroDiario registroDiario) {
            distrito.setText(registroDiario.getDistrito());
            larvicida.setText(registroDiario.getLarvicida());
            data.setText(Util.getData(registroDiario.getData()));
            horario.setText(Util.getHora(registroDiario.getData()));
            bairro.setText(registroDiario.getBairro());
            ciclo.setText(registroDiario.getCiclo());
            supervisor.setText(registroDiario.getSupervisor());
        }

        private TextView data;
        private TextView horario;
        private TextView distrito;
        private TextView larvicida;
        private TextView bairro;
        private TextView ciclo;
        private TextView supervisor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            distrito = itemView.findViewById(R.id.textViewDistrito);
            larvicida = itemView.findViewById(R.id.textViewLarvicida);
            bairro = itemView.findViewById(R.id.textViewBairro);
            data = itemView.findViewById(R.id.textViewData);
            horario = itemView.findViewById(R.id.textViewHorario);
            ciclo = itemView.findViewById(R.id.textViewCiclo);
            supervisor = itemView.findViewById(R.id.textViewSupervisor);
        }
    }

}

interface RegistroDiarioAdapterCallback {
    void onClick(RegistroDiario registroDiario);

    void onLongClick(RegistroDiario registroDiario);
}

