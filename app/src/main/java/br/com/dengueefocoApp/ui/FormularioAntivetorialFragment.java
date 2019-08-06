package br.com.dengueefocoApp.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.gson.JsonElement;

import java.util.Arrays;
import java.util.List;

import br.com.dengueefocoApp.AppDatabase;
import br.com.dengueefocoApp.Configuracao;
import br.com.dengueefocoApp.R;
import br.com.dengueefocoApp.api.GoogleApi;
import br.com.dengueefocoApp.api.RetrofitClient;
import br.com.dengueefocoApp.model.Antivetorial;
import br.com.dengueefocoApp.model.AntivetorialDao;
import br.com.dengueefocoApp.model.Status;
import br.com.dengueefocoApp.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormularioAntivetorialFragment extends Fragment {

    private MainActivity mActivity;
    private AntivetorialDao antivetorialDao;
    private EditText editTextQuantidade;
    private EditText editTextCep;
    private String larvicidaSelecionado;
    private String statusImovelSelecionado;
    private String tipoImoveSelecionado;
    private int LOCATION_PERMISSION_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private Location location;
    private LocationCallback locationCallback;
    private LocationRequest mLocationRequest;
    private TextView editTextQuadra;
    private TextView editTextLote;
    private TextView editTextNumero;
    private Switch switchNotificado;
    private TextView editTextSetor;
    private Configuracao configuracao;

    static FormularioAntivetorialFragment newInstance() {
        return new FormularioAntivetorialFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mActivity.setActionBarTitle("Cadastrar antivetorial");
        configuracao = new Configuracao(getContext());
        antivetorialDao = AppDatabase.newInstance(getContext()).antivetorialDao();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(mActivity);
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(60000)      // 10 seconds, in milliseconds
                .setFastestInterval(10000); // 1 second, in milliseconds
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Log.e("teste", "teste");
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Update UI with location data
                    // ...
                    FormularioAntivetorialFragment.this.location = location;
                    Log.i("onLocationChanged", location.getLatitude() + String.valueOf(location.getLongitude()));
                    geocode();
                }
            }

        };
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_formulario_antivetorial, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        editTextQuantidade = view.findViewById(R.id.editTextQuantidade);
        editTextCep = view.findViewById(R.id.editTextCep);
        editTextQuadra = view.findViewById(R.id.editTextQuadra);
        editTextLote = view.findViewById(R.id.editTextLote);
        editTextNumero = view.findViewById(R.id.editTextNumero);
        switchNotificado = view.findViewById(R.id.switchNotificado);
        editTextSetor = view.findViewById(R.id.editTextSetor);
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
        iconGps.setOnClickListener(v -> startLocationRequests());

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
        editTextQuantidade.setText("");
        editTextCep.setText("");
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
        antivetorial.setStatus(Status.AGUARDANDO.valor);
        RetrofitClient.getApi().salvaAntivetorial(antivetorial).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Toast.makeText(getContext(), "Operação realizada com sucesso", Toast.LENGTH_SHORT).show();
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
        Double quantidade = Double.valueOf(editTextQuantidade.getText().toString());
        String cep = editTextCep.getText().toString();
        String setor = editTextSetor.getText().toString();
        String quadra = editTextQuadra.getText().toString();
        String lote = editTextLote.getText().toString();
        String numero = editTextNumero.getText().toString();
        boolean notificado = switchNotificado.isChecked();

        Antivetorial antivetorial = new Antivetorial();
        antivetorial.setIdUsuario(configuracao.getUsuarioLogado().getId());
        antivetorial.setIdSupervisor(configuracao.getUsuarioLogado().getIdSupervisor());
        antivetorial.setQtdLarvicida(quantidade);
        antivetorial.setLarvicida(larvicidaSelecionado);
        antivetorial.setStatusImovel(statusImovelSelecionado);
        antivetorial.setTipoImovel(tipoImoveSelecionado);
        antivetorial.setDataVisita(Util.getDataHojeString());
        antivetorial.setStatus(Status.NAO_ENVIANDO.valor);
        antivetorial.setSetor(setor);
        antivetorial.setQuadra(quadra);
        antivetorial.setLote(lote);
        antivetorial.setNumero(numero);
        antivetorial.setNotificado(notificado);

        return antivetorial;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void startLocationRequests() {
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
            initLocationRequests();
        }
        geocode();
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
    private void initLocationRequests() {
        fusedLocationClient.requestLocationUpdates(
                mLocationRequest,
                locationCallback,
                null /* Looper */
        );
    }

    private void geocode() {
        if (location == null) {
            return;
        }
        String latitude = String.valueOf(location.getLatitude());
        String longitude = String.valueOf(location.getLongitude());
        String latLng = String.format("%s, %s", latitude, longitude);
        GoogleApi service = RetrofitClient.getGoogleApi();
        Call<JsonElement> call = service.reverseGeocode(latLng, "AIzaSyBuuq_Td4NjK9DSowVe03PegsojAI70Hxw");
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.e("Response", "sucesso");
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("Response", "error");
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

}
