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

import br.com.dengueefocoApp.model.Antivetorial;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.com.dengueefocoApp.AppDatabase;
import br.com.dengueefocoApp.R;
import br.com.dengueefocoApp.model.AntivetorialDao;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ListaAntivetorialFragment extends Fragment implements AntivetorialAdapterCallback {

    private MainActivity mActivity;
    private AntivetorialDao antivetorialDao;
    private AntivetorialAdapter adapter;

    static ListaAntivetorialFragment newInstance() {
        return new ListaAntivetorialFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        antivetorialDao = AppDatabase.getInstance(getContext()).antivetorialDao();
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
        adapter = new AntivetorialAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.setActionBarTitle("Antivetorial");
        processaLista();
    }

    private void processaLista() {
        antivetorialDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::processaLista, e -> Log.e(this.getTag(), e.getMessage()));
    }

    private void processaLista(List<Antivetorial> lista) {
        adapter.atualizaLista(lista);
    }

    private void configuraFab(View view) {
        final FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            if (mActivity != null) {
                mActivity.abreFragment(FormularioAntivetorialFragment.newInstance(), true);
            }
        });
    }

    @Override
    public void onClick(Antivetorial antivetorial) {
        if (mActivity != null) {
			mActivity.abreFragment(DetalheAntivetorialFragment.newInstance(antivetorial));
        }
    }

    @Override
    public void onLongClick(final Antivetorial antivetorial) {
        new AlertDialog.Builder(getContext())
                .setTitle("Tem certeza que deseja excluir?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    antivetorialDao.delete(antivetorial);
                    processaLista();
                    Toast.makeText(getContext(), "Item excluído com sucesso", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Não", (dialog, which) -> {
                })
                .create()
                .show();
    }

}
