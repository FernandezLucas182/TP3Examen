package com.example.tp3.ui.busqueda;


import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.tp3.MainActivity;
import com.example.tp3.model.Producto;

import java.util.List;
import java.util.Locale;

public class BusquedaViewModel extends AndroidViewModel {

    private MutableLiveData<String> resultadoBusqueda;

    public BusquedaViewModel(@NonNull Application application) {
        super(application);
        resultadoBusqueda = new MutableLiveData<>();
    }

    public LiveData<String> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public void buscarProductoPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            resultadoBusqueda.setValue("Por favor, ingrese un código para buscar.");
            return;
        }

        List<Producto> listaActual = MainActivity.listaProductos;
        Producto productoEncontrado = null;

        for (Producto p : listaActual) {
            if (p.getCodigo() != null && p.getCodigo().equalsIgnoreCase(codigo.trim())) {
                productoEncontrado = p;
                break;
            }
        }

        if (productoEncontrado != null) {
            String infoProducto = String.format(Locale.getDefault(),
                    "Producto Encontrado:\nCódigo: %s\nDescripción: %s\nPrecio: $%.2f",
                    productoEncontrado.getCodigo(),
                    productoEncontrado.getDescripcion(),
                    productoEncontrado.getPrecio());
            resultadoBusqueda.setValue(infoProducto);
        } else {
            resultadoBusqueda.setValue("Producto no encontrado con el código: " + codigo);
        }
    }
}