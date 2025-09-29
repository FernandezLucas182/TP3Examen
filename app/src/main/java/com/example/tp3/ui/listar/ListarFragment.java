package com.example.tp3.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView; // Para el TextView de lista vacía

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tp3.databinding.FragmentListarBinding;
import com.example.tp3.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ListarFragment extends Fragment {

    private ListarViewModel listarViewModel;

    private FragmentListarBinding binding;
    private ProductoAdapter productoAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listarViewModel =
                new ViewModelProvider(this).get(ListarViewModel.class);


        binding = FragmentListarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.rvListaProductos.setLayoutManager(new LinearLayoutManager(getContext()));
        productoAdapter = new ProductoAdapter(new ArrayList<>()); // Inicializa con lista vacía
        binding.rvListaProductos.setAdapter(productoAdapter);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listarViewModel.getListaProductos().observe(getViewLifecycleOwner(), new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                // actualiza el adaptador con la nueva lista
                productoAdapter.actualizarProductos(productos);
            }
        });

        listarViewModel.getListaVacia().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean estaVacia) {
                if (estaVacia) {
                    binding.rvListaProductos.setVisibility(View.GONE);
                    binding.tvListaVacia.setVisibility(View.VISIBLE);
                } else {
                    binding.rvListaProductos.setVisibility(View.VISIBLE);
                    binding.tvListaVacia.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        if (listarViewModel != null) {
            listarViewModel.cargarProductos();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
