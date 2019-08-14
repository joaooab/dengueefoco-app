package br.com.dengueefocoApp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import br.com.dengueefocoApp.AppDatabase;
import br.com.dengueefocoApp.Configuracao;
import br.com.dengueefocoApp.R;
import br.com.dengueefocoApp.api.RetrofitClient;
import br.com.dengueefocoApp.model.*;
import com.google.gson.JsonElement;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.*;

public class FormularioAntivetorialFragment extends Fragment {

	private MainActivity mActivity;
	private AntivetorialDao antivetorialDao;
	//	private EditText editTextCep;
//	private int LOCATION_PERMISSION_CODE = 1;
//	private FusedLocationProviderClient fusedLocationClient;
//	private Location location;
//	private LocationCallback locationCallback;
//	private LocationRequest mLocationRequest;
	private TextView editTextQuadra;
	private TextView editTextNumLote;
	private Switch switchNotificado;
	private TextView editTextLogradouro;
	private TextView editTextObservacao;
	private Configuracao configuracao;
	private Spinner spinnerTipoImovel;
	private Spinner spinnerLarvicida;
	private Spinner spinnerDistrito;
	private Spinner spinnerPendencia;
	private TextView editTextNumQuarto;
	private Switch switchImovelFoco;
	private Spinner spinnerCiclo;
	private BairroDao bairroDao;
	private Spinner spinnerBairro;
	private Spinner spinnerLado;
	private Spinner spinnerSequencia;
	private Spinner spinnerQtdGrama;
	private Spinner spinnerQtdDepTrat;
	private Spinner spinnerTipoFoco;
	private Spinner spinnerQtdFoco;
	private Spinner spinnerNumDepElim;

	static FormularioAntivetorialFragment newInstance() {
		return new FormularioAntivetorialFragment();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = (MainActivity) getActivity();
		mActivity.setActionBarTitle("Cadastrar antivetorial");
		configuracao = new Configuracao(getContext());
		AppDatabase db = AppDatabase.getInstance(getContext());
		antivetorialDao = db.antivetorialDao();
		bairroDao = db.bairroDao();
//		fusedLocationClient = LocationServices.getFusedLocationProviderClient(mActivity);
//		mLocationRequest = LocationRequest.create()
//				.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
//				.setInterval(60000)      // 10 seconds, in milliseconds
//				.setFastestInterval(10000); // 1 second, in milliseconds
//		locationCallback = new LocationCallback() {
//			@Override
//			public void onLocationResult(LocationResult locationResult) {
//				Log.e("teste", "teste");
//				if (locationResult == null) {
//					return;
//				}
//				for (Location location : locationResult.getLocations()) {
//					// Update UI with location data
//					// ...
//					FormularioAntivetorialFragment.this.location = location;
//					Log.i("onLocationChanged", location.getLatitude() + String.valueOf(location.getLongitude()));
//					geocode();
//				}
//			}
//
//		};
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_formulario_antivetorial, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//		editTextCep = view.findViewById(R.id.editTextCep);
		editTextQuadra = view.findViewById(R.id.editTextQuadra);
		editTextNumLote = view.findViewById(R.id.editTextNumLote);
		switchNotificado = view.findViewById(R.id.switchNotificado);
		switchImovelFoco = view.findViewById(R.id.switchImovelFoco);
		editTextLogradouro = view.findViewById(R.id.editTextLogradouro);
		editTextObservacao = view.findViewById(R.id.editTextObservacao);
		editTextNumQuarto = view.findViewById(R.id.editTextNumQuarto);
		configuraTipoImovel(view);
		configuraSpinnerLarvicida(view);
		configuraSpinnerDistrito(view);
		configuraSpinnerPendencia(view);
		configuraSpinnerCiclo(view);
		configuraSpinnerBairro(view);
		configuraSpinnerLado(view);
		configuraSpinnerSequencia(view);
		configuraSpinnerQtdGrama(view);
		configuraSpinnerQtdDepTrat(view);
		configuraSpinnerQtdFoco(view);
		configuraSpinnerTipoFoco(view);
		configuraSpinnerNumDepElim(view);
		configuraBotaoSalvar(view);
		configuraBotaoLimpar(view);
//		configuraGps(view);

		super.onViewCreated(view, savedInstanceState);
	}

//	private void configuraGps(@NonNull View view) {
//		ImageView iconGps = view.findViewById(R.id.iconGps);
//		iconGps.setOnClickListener(v -> startLocationRequests());
//	}

	private void configuraTipoImovel(@NonNull View view) {
		final List<String> tipoImovel = Arrays.asList("Residencial", "Comércio", "Terreno baldio", "Ponto Estratégico", "Outros");
		ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(tipoImovel);
		spinnerTipoImovel = view.findViewById(R.id.spinnerTipoImovel);
		spinnerTipoImovel.setAdapter(stringArrayAdapter);
	}

	private ArrayAdapter<String> criaArrayAdapterSpinner(List<String> lista) {
		return new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, lista);
	}

	private void configuraSpinnerLarvicida(View view) {
		final List<String> larvicidas = Arrays.asList("Sinopsade", "Pryriproxfen");
		ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(larvicidas);
		spinnerLarvicida = view.findViewById(R.id.spinnerLarvicida);
		spinnerLarvicida.setAdapter(stringArrayAdapter);
	}


	private void configuraSpinnerDistrito(View view) {
		final List<String> distritos = Distrito.getDistrito();
		ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(distritos);
		spinnerDistrito = view.findViewById(R.id.spinnerDistrito);
		spinnerDistrito.setAdapter(stringArrayAdapter);
		spinnerDistrito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String distrito = distritos.get(position);
				buscarBairros(distrito);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	private void configuraSpinnerPendencia(View view) {
		final List<String> lista = Arrays.asList("Recusado", "Abandonado", "Fechado", "Imobiliaria");
		ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(lista);
		spinnerPendencia = view.findViewById(R.id.spinnerPendencia);
		spinnerPendencia.setAdapter(stringArrayAdapter);
	}


	private void configuraSpinnerCiclo(View view) {
		final List<String> lista = Arrays.asList("1", "2", "3", "4", "5", "6");
		ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(lista);
		spinnerCiclo = view.findViewById(R.id.spinnerCiclo);
		spinnerCiclo.setAdapter(stringArrayAdapter);
	}

	private void configuraSpinnerLado(View view) {
		final List<String> lista = Arrays.asList("1", "2", "3", "4");
		ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(lista);
		spinnerLado = view.findViewById(R.id.spinnerLado);
		spinnerLado.setAdapter(stringArrayAdapter);
	}

	private void configuraSpinnerSequencia(View view) {
		final List<String> lista = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
		ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(lista);
		spinnerSequencia = view.findViewById(R.id.spinnerSequencia);
		spinnerSequencia.setAdapter(stringArrayAdapter);
	}

	private void configuraSpinnerQtdGrama(View view) {
		final List<String> lista = Arrays.asList("1/8", "1/4", "1/2", "3");
		ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(lista);
		spinnerQtdGrama = view.findViewById(R.id.spinnerQtdGrama);
		spinnerQtdGrama.setAdapter(stringArrayAdapter);
	}

	private void configuraSpinnerQtdDepTrat(View view) {
		final List<String> lista = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
		ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(lista);
		spinnerQtdDepTrat = view.findViewById(R.id.spinnerQtdDepTrat);
		spinnerQtdDepTrat.setAdapter(stringArrayAdapter);
	}

	private void configuraSpinnerQtdFoco(View view) {
		final List<String> lista = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
		ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(lista);
		spinnerQtdFoco = view.findViewById(R.id.spinnerQtdFoco);
		spinnerQtdFoco.setAdapter(stringArrayAdapter);
	}

	private void configuraSpinnerTipoFoco(View view) {
		final List<String> lista = Arrays.asList("Bacia", "Balde", "Bebedouro de animais", "Bromélia", "Caixa d'agua", "Caixa de Passagem", "Calha", "Cascata", "Fonte de água", "Garrafa pet", "Lata", "Lona", "Piscina", "Pneu", "Pratinho planta", "Ralinhos", "Tambor", "Vaso de Planta", "Vaso Sanitário", "Outros");
		ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(lista);
		spinnerTipoFoco = view.findViewById(R.id.spinnerTipoFoco);
		spinnerTipoFoco.setAdapter(stringArrayAdapter);
	}

	private void configuraSpinnerNumDepElim(View view) {
		final List<String> lista = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
		ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(lista);
		spinnerNumDepElim = view.findViewById(R.id.spinnerNumDepElim);
		spinnerNumDepElim.setAdapter(stringArrayAdapter);
	}

	private void configuraSpinnerBairro(View view) {
		spinnerBairro = view.findViewById(R.id.spinnerBairro);
		String distrito = spinnerDistrito.getSelectedItem().toString();
		buscarBairros(distrito);
	}

	private void buscarBairros(String distrito) {
		bairroDao.getAllByDistrito(distrito)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(lista -> listaBairros(lista), e -> Log.e(this.getTag(), e.getMessage()));
	}

	private void listaBairros(List<Bairro> bairros) {
		List<String> lista = new ArrayList<>();
		for (Bairro bairro : bairros) {
			lista.add(bairro.getNome());
		}
		Collections.sort(lista);
		ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(lista);
		spinnerBairro.setAdapter(stringArrayAdapter);
	}

	private void configuraBotaoLimpar(@NonNull View view) {
		Button buttonLimpar = view.findViewById(R.id.buttonLimpar);
		buttonLimpar.setOnClickListener(v -> limparFormulario());
	}

	private void limparFormulario() {
//		editTextCep.setText("");
		editTextLogradouro.setText("");
		editTextQuadra.setText("");
		editTextNumLote.setText("");
		editTextObservacao.setText("");
		editTextNumQuarto.setText("");
	}

	private void configuraBotaoSalvar(@NonNull View view) {
		Button buttonSalvar = view.findViewById(R.id.buttonSalvar);
		buttonSalvar.setOnClickListener(v -> {
			Antivetorial antivetorial = criaAntivetorial();
			salvaAntivetorial(antivetorial);
			limparFormulario();
		});
	}

	private void salvaAntivetorial(final Antivetorial antivetorial) {
		RetrofitClient.getApi().salvarAntivetorial2(antivetorial).enqueue(new Callback<JsonElement>() {
			@Override
			public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
				Toast.makeText(getContext(), "Operação realizada com sucesso", Toast.LENGTH_SHORT).show();
				antivetorial.setStatus(Status.ENVIADO.valor);
				antivetorialDao.insertAll(antivetorial);
			}

			@Override
			public void onFailure(Call<JsonElement> call, Throwable t) {
				Toast.makeText(getContext(), "Falha ao enviar formulário", Toast.LENGTH_SHORT).show();
				antivetorial.setStatus(Status.NAO_ENVIANDO.valor);
				antivetorialDao.insertAll(antivetorial);
			}
		});
	}

	private Antivetorial criaAntivetorial() {
		String quadra = editTextQuadra.getText().toString();
		String numLote = editTextNumLote.getText().toString();
		String logradouro = editTextLogradouro.getText().toString();
		String observacao = editTextObservacao.getText().toString();
		String numQuarto = editTextNumQuarto.getText().toString();

		boolean notificado = switchNotificado.isChecked();
		boolean imovelFoco = switchImovelFoco.isChecked();

		Antivetorial antivetorial = new Antivetorial();
		antivetorial.setIdUsuario(configuracao.getUsuarioLogado().getId());
		antivetorial.setLarvicida(spinnerLarvicida.getSelectedItem().toString());
		antivetorial.setTipoImovel(spinnerTipoImovel.getSelectedItem().toString());
		antivetorial.setDataVisita(new Date());
		antivetorial.setStatus(Status.NAO_ENVIANDO.valor);
		antivetorial.setQuadra(quadra);
		antivetorial.setNumLote(numLote);
		antivetorial.setNotificado(notificado);
		antivetorial.setImovelFoco(imovelFoco);
//		antivetorial.setLatitude(location.getLatitude());
//		antivetorial.setLongitude(location.getLongitude());
		antivetorial.setLogradouro(logradouro);
		antivetorial.setObservacao(observacao);
		antivetorial.setQuarteirao(numQuarto);
		antivetorial.setCilo(spinnerCiclo.getSelectedItem().toString());
		antivetorial.setDistrito(spinnerDistrito.getSelectedItem().toString());
		antivetorial.setPendencia(spinnerPendencia.getSelectedItem().toString());
		antivetorial.setLado(spinnerLado.getSelectedItem().toString());
		antivetorial.setSequencia(spinnerSequencia.getSelectedItem().toString());
		antivetorial.setQtdLarvicida(spinnerQtdGrama.getSelectedItem().toString());
		antivetorial.setQtdDepTratado(spinnerQtdDepTrat.getSelectedItem().toString());
		antivetorial.setTipoFoco(spinnerTipoFoco.getSelectedItem().toString());
		antivetorial.setQtdFoco(spinnerQtdFoco.getSelectedItem().toString());
		antivetorial.setNumDepEliminado(spinnerNumDepElim.getSelectedItem().toString());
		antivetorial.setBairro(spinnerBairro.getSelectedItem().toString());

		return antivetorial;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

//	private void startLocationRequests() {
//		if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
//				!= PackageManager.PERMISSION_GRANTED) {
//			if (ActivityCompat.shouldShowRequestPermissionRationale(
//					getActivity(),
//					Manifest.permission.ACCESS_FINE_LOCATION
//			)) {
//			} else {
//				ActivityCompat.requestPermissions(
//						getActivity(),
//						new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//						LOCATION_PERMISSION_CODE
//				);
//			}
//		} else {
//			initLocationRequests();
//		}
//		geocode();
//	}
//
//	@Override
//	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//		if (requestCode == LOCATION_PERMISSION_CODE) {
//			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//				startLocationRequests();
//			}
//		}
//	}

//	@SuppressLint("MissingPermission")
//	private void initLocationRequests() {
//		fusedLocationClient.requestLocationUpdates(
//				mLocationRequest,
//				locationCallback,
//				null /* Looper */
//		);
//	}
//
//	private void geocode() {
//		if (location == null) {
//			return;
//		}
//		ViaCepApi viaCepApi = RetrofitClient.getViaCepApi();
//		String cep = editTextCep.getText().toString();
//		if (cep.isEmpty() || !isCepValido(cep)) {
//			editTextCep.setError("CEP inválido");
//			return;
//		}
//		viaCepApi.getEndereco(cep).enqueue(new Callback<JsonElement>() {
//			@Override
//			public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
//				Endereco endereco = new Gson().fromJson(response.body(), Endereco.class);
//				editTextLogradouro.setText(endereco.getLogradouro());
//			}
//
//			@Override
//			public void onFailure(Call<JsonElement> call, Throwable t) {
//				Log.e(this.getClass().getSimpleName(), t.getMessage());
//			}
//		});
//        String latitude = String.valueOf(location.getLatitude());
//        String longitude = String.valueOf(location.getLongitude());
//        String latLng = String.format("%s, %s", latitude, longitude);
//        GoogleApi service = RetrofitClient.getGoogleApi();
//        Call<JsonElement> call = service.reverseGeocode(latLng, "AIzaSyBuuq_Td4NjK9DSowVe03PegsojAI70Hxw");
//        call.enqueue(new Callback<JsonElement>() {
//            @Override
//            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
//                Log.e("Response", "sucesso");
//            }
//
//            @Override
//            public void onFailure(Call<JsonElement> call, Throwable t) {
//                Log.e("Response", "error");
//            }
//        });

//	}

//	private boolean isCepValido(String cep) {
//		Pattern p = Pattern.compile("[0-9]{8}$");
//		Matcher m = p.matcher(cep);
//		return m.find();
//	}

//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//		fusedLocationClient.removeLocationUpdates(locationCallback);
//	}

}
