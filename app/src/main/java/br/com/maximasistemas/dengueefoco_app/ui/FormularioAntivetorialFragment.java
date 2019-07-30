package br.com.maximasistemas.dengueefoco_app.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import br.com.maximasistemas.dengueefoco_app.R;
import br.com.maximasistemas.dengueefoco_app.model.Antivetorial;

import java.util.Arrays;
import java.util.List;

public class FormularioAntivetorialFragment extends Fragment {

//	private AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "database-name").build();
//	private AntivetorialDao antivetorialDao = db.antivetorialDao();
	private EditText editTextAgente;
	private Spinner spinnerLarvicida;
	private EditText editTextQuantidade;
	private Spinner spinnerStatusImovel;
	private Spinner spinnerTipoImovel;
	private EditText editTextCep;
	private String larvicidaSelecionado;
	private String statusImovelSelecionado;
	private String tipoImoveSelecionado;

	static FormularioAntivetorialFragment newInstance() {
		return new FormularioAntivetorialFragment();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_formulario_antivetorial, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		editTextAgente = view.findViewById(R.id.editTextAgente);
		editTextQuantidade = view.findViewById(R.id.editTextQuantidade);
		editTextCep = view.findViewById(R.id.editTextCep);
		configuraTipoImovel();
		configuraStatusImovel(view);
		configuraSpinnerLarvicida();
		configuraBotaoSalvar(view);
		configuraBotaoLimpar(view);

		super.onViewCreated(view, savedInstanceState);
	}

	private void configuraTipoImovel() {
		final List<String> tipoImovel = Arrays.asList("Tipo de imóvel", "Casa", "Apartamento", "Terreno baldio", "Comércio");
		ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, tipoImovel);
		spinnerStatusImovel.setAdapter(stringArrayAdapter);
		spinnerStatusImovel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				tipoImoveSelecionado = tipoImovel.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	private void configuraStatusImovel(@NonNull View view) {
		final List<String> statusImovel = Arrays.asList("Status do imóvel", "Visitado", "Fechado", "Recusado");
		ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, statusImovel);
		spinnerStatusImovel.setAdapter(stringArrayAdapter);
		spinnerStatusImovel = view.findViewById(R.id.spinnerStatusImovel);
		spinnerStatusImovel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				statusImovelSelecionado = statusImovel.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	private void configuraSpinnerLarvicida() {
		final List<String> larvicidas = Arrays.asList("Sinopsade", "Pryriproxfen");
		ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, larvicidas);
		spinnerLarvicida.setAdapter(stringArrayAdapter);
		spinnerLarvicida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				larvicidaSelecionado = larvicidas.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	private void configuraBotaoLimpar(@NonNull View view) {
		Button buttonLimpar = view.findViewById(R.id.buttonLimpar);
		buttonLimpar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				limparFormulario();
			}
		});
	}

	private void limparFormulario() {
		editTextAgente.setText("");
		editTextQuantidade.setText("");
		editTextCep.setText("");
	}

	private void configuraBotaoSalvar(@NonNull View view) {
		Button buttonSalvar = view.findViewById(R.id.buttonSalvar);
		buttonSalvar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Long agente = Long.valueOf(editTextAgente.getText().toString());
				Double quantidade = Double.valueOf(editTextQuantidade.getText().toString());
				String cep = editTextQuantidade.getText().toString();
				Antivetorial antivetorial = new Antivetorial();
				antivetorial.setIdUsuario(agente);
				antivetorial.setQtdLarvicida(quantidade);
				antivetorial.setLarvicida(larvicidaSelecionado);
				antivetorial.setStatusImovel(statusImovelSelecionado);
				antivetorial.setTipoImovel(tipoImoveSelecionado);
				Toast.makeText(getContext(), "Operação realizada com sucesso", Toast.LENGTH_SHORT).show();
				limparFormulario();
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
	}
}
