package br.com.dengueefocoApp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import br.com.dengueefocoApp.AppDatabase;
import br.com.dengueefocoApp.Configuracao;
import br.com.dengueefocoApp.R;
import br.com.dengueefocoApp.model.RegistroDiario;
import br.com.dengueefocoApp.model.RegistroDiarioDao;
import br.com.dengueefocoApp.model.Usuario;
import br.com.dengueefocoApp.util.Util;

public class FormularioRegistroDiarioFragment extends Fragment {

    private RegistroDiarioDao registroDiarioDao;
    private Spinner spinnerDistrito;
    private Spinner spinnerBairro;
    private Spinner spinnerLarvicida;
    private Spinner spinnerSupervisor;
    private EditText editTextCiclo;
    private Usuario usuarioLogado;

    static FormularioRegistroDiarioFragment newInstance() {
        return new FormularioRegistroDiarioFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registroDiarioDao = AppDatabase.newInstance(getContext()).registroDiarioDao();
        usuarioLogado = new Configuracao(getContext()).getUsuarioLogado();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_formulario_antivetorial, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        spinnerDistrito = view.findViewById(R.id.spinnerDistrito);
        spinnerBairro = view.findViewById(R.id.spinnerBairro);
        spinnerLarvicida = view.findViewById(R.id.spinnerLarvicida);
        spinnerSupervisor = view.findViewById(R.id.spinnerSupervisor);
        editTextCiclo = view.findViewById(R.id.editTextCiclo);
        configuraBotaoSalvar(view);

        super.onViewCreated(view, savedInstanceState);
    }

    private void configuraBotaoSalvar(@NonNull View view) {
        Button buttonSalvar = view.findViewById(R.id.buttonSalvar);
        buttonSalvar.setOnClickListener(v -> {
            String distrito = spinnerDistrito.getSelectedItem().toString();
            String bairro = spinnerBairro.getSelectedItem().toString();
            String larvicida = spinnerLarvicida.getSelectedItem().toString();
            String supervisor = spinnerSupervisor.getSelectedItem().toString();
            String ciclo = editTextCiclo.getText().toString();

            RegistroDiario registroDiario = new RegistroDiario(usuarioLogado.getNome(), usuarioLogado.getMatricula(), distrito, bairro, larvicida, supervisor, ciclo, Util.getDataHojeString());
            registroDiarioDao.insertAll(registroDiario);
        });
    }

}
