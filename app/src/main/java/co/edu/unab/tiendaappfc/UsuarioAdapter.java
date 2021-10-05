package co.edu.unab.tiendaappfc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UsuarioAdapter extends RecyclerView.Adapter {

    private ArrayList<Usuario> listadoU;

    public UsuarioAdapter(ArrayList<Usuario> listadoU) {
        this.listadoU = listadoU;
    }


    public void setListadoU(ArrayList<Usuario> listadoU) {
        this.listadoU = listadoU;
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder{

        ImageView ivUsuario;
        TextView tvNombreUsuario;
        TextView tvCorreoUsuario;

        public UsuarioViewHolder(@NonNull final View itemView){
            super(itemView);
            ivUsuario = itemView.findViewById(R.id.iv_usuario_item);
            tvNombreUsuario = itemView.findViewById(R.id.tv_usuario_nombreIt);
            tvCorreoUsuario = itemView.findViewById(R.id.tv_correo_usuarioIt);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario,parent,false);

        return new UsuarioViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final Usuario miUsuario = listadoU.get(position);
        final UsuarioViewHolder miHolder = (UsuarioViewHolder) holder;

        miHolder.tvNombreUsuario.setText(miUsuario.getNombre());
        miHolder.tvCorreoUsuario.setText(miUsuario.getCorreo());
        Glide.with(miHolder.itemView.getContext()).load(miUsuario.getUrlFoto()).into(miHolder.ivUsuario);
    }

    @Override
    public int getItemCount() {
        return listadoU.size();
    }

}
