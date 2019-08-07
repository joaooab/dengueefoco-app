package br.com.dengueefocoApp.ui;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.dengueefocoApp.Configuracao;
import br.com.dengueefocoApp.model.Antivetorial;
import br.com.dengueefocoApp.R;
import br.com.dengueefocoApp.model.Status;
import br.com.dengueefocoApp.model.Usuario;
import br.com.dengueefocoApp.util.Util;

import java.util.List;

public class AntivetorialAdapter extends RecyclerView.Adapter<AntivetorialAdapter.ViewHolder> {

	private List<Antivetorial> lista;
	private AntivetorialAdapterCallback callback;
	private Usuario usuarioLogado;

	AntivetorialAdapter(List<Antivetorial> lista, AntivetorialAdapterCallback callback) {
		this.lista = lista;
		this.callback = callback;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
		usuarioLogado = new Configuracao(parent.getContext()).getUsuarioLogado();
		return new ViewHolder(LayoutInflater
				.from(parent.getContext())
				.inflate(R.layout.item_antivetorial, parent, false), parent.getContext());
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
		Antivetorial antivetorial = lista.get(position);
		View itemView = viewHolder.itemView;
		itemView.setOnClickListener(v -> callback.onClick(antivetorial));
		itemView.setOnLongClickListener(v -> {
			callback.onLongClick(antivetorial);
			return true;
		});
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
			distrito.setText(antivetorial.getSetor());
			larvicida.setText(antivetorial.getLarvicida());
			data.setText(Util.getData(antivetorial.getDataVisita()));
			horario.setText(Util.getHora(antivetorial.getDataVisita()));
			notificado.setText(Util.getNotificado(antivetorial.getNotificado()));
			status.setText(antivetorial.getStatus());
			if(antivetorial.getStatus().equals(Status.NAO_ENVIANDO.valor) ) {
				int color = ContextCompat.getColor(context, android.R.color.holo_red_dark);
				status.setTextColor(color);
			} else if(antivetorial.getStatus().equals(Status.ENVIADO.valor)) {
				int color = ContextCompat.getColor(context, android.R.color.holo_green_dark);
				status.setTextColor(color);
			}
		}

		private TextView data;
		private TextView horario;
		private TextView distrito;
		private TextView larvicida;
		private TextView status;
		private TextView notificado;
		private Context context;

		ViewHolder(@NonNull View itemView, Context context) {
			super(itemView);
			distrito = itemView.findViewById(R.id.textViewDistrito);
			larvicida = itemView.findViewById(R.id.textViewLarvicida);
			status = itemView.findViewById(R.id.textViewStatus);
			data = itemView.findViewById(R.id.textViewData);
			horario = itemView.findViewById(R.id.textViewHorario);
			notificado = itemView.findViewById(R.id.textViewNotificado);
			this.context = context;
		}
	}
}

interface AntivetorialAdapterCallback {
	void onClick(Antivetorial antivetorial);

	void onLongClick(Antivetorial antivetorial);
}
