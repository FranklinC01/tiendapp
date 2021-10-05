package co.edu.unab.tiendaappfc;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter {

    private ArrayList<Producto> listadoP;
    private OnItemClickListener onItemClickListener;

    public ProductoAdapter(ArrayList<Producto> listadoP) {
        this.listadoP = listadoP;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setListadoP(ArrayList<Producto> listadoP) {
        this.listadoP = listadoP;
        notifyDataSetChanged();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProducto;
        TextView tvNombreProducto;
        TextView tvPrecioProducto;

        public ProductoViewHolder(@NonNull final View itemView) {
            super(itemView);
            ivProducto = itemView.findViewById(R.id.iv_producto_item);
            tvNombreProducto = itemView.findViewById(R.id.tv_nombre_producto_item);
            tvPrecioProducto = itemView.findViewById(R.id.tv_precio_producto_item);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);

        return new ProductoViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Producto miProducto = listadoP.get(position);

        final ProductoViewHolder miHolder = (ProductoViewHolder) holder;

        miHolder.tvNombreProducto.setText(miProducto.getNombre());
        miHolder.tvPrecioProducto.setText(String.valueOf(miProducto.getPrecio()));
        Glide.with(miHolder.itemView.getContext()).load(miProducto.getUrlImagen()).into(miHolder.ivProducto);

        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(miProducto);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listadoP.size();
    }

    public interface OnItemClickListener{
        void onItemClick(Producto miProducto);
    }
}