package com.example.tp3.ui.cargar;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import static com.example.tp3.MainActivity.*;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp3.MainActivity;
import com.example.tp3.databinding.FragmentCargarBinding;
import com.example.tp3.model.Producto;



public class CargarViewModel extends AndroidViewModel {

    private FragmentCargarBinding binding;
    private MutableLiveData<String> mMsj;

    public CargarViewModel(@NonNull Application application) {
        super(application);
    }
    //private ArrayList<Producto> productos = MainActivity.listaProductos;

    public LiveData<String> getMMensaje(){
        if(mMsj == null) {
            mMsj = new MutableLiveData<>();
        }
        return mMsj;
    }

    public void cargarProducto(String codigo, String descripcion, String precio){

        boolean valido = validar(codigo, descripcion, precio);

        if (valido){
            MainActivity.listaProductos.add(new Producto(codigo, descripcion, Double.parseDouble(precio)));
            mMsj.setValue("Producto creado con éxito");
        }
    }
    private boolean validar(String codigo, String descripcion, String precio){
        boolean duplicado = false;
        boolean valido = true;
        StringBuilder mensaje = new StringBuilder();

        try{
            double p = Double.parseDouble(precio);

            duplicado = listaProductos.contains(new Producto(codigo, descripcion, p));
        }catch (NumberFormatException e) {
            mMsj.setValue(("El precio debe contener un numero"));
            valido = false;
        }

        if(duplicado){
              mensaje.append("Ya existe un producto con el mismo código \n");
              valido = false;
              }
              if (codigo.isBlank()){
              mensaje.append("el producto debe tener una descripcion");
              valido = false;
              }
              if(codigo.isBlank()){
                  mensaje.append("el producto debe tener un código");
                  valido = false;
              }
              if (precio.isBlank()){
                  mensaje.append("el producto debe tener un precio");
                  valido = false;
              }
              mMsj.setValue(mensaje.toString());
              return  valido;

    }
}


