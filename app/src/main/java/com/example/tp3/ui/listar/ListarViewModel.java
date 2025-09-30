package com.example.tp3.ui.listar;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.tp3.MainActivity;
import com.example.tp3.model.Producto;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ListarViewModel extends AndroidViewModel {

    private MutableLiveData<List<Producto>> mListaProductos;
    private MutableLiveData<Boolean> mListaVacia;

    public ListarViewModel(@NonNull Application application) {
        super(application);
        mListaProductos = new MutableLiveData<>();
        mListaVacia = new MutableLiveData<>();
        cargarProductos();
    }

    public LiveData<List<Producto>> getListaProductos() {
        return mListaProductos;
    }

    public LiveData<Boolean> getListaVacia() {
        return mListaVacia;
    }

    public void cargarProductos() {

        List<Producto> productosObtenidos = new ArrayList<>(MainActivity.listaProductos);


        Collections.sort(productosObtenidos, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                String desc1 = (p1 != null && p1.getDescripcion() != null) ? p1.getDescripcion() : "";
                String desc2 = (p2 != null && p2.getDescripcion() != null) ? p2.getDescripcion() : "";
                return desc1.compareToIgnoreCase(desc2);
            }
        });

        mListaProductos.setValue(productosObtenidos);
        mListaVacia.setValue(productosObtenidos.isEmpty());
    }
}
