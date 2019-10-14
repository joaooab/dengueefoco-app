package br.com.dengueefocoApp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.dengueefocoApp.AppDatabase;
import br.com.dengueefocoApp.R;
import br.com.dengueefocoApp.model.Bairro;
import br.com.dengueefocoApp.model.BairroDao;
import br.com.dengueefocoApp.model.Distrito;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FormularioOuvitrampasFragment extends Fragment {

    private Spinner spinnerDistrito;
    private Spinner spinnerObservacao;
    private BairroDao bairroDao;
    private Spinner spinnerBairro;

    public static FormularioOuvitrampasFragment newInstance() {
        return new FormularioOuvitrampasFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDatabase db = AppDatabase.getInstance(getContext());
        bairroDao = db.bairroDao();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_formulario_ouvitrampas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerDistrito = view.findViewById(R.id.spinnerDistrito);
        configuraSpinnerDistrito(view);
        configuraSpinnerBairro(view);
        configuraSpinnerObservacoes(view);
    }

    private void configuraSpinnerObservacoes(View view) {
        String[] stringArray = getResources().getStringArray(R.array.observacoes);
        ArrayAdapter<String> stringArrayAdapter = criaArrayAdapterSpinner(Arrays.asList(stringArray));
        spinnerObservacao = view.findViewById(R.id.spinnerObservacao);
        spinnerObservacao.setAdapter(stringArrayAdapter);
        spinnerObservacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

    private ArrayAdapter<String> criaArrayAdapterSpinner(List<String> lista) {
        return new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, lista);
    }

}