package br.com.dengueefocoApp.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.dengueefocoApp.R;
import br.com.dengueefocoApp.model.Antivetorial;
import br.com.dengueefocoApp.model.Status;
import br.com.dengueefocoApp.util.Util;

public class DetalheAntivetorialFragment extends Fragment {

	private MainActivity mActivity;
	private static Antivetorial antivetorial;
	private TextView larvicida;
	private TextView quantidade;
	private TextView status;
	private TextView data;
	private TextView statusImovel;
	private TextView tipoImovel;
	private TextView notificado;
	private TextView horario;
	private TextView logradouro;
	private TextView setor;
	private TextView quadra;
	private TextView lote;
	private TextView numero;
	private TextView observacao;

	static DetalheAntivetorialFragment newInstance(final Antivetorial antivetorial) {
		setAntivetorial(antivetorial);
		return new DetalheAntivetorialFragment();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = (MainActivity) getActivity();
		mActivity.setActionBarTitle("Detalhes antivetorial");
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_detalhe_antivetorial, container, false);
		larvicida = view.findViewById(R.id.textViewLarvicida);
		quantidade = view.findViewById(R.id.textViewQuantidade);
		status = view.findViewById(R.id.textViewLabelStatus);
		data = view.findViewById(R.id.textViewData);
		statusImovel = view.findViewById(R.id.textViewStatusImovel);
		tipoImovel = view.findViewById(R.id.textViewTipoImovel);
		notificado = view.findViewById(R.id.textViewBairro);
		horario = view.findViewById(R.id.textViewHorario);
		logradouro = view.findViewById(R.id.textViewLogradouro);
		setor = view.findViewById(R.id.textViewSetor);
		quadra = view.findViewById(R.id.textViewQuadra);
		lote = view.findViewById(R.id.textViewLote);
		numero = view.findViewById(R.id.textViewNumero);
		observacao = view.findViewById(R.id.textViewObservacao);
		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		larvicida.setText(antivetorial.getLarvicida());
		quantidade.setText(String.valueOf(antivetorial.getQtdLarvicida()));
		status.setText(antivetorial.getStatus());
		notificado.setText(Util.getNotificado(antivetorial.getNotificado()));
		horario.setText(Util.getHora(antivetorial.getDataVisita()));
		logradouro.setText(antivetorial.getLogradouro());
		setor.setText(antivetorial.getSetor());
		quadra.setText(antivetorial.getQuadra());
		lote.setText(antivetorial.getLote());
		numero.setText(antivetorial.getNumero());
		observacao.setText(antivetorial.getObservacao());
		if (antivetorial.getStatus().equals(Status.ENVIADO.valor)) {
			int cor = ContextCompat.getColor(getContext(), android.R.color.holo_green_dark);
			status.setTextColor(cor);
		}
		data.setText(Util.getData(antivetorial.getDataVisita()));
		tipoImovel.setText(antivetorial.getTipoImovel());
	}

	private static Antivetorial getAntivetorial() {
		return antivetorial;
	}

	private static void setAntivetorial(Antivetorial antivetorial) {
		DetalheAntivetorialFragment.antivetorial = antivetorial;
	}

}
