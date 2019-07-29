package br.com.maximasistemas.dengueefoco_app.ui;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.maximasistemas.dengueefoco_app.AppDatabase;
import br.com.maximasistemas.dengueefoco_app.R;
import br.com.maximasistemas.dengueefoco_app.model.AntivetorialDao;

public class FormularioAntivetorialFragment extends Fragment {

    private AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "database-name").build();
    private AntivetorialDao antivetorialDao = db.antivetorialDao();

    static FormularioAntivetorialFragment newInstance() {
		return new FormularioAntivetorialFragment();
	}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_formulario_antivetorial, container, false);
	}
}
