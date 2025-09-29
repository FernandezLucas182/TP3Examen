// En SalirViewModel.java
package com.example.tp3.ui.salir;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SalirViewModel extends ViewModel {


    private final MutableLiveData<Boolean> _mostrarDialogoConfirmacion = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _cerrarAplicacion = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _navegarAtras = new MutableLiveData<>();


    public LiveData<Boolean> mostrarDialogoConfirmacion() {
        return _mostrarDialogoConfirmacion;
    }

    public LiveData<Boolean> cerrarAplicacion() {
        return _cerrarAplicacion;
    }

    public LiveData<Boolean> navegarAtras() {
        return _navegarAtras;
    }


    public void iniciarProcesoDeSalida() {
        _mostrarDialogoConfirmacion.setValue(true);
    }

    public void usuarioConfirmaSalir() {
        _cerrarAplicacion.setValue(true);
    }

    public void usuarioCancelaSalir() {
        _navegarAtras.setValue(true);

    }


    public void dialogoDeSalidaManejado() {
        _mostrarDialogoConfirmacion.setValue(null);
    }

    public void cerrarAppManejado() {
        _cerrarAplicacion.setValue(null);
    }

    public void navegarAtrasManejado() {
        _navegarAtras.setValue(null);
    }
}



