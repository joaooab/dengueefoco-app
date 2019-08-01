package br.com.dengueefocoApp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.dengueefocoApp.AppDatabase;
import br.com.dengueefocoApp.model.Antivetorial;
import br.com.dengueefocoApp.model.AntivetorialDao;
import br.com.dengueefocoApp.R;
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
        antivetorialDao = AppDatabase.newInstance(getContext()).antivetorialDao();
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
            if (mActivity != null) mActivity.abreFragment(FormularioAntivetorialFragment.newInstance(), true);
        });
    }

    @Override
    public void onClick(Antivetorial antivetorial) {
        if (mActivity != null) mActivity.abreFragment(DetalheAntivetorialFragment.newInstance(antivetorial));
    }

    @Override
    public void onLongClick(Antivetorial antivetorial) {

    }
}
