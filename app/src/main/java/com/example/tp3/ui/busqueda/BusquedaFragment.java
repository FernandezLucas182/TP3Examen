package com.example.tp3.ui.busqueda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.tp3.R;
import com.example.tp3.databinding.FragmentBusquedaBinding;
import com.example.tp3.model.Producto;

public class BusquedaFragment extends Fragment {

    private BusquedaViewModel busquedaViewModel;
    private FragmentBusquedaBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        busquedaViewModel = new ViewModelProvider(this).get(BusquedaViewModel.class);
        binding = FragmentBusquedaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnBuscarProducto.setOnClickListener(v -> {
            String codigo = binding.etCodigoBusqueda.getText().toString();
            busquedaViewModel.buscarProductoPorCodigo(codigo);
        });


        busquedaViewModel.getNavegarADetalle().observe(getViewLifecycleOwner(), producto -> {
            if (producto != null) {

                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);


                Bundle args = new Bundle();
                args.putSerializable("producto_arg", producto);

                navController.navigate(R.id.action_nav_busqueda_to_detalleProductoFragment, args);
            }
        });


        busquedaViewModel.getMensajeBusqueda().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje != null && !mensaje.isEmpty()) {

                Toast.makeText(getContext(), mensaje, Toast.LENGTH_LONG).show();
                binding.tvResultadoBusqueda.setText(""); // Limpiar el resultado anterior si lo hubiera
            }
        });

        busquedaViewModel.getMensajeBusqueda().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje == null || mensaje.isEmpty()) {
                binding.tvResultadoBusqueda.setText("");
            } else {
                binding.tvResultadoBusqueda.setText(mensaje);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
