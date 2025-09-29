package com.example.tp3.ui.salir;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tp3.R;
import com.example.tp3.databinding.FragmentSalirBinding;

public class SalirFragment extends Fragment {

    private SalirViewModel salirViewModel;
    private FragmentSalirBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        salirViewModel = new ViewModelProvider(this).get(SalirViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentSalirBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        salirViewModel.mostrarDialogoConfirmacion().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean mostrar) {
                if (mostrar != null && mostrar) {
                    mostrarDialogoDeSalida();

                    salirViewModel.dialogoDeSalidaManejado();
                }
            }
        });

        salirViewModel.cerrarAplicacion().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean cerrar) {
                if (cerrar != null && cerrar) {
                    if (getActivity() != null) {
                        getActivity().finishAffinity();

                    }
                }
            }
        });

        salirViewModel.navegarAtras().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean navegar) {
                if (navegar != null && navegar) {
                    NavController navController = NavHostFragment.findNavController(SalirFragment.this);
                    navController.popBackStack();
                    salirViewModel.navegarAtrasManejado();
                }
            }
        });


        if (savedInstanceState == null) {
            salirViewModel.iniciarProcesoDeSalida();
        }
    }

    private void mostrarDialogoDeSalida() {
        if (getContext() == null) {
            return;
        }

        new AlertDialog.Builder(requireContext()) // Usar requireContext()
                .setTitle("Confirmar Salida")
                .setMessage("¿Desea salir de la app de carga de productos?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        salirViewModel.usuarioConfirmaSalir();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        salirViewModel.usuarioCancelaSalir();
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
