package com.example.tp3.ui.busqueda;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.tp3.MainActivity;
import com.example.tp3.model.Producto;
import com.example.tp3.util.SingleLiveEvent;

import java.util.List;

public class BusquedaViewModel extends AndroidViewModel {


    private final MutableLiveData<Producto> productoEncontrado = new MutableLiveData<>();

    private final MutableLiveData<String> mensajeBusqueda = new MutableLiveData<>();

    private final SingleLiveEvent<Producto> navegarADetalle = new SingleLiveEvent<>();


    public BusquedaViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Producto> getProductoEncontrado() {
        return productoEncontrado;
    }

    public LiveData<String> getMensajeBusqueda() {
        return mensajeBusqueda;
    }

    public LiveData<Producto> getNavegarADetalle() {
        return navegarADetalle;
    }


    public void buscarProductoPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            mensajeBusqueda.setValue("Por favor, ingrese un código para buscar.");
            return;
        }

        List<Producto> listaActual = MainActivity.listaProductos;
        Producto productoResultado = null;

        for (Producto p : listaActual) {
            if (p.getCodigo() != null && p.getCodigo().equalsIgnoreCase(codigo.trim())) {
                productoResultado = p;
                break;
            }
        }

        if (productoResultado != null) {

            navegarADetalle.setValue(productoResultado);
            mensajeBusqueda.setValue(null);
        } else {

            mensajeBusqueda.setValue("Producto no encontrado con el código: " + codigo);
        }
    }
}
