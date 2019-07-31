package br.com.dengueefocoApp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class ListaSupervisorFragment extends Fragment {

    private AntivetorialDao antivetorialDao;
    private SupervisorAdapter adapter;

    static ListaSupervisorFragment newInstance() {
        return new ListaSupervisorFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        antivetorialDao = AppDatabase.newInstance(getContext()).antivetorialDao();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.include_lista, container, false);
        configuraLista(view);
        return view;
    }

    private void configuraLista(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new SupervisorAdapter(new ArrayList<>());
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

}
