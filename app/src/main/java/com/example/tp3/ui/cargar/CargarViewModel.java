package com.example.tp3.ui.cargar;


import android.app.Application;
import static com.example.tp3.MainActivity.*;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp3.MainActivity;
// import com.example.tp3.databinding.FragmentCargarBinding;
import com.example.tp3.model.Producto;

public class CargarViewModel extends AndroidViewModel {


    private MutableLiveData<String> mMsj;

    public CargarViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<String> getMMensaje(){
        if(mMsj == null) {
            mMsj = new MutableLiveData<>();
        }
        return mMsj;
    }

    public void cargarProducto(String codigo, String descripcion, String precio){
        boolean valido = validar(codigo, descripcion, precio);

        if (valido){

            MainActivity.listaProductos.add(new Producto(codigo.trim(), descripcion.trim(), Double.parseDouble(precio.trim())));
            mMsj.setValue("Producto creado con éxito");
        }
        // Si no es válido, el método validar() ya habrá llamado a mMsj.setValue() con el error.
    }

    private boolean validar(String codigo, String descripcion, String precioStr){
        boolean duplicado = false;
        boolean valido = true;
        StringBuilder mensajeBuilder = new StringBuilder();


        if (codigo == null || codigo.trim().isEmpty()) {
            mensajeBuilder.append("El código no puede estar vacío.\n");
            valido = false;
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            mensajeBuilder.append("La descripción no puede estar vacía.\n");
            valido = false;
        }
        if (precioStr == null || precioStr.trim().isEmpty()) {
            mensajeBuilder.append("El precio no puede estar vacío.\n");
            valido = false;
        }


        if (!valido) {
            mMsj.setValue(mensajeBuilder.toString().trim());
            return false;
        }

        try{
            double p = Double.parseDouble(precioStr.trim());


            Producto productoTempParaDuplicado = new Producto(codigo.trim(), "", 0.0);
            duplicado = listaProductos.contains(productoTempParaDuplicado);

        } catch (NumberFormatException e) {

            mMsj.setValue("El precio debe ser un número válido.");
            return false;
        }

        if(duplicado){
            mensajeBuilder.append("Ya existe un producto con el mismo código.\n");
            valido = false;
        }


        if (!valido) {
            mMsj.setValue(mensajeBuilder.toString().trim());
        }


        return valido;
    }
}
