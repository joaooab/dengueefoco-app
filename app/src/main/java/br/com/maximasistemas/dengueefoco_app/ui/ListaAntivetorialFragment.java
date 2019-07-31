package br.com.maximasistemas.dengueefoco_app.ui;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
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
import br.com.maximasistemas.dengueefoco_app.AppDatabase;
import br.com.maximasistemas.dengueefoco_app.R;
import br.com.maximasistemas.dengueefoco_app.model.Antivetorial;
import br.com.maximasistemas.dengueefoco_app.model.AntivetorialDao;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ListaAntivetorialFragment extends Fragment {

	private AntivetorialDao antivetorialDao;
	private AntivetorialAdapter adapter;

	static ListaAntivetorialFragment newInstance() {
		return new ListaAntivetorialFragment();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		antivetorialDao = AppDatabase.newInstance(getContext()).antivetorialDao();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_lista_antivetorial, container, false);
		configuraFab(view);
//		Antivetorial antivetorial = new Antivetorial();
//		antivetorial.setIdUsuario(16L);
//		antivetorial.setQtdLarvicida(5D);
//		antivetorial.setLarvicida("larvicidaSelecionado");
//		antivetorial.setStatusImovel("statusImovelSelecionado");
//		antivetorial.setTipoImovel("tipoImoveSelecionado");
//		antivetorial.setDataVisita(new Date());
//		List<Antivetorial> antivetorials = Arrays.asList(antivetorial);
		configuraLista(view);
		return view;
	}

	private void configuraLista(View view) {
		RecyclerView recyclerView = view.findViewById(R.id.recyclerViewAntivetorial);
		adapter = new AntivetorialAdapter(new ArrayList<>());
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
			MainActivity activity = (MainActivity) getActivity();
			if (activity != null) {
				activity.abreFragment(FormularioAntivetorialFragment.newInstance());
			}
		});
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}
}
