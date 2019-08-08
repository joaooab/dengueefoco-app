package br.com.dengueefocoApp.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.com.dengueefocoApp.AppDatabase;
import br.com.dengueefocoApp.R;
import br.com.dengueefocoApp.model.AntivetorialDao;
import br.com.dengueefocoApp.model.RegistroDiario;
import br.com.dengueefocoApp.model.RegistroDiarioDao;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ListaAntivetorialFragment extends Fragment implements RegistroDiarioAdapterCallback {

    private MainActivity mActivity;
    private RegistroDiarioDao registroDiarioDao;
    private RegistroDiarioAdapter adapter;

    static ListaAntivetorialFragment newInstance() {
        return new ListaAntivetorialFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        registroDiarioDao = AppDatabase.newInstance(getContext()).registroDiarioDao();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_antivetorial, container, false);
        configuraFab(view);
        configuraLista(view);
        return view;
    }

    private void configuraLista(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewAntivetorial);
        adapter = new RegistroDiarioAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.setActionBarTitle("Registro diario Antivetorial");
        processaLista();
    }

    private void processaLista() {
        registroDiarioDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::processaLista, e -> Log.e(this.getTag(), e.getMessage()));
    }

    private void processaLista(List<RegistroDiario> lista) {
        adapter.atualizaLista(lista);
    }

    private void configuraFab(View view) {
        final FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            if (mActivity != null) {
                mActivity.abreFragment(FormularioRegistroDiarioFragment.newInstance(), true);
            }
        });
    }

    @Override
    public void onClick(RegistroDiario registroDiario) {
        if (mActivity != null) {
//			mActivity.abreFragment(DetalheAntivetorialFragment.newInstance(registroDiario));
        }
    }

    @Override
    public void onLongClick(final RegistroDiario registroDiario) {
        new AlertDialog.Builder(getContext())
                .setTitle("Tem certeza que deseja excluir?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    registroDiarioDao.delete(registroDiario);
                    processaLista();
                    Toast.makeText(getContext(), "Item excluído com sucesso", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Não", (dialog, which) -> {
                })
                .create()
                .show();
    }

}
