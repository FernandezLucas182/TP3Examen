package com.example.tp3.ui.detalle; // Asegúrate que el paquete sea correcto

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.tp3.databinding.FragmentDetalleProductoBinding; // Importa tu ViewBinding generado
import com.example.tp3.model.Producto;
import java.util.Locale;

public class DetalleProductoFragment extends Fragment {

    private FragmentDetalleProductoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleProductoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        if (getArguments() != null) {

            Producto producto = (Producto) getArguments().getSerializable("producto_arg");
            if (producto != null) {
                binding.tvDetalleCodigo.setText(producto.getCodigo());
                binding.tvDetalleDescripcion.setText(producto.getDescripcion());
                binding.tvDetallePrecio.setText(String.format(Locale.getDefault(), "$%.2f", producto.getPrecio()));
            }
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
