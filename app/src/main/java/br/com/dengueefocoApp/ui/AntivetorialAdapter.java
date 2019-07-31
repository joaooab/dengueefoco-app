package br.com.dengueefocoApp.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.dengueefocoApp.model.Antivetorial;
import br.com.dengueefocoApp.R;

import java.util.List;

public class AntivetorialAdapter extends RecyclerView.Adapter<AntivetorialAdapter.ViewHolder> {

	private List<Antivetorial> lista;

	AntivetorialAdapter(List<Antivetorial> lista) {
		this.lista = lista;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
		return new ViewHolder(LayoutInflater
				.from(parent.getContext())
				.inflate(R.layout.item_antivetorial, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
		Antivetorial antivetorial = lista.get(position);
		viewHolder.bindView(antivetorial);
	}

	@Override
	public int getItemCount() {
		return lista.size();
	}

	void atualizaLista(List<Antivetorial> novaLista) {
		this.lista.clear();
		this.lista.addAll(novaLista);
		notifyDataSetChanged();
	}

	class ViewHolder extends RecyclerView.ViewHolder {

		void bindView(Antivetorial antivetorial) {
			id.setText(String.valueOf(antivetorial.getId()));
			distrito.setText(antivetorial.getLarvicida());
			data.setText(antivetorial.getDataVisita());
		}

		private TextView id;
		private TextView data;
		private TextView distrito;

		ViewHolder(@NonNull View itemView) {
			super(itemView);
			id = itemView.findViewById(R.id.textViewId);
			distrito = itemView.findViewById(R.id.textViewDistrito);
			data = itemView.findViewById(R.id.textViewData);
		}
	}
}
