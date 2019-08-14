package br.com.dengueefocoApp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.dengueefocoApp.R;

public class ListaOvitrampasFragment extends Fragment {

    private MainActivity mActivity;

    static ListaOvitrampasFragment newInstance() {
        return new ListaOvitrampasFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_antivetorial, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configuraFab(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.setActionBarTitle("Ovitrampas");
//        processaLista();
    }

    private void configuraFab(View view) {
        final FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            if (mActivity != null) {
                mActivity.abreFragment(FormularioOuvitrampasFragment.newInstance(), true);
            }
        });
    }
}
