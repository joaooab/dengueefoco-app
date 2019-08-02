package br.com.dengueefocoApp.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;

import br.com.dengueefocoApp.AppDatabase;
import br.com.dengueefocoApp.model.Antivetorial;
import br.com.dengueefocoApp.model.AntivetorialDao;
import br.com.dengueefocoApp.R;
import br.com.dengueefocoApp.util.Util;

import java.util.Arrays;
import java.util.List;

public class FormularioAntivetorialFragment extends Fragment implements LocationListener {

	private MainActivity mActivity;
	private AntivetorialDao antivetorialDao;
	private EditText editTextAgente;
	private EditText editTextQuantidade;
	private EditText editTextCep;
	private String larvicidaSelecionado;
	private String statusImovelSelecionado;
	private String tipoImoveSelecionado;
	private LocationManager locationManager;
	private int LOCATION_PERMISSION_CODE = 1;

	static FormularioAntivetorialFragment newInstance() {
		return new FormularioAntivetorialFragment();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = (MainActivity) getActivity();
		mActivity.setActionBarTitle("Cadastrar antivetorial");
		antivetorialDao = AppDatabase.newInstance(getContext()).antivetorialDao();
		locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
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
		configuraTipoImovel(view);
		configuraStatusImovel(view);
		configuraSpinnerLarvicida(view);
		configuraBotaoSalvar(view);
		configuraBotaoLimpar(view);
		configuraGps(view);

		super.onViewCreated(view, savedInstanceState);
	}

	private void configuraGps(@NonNull View view) {
		ImageView iconGps = view.findViewById(R.id.iconGps);
		iconGps.setOnClickListener(v -> requestPermissions());

	}

	private void configuraTipoImovel(@NonNull View view) {
		final List<String> tipoImovel = Arrays.asList("Casa", "Apartamento", "Terreno baldio", "Comércio");
		ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(tipoImovel);
        Spinner spinnerStatusImovel = view.findViewById(R.id.spinnerTipoImovel);
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

	private ArrayAdapter<String> criaArrayAdapterSpinner(List<String> lista) {
		return new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, lista);
	}

	private void configuraStatusImovel(@NonNull View view) {
		final List<String> statusImovel = Arrays.asList("Visitado", "Fechado", "Recusado");
		ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(statusImovel);
        Spinner spinnerStatusImovel = view.findViewById(R.id.spinnerStatusImovel);
        spinnerStatusImovel.setAdapter(stringArrayAdapter);
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

	private void configuraSpinnerLarvicida(View view) {
		final List<String> larvicidas = Arrays.asList("Sinopsade", "Pryriproxfen");
		ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(larvicidas);
        Spinner spinnerLarvicida = view.findViewById(R.id.spinnerLarvicida);
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
		buttonLimpar.setOnClickListener(v -> limparFormulario());
	}

	private void limparFormulario() {
		editTextAgente.setText("");
		editTextQuantidade.setText("");
		editTextCep.setText("");
	}

	private void configuraBotaoSalvar(@NonNull View view) {
		Button buttonSalvar = view.findViewById(R.id.buttonSalvar);
		buttonSalvar.setOnClickListener(v -> {
			Antivetorial antivetorial = criaAntivetorial();
			salvaAntivetorial(antivetorial);
			Toast.makeText(getContext(), "Operação realizada com sucesso", Toast.LENGTH_SHORT).show();
			limparFormulario();
		});
	}

	private void salvaAntivetorial(final Antivetorial antivetorial) {
		antivetorialDao.insertAll(antivetorial);
	}

	private Antivetorial criaAntivetorial() {
		Long agente = Long.valueOf(editTextAgente.getText().toString());
		Double quantidade = Double.valueOf(editTextQuantidade.getText().toString());
		String cep = editTextQuantidade.getText().toString();

		Antivetorial antivetorial = new Antivetorial();
		antivetorial.setIdUsuario(agente);
		antivetorial.setQtdLarvicida(quantidade);
		antivetorial.setLarvicida(larvicidaSelecionado);
		antivetorial.setStatusImovel(statusImovelSelecionado);
		antivetorial.setTipoImovel(tipoImoveSelecionado);
		antivetorial.setDataVisita(Util.getDataHojeString());

		return antivetorial;
	}

    @Override
    public void onResume() {
        super.onResume();
    }

    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
            )) {
            } else {
                ActivityCompat.requestPermissions(
                        getActivity(),
                        new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_PERMISSION_CODE
                );
            }
        } else {
            startLocationRequests();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationRequests();
            }
        }
    }

    @SuppressLint ("MissingPermission")
    private void startLocationRequests() {
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                0f,
                this
        );
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("onLocationChanged", location.getLatitude() + String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i("onStatusChanged", provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.i("onProviderEnabled", provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.i("onProviderDisabled", provider);
    }
}
