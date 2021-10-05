package co.edu.unab.tiendaappfc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoUserActivity extends AppCompatActivity {

    //public static final int CODIGO_REGISTRAR_USUARIO = 100;
    private ArrayList<Usuario> listaUsuarios;
    private RecyclerView rvUsuarios;
    private UsuarioAdapter miAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_user);

        rvUsuarios = findViewById(R.id.rv_usuarios_lista);

        /*Intent miIntencion = new Intent(ListadoUserActivity.this,FormularioUserActivity.class);
        startActivityForResult(miIntencion,CODIGO_REGISTRAR_USUARIO);*/

        listaUsuarios = new ArrayList<>();
        cargarUsuarios();
        miAdaptador = new UsuarioAdapter(listaUsuarios);
        rvUsuarios.setAdapter(miAdaptador);
        rvUsuarios.setLayoutManager(new GridLayoutManager(ListadoUserActivity.this,2));
        rvUsuarios.setHasFixedSize(true);

        Usuario miUsuario = (Usuario) getIntent().getSerializableExtra("usuario");
        int id = listaUsuarios.get(listaUsuarios.size() - 1).getId() + 1;
        miUsuario.setId(id);
        listaUsuarios.add(miUsuario);
        miAdaptador.setListadoU(listaUsuarios);
        Toast.makeText(ListadoUserActivity.this, miUsuario.toString(), Toast.LENGTH_SHORT).show();

    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_REGISTRAR_USUARIO && resultCode == RESULT_OK) {
            if (data != null) {

            }
        }
    }*/

    private void cargarUsuarios(){
        Usuario u1 = new Usuario("Franklin Cordero", "fcordero@","1234","https://i2.wp.com/horapiko.com/wp-content/uploads/2021/02/image-87.png?fit=750%2C554&ssl=1");
        u1.setId(1);
        Usuario u2 = new Usuario("Maria Ayala","mayala@gmail","4567","https://www.semana.com/resizer/75K0ctreHr4E0oafE7Z_nLCcOBc=/1200x675/filters:format(jpg):quality(50)//cloudfront-us-east-1.images.arcpublishing.com/semana/C2HQ36UAKFHJ7BLFF5NT4PRN7U.png");
        u2.setId(2);
        listaUsuarios.add(u1);
        listaUsuarios.add(u2);
    }
}