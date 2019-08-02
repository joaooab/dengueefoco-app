package br.com.dengueefocoApp.ui;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.Arrays;
import java.util.List;

import br.com.dengueefocoApp.AppDatabase;
import br.com.dengueefocoApp.R;
import br.com.dengueefocoApp.model.Antivetorial;
import br.com.dengueefocoApp.model.AntivetorialDao;
import br.com.dengueefocoApp.util.Util;

public class FormularioAntivetorialFragment extends Fragment {

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
    private FusedLocationProviderClient fusedLocationClient;
    private Double latitude;
    private Double longitude;

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
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(mActivity);
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
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(mActivity, location -> {
                        double longitude = location.getLongitude();
                    });
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

}
