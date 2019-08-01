package br.com.dengueefocoApp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.dengueefocoApp.R;
import br.com.dengueefocoApp.model.Antivetorial;

public class AntivetorialDetalheFragment extends Fragment {

	private static Antivetorial antivetorial;
	private TextView agente;
	private TextView larvicida;
	private TextView quantidade;
	private TextView status;
	private TextView data;
	private TextView statusImovel;
	private TextView tipoImovel;

	public static AntivetorialDetalheFragment newInstance(final Antivetorial antivetorial) {
		setAntivetorial(antivetorial);
		return new AntivetorialDetalheFragment();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_detalhe_antivetorial, container, false);
		agente = view.findViewById(R.id.textViewAgente);
		larvicida = view.findViewById(R.id.textViewLarvicida);
		quantidade = view.findViewById(R.id.textViewQuantidade);
		status = view.findViewById(R.id.textViewStatus);
		data = view.findViewById(R.id.textViewData);
		statusImovel = view.findViewById(R.id.textViewStatusImovel);
		tipoImovel = view.findViewById(R.id.textViewTipoImovel);
		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		agente.setText(String.valueOf(antivetorial.getId()));
		larvicida.setText(antivetorial.getLarvicida());
		quantidade.setText(String.valueOf(antivetorial.getQtdLarvicida()));
		status.setText(antivetorial.getStatus());
		data.setText(antivetorial.getDataVisita());
		statusImovel.setText(antivetorial.getStatusImovel());
		tipoImovel.setText(antivetorial.getTipoImovel());
	}

	private static Antivetorial getAntivetorial() {
		return antivetorial;
	}

	private static void setAntivetorial(Antivetorial antivetorial) {
		AntivetorialDetalheFragment.antivetorial = antivetorial;
	}

}
