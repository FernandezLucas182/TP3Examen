package com.example.tp3.ui.busqueda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.tp3.databinding.FragmentBusquedaBinding; // Importa tu ViewBinding generado

public class BusquedaFragment extends Fragment {

    private BusquedaViewModel busquedaViewModel;
    private FragmentBusquedaBinding binding; // Variable para ViewBinding

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        busquedaViewModel = new ViewModelProvider(this).get(BusquedaViewModel.class);

        // Inflar el layout usando ViewBinding
        binding = FragmentBusquedaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Configurar el listener del botón
        binding.btnBuscarProducto.setOnClickListener(v -> {
            String codigo = binding.etCodigoBusqueda.getText().toString();
            busquedaViewModel.buscarProductoPorCodigo(codigo);
        });

        // Observar los cambios en el resultado de la búsqueda
        busquedaViewModel.getResultadoBusqueda().observe(getViewLifecycleOwner(), resultado -> {
            binding.tvResultadoBusqueda.setText(resultado);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; //
    }
}