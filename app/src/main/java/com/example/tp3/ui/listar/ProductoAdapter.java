package com.example.tp3.ui.listar; // O el paquete que corresponda

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tp3.R;
import com.example.tp3.model.Producto;
import java.util.List;
import java.util.Locale; // Para formatear el precio

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Producto> listaProductos;


    public ProductoAdapter(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);
        holder.tvCodigo.setText(String.format(Locale.getDefault(),"Código: %s", producto.getCodigo()));
        holder.tvDescripcion.setText(producto.getDescripcion());
        holder.tvPrecio.setText(String.format(Locale.getDefault(),"Precio: $%.2f", producto.getPrecio()));
    }

    @Override
    public int getItemCount() {
        return listaProductos != null ? listaProductos.size() : 0;
    }

    // Método para actualizar la lista de productos en el adaptador
    public void actualizarProductos(List<Producto> nuevosProductos) {
        this.listaProductos = nuevosProductos;
        notifyDataSetChanged(); // Notifica al RecyclerView que los datos han cambiado
    }


    static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigo;
        TextView tvDescripcion;
        TextView tvPrecio;

        ProductoViewHolder(View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigoProducto);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionProducto);
            tvPrecio = itemView.findViewById(R.id.tvPrecioProducto);
        }
    }
}
