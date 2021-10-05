package co.edu.unab.tiendaappfc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetalleActivity extends AppCompatActivity {

    private static final int CODIGO_EDITAR_PRODUCTO = 100;
    private TextView txNombreProducto;
    private ImageView ivProducto;
    private TextView txPrecio;
    private TextView txDescripcion;
    private Button btnEditarProducto;
    private Button btnEliminarProducto;
    private Producto miProducto;

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

            Intent miNuevaInt = new Intent(DetalleActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //Limpia todas las actividades anteriores
            startActivity(miNuevaInt);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        txNombreProducto = findViewById(R.id.tx_nombre_prod);
        ivProducto = findViewById(R.id.im_imagen_detalle);
        txPrecio = findViewById(R.id.tx_precio_detalle);
        txDescripcion = findViewById(R.id.tx_descripcion_detalle);
        btnEditarProducto = findViewById(R.id.bt_editar_producto);
        btnEliminarProducto = findViewById(R.id.bt_eliminar_producto);

        miProducto = (Producto) getIntent().getSerializableExtra("producto");
        cargarDatosProducto();

        btnEditarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetalleActivity.this,FormularioActivity.class);
                i.putExtra("producto", miProducto);
                startActivityForResult(i,CODIGO_EDITAR_PRODUCTO);
            }
        });

        btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iData = new Intent();
                iData.putExtra("producto",miProducto);
                setResult(RESULT_OK,iData);
                finish();
            }
        });
    }

    private void cargarDatosProducto() {
        txNombreProducto.setText(getString(R.string.txt_nombre_producto, miProducto.getNombre()));
        txPrecio.setText(String.valueOf(miProducto.getPrecio()));
        txDescripcion.setText(miProducto.getDescripcion());
        Glide.with(DetalleActivity.this).load(miProducto.getUrlImagen()).into(ivProducto);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==CODIGO_EDITAR_PRODUCTO && resultCode==RESULT_OK){
            if (data != null){
                miProducto = (Producto) data.getSerializableExtra("producto");
                cargarDatosProducto();
            }
        }
    }

    public void onBackPressed(){
        Intent iData = new Intent();
        iData.putExtra("producto",miProducto);
        iData.putExtra("editar",true);
        setResult(RESULT_OK,iData);
        super.onBackPressed();
    }
}