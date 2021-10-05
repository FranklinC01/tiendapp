package co.edu.unab.tiendaappfc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private static final int CODIGO_AGREGAR_PRODUCTO = 100;
    private static final int CODIGO_DETALLE_PRODUCTO = 110;
    private ArrayList<Producto> listaProductos;
    private RecyclerView rvProductos;
    private Button btnListado;
    private ProductoAdapter miAdaptador;
    //private ListView lvProductos;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.mi_cerrar_sesion) {
            SharedPreferences preferencias = getSharedPreferences(getString(R.string.txt_nombre_preferencia), MODE_PRIVATE);
            SharedPreferences.Editor editable = preferencias.edit();
            editable.clear();
            editable.apply();

            Intent miNuevaInt = new Intent(ListadoActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //Limpia todas las actividades anteriores
            startActivity(miNuevaInt);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        String email = getSharedPreferences(getString(R.string.txt_nombre_preferencia), MODE_PRIVATE).getString("email","");
        setTitle(email);

        rvProductos = findViewById(R.id.rv_productos_lista);
        btnListado = findViewById(R.id.btn_listado);

        btnListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent miIntencion = new Intent(ListadoActivity.this,FormularioActivity.class);
                startActivityForResult(miIntencion, CODIGO_AGREGAR_PRODUCTO);
            }
        });

        listaProductos = new ArrayList<>();
        cargarProductos();

        miAdaptador = new ProductoAdapter(listaProductos);
        rvProductos.setAdapter(miAdaptador);
        rvProductos.setLayoutManager(new LinearLayoutManager(ListadoActivity.this));
        rvProductos.setHasFixedSize(true);

        miAdaptador.setOnItemClickListener(new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto) {
                Intent miIntencion = new Intent(ListadoActivity.this, DetalleActivity.class);
                miIntencion.putExtra("producto",miProducto);
                startActivityForResult(miIntencion,CODIGO_DETALLE_PRODUCTO);
            }
        });

        /*ArrayAdapter<Producto> adaptador = new ArrayAdapter<>(ListadoActivity.this, android.R.layout.simple_list_item_1, listaProductos);
        lvProductos.setAdapter(adaptador);

        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Producto miProducto = listaProductos.get(i);
                Toast.makeText(ListadoActivity.this, "Hice click en "+ miProducto.getNombre(), Toast.LENGTH_SHORT).show();

            }
        });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CODIGO_AGREGAR_PRODUCTO && resultCode==RESULT_OK){
            if (data != null){
                Producto miProducto = (Producto) data.getSerializableExtra("producto");
                int id = listaProductos.get(listaProductos.size()-1).getId()+1;
                miProducto.setId(id);
                listaProductos.add(miProducto);
                miAdaptador.setListadoP(listaProductos);
                Toast.makeText(ListadoActivity.this, miProducto.toString(), Toast.LENGTH_SHORT).show();
            }

        }

        if (requestCode==CODIGO_DETALLE_PRODUCTO && resultCode==RESULT_OK){
            if (data != null){
                Producto miProducto = (Producto) data.getSerializableExtra("producto");
                Boolean editar = data.getBooleanExtra("editar",false);
                for (Producto elemento:listaProductos){
                    if (elemento.getId()==miProducto.getId()){
                        int posicion  = listaProductos.indexOf(elemento);

                        if (editar){
                            listaProductos.set(posicion,miProducto);
                        }else{
                            listaProductos.remove(elemento);
                            if(listaProductos.isEmpty()){
                                cargarProductos();
                            }
                            Toast.makeText(ListadoActivity.this, "Producto eliminado", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
                miAdaptador.setListadoP(listaProductos);
            }
        }
    }


    private void cargarProductos(){
        Producto p1 = new Producto("USB",15000,"https://exitocol.vtexassets.com/arquivos/ids/323789/Memoria-Usb-31-30-20-Kingston-64-Gb-Dt100G3.jpg?v=637004756640530000        ");
        p1.setId(1);
        p1.setDescripcion("USB 64GB FULLHD");
        Producto p2 = new Producto("Disco duro",80000,"https://www.administracionderedes.com/wp-content/uploads/2017/12/disco-duro-725.jpg");
        p2.setId(2);
        Producto p3 = new Producto("Teclado",18000,"https://bitwares.net/wp-content/uploads/2020/12/pks-201sin_35.png");
        p3.setId(3);
        Producto p4 = new Producto("Pantalla",100000,"https://sc04.alicdn.com/kf/HTB1BS6nX0fvK1RjSspfq6zzXFXa0.jpg");
        p4.setId(4);
        Producto p5 = new Producto("Procesador",500000,"https://www.muycomputer.com/wp-content/uploads/2016/03/Intel-1.jpg");
        p5.setId(5);
        listaProductos.add(p1);
        listaProductos.add(p2);
        listaProductos.add(p3);
        listaProductos.add(p4);
        listaProductos.add(p5);
    }
}