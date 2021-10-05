package co.edu.unab.tiendaappfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FormularioActivity extends AppCompatActivity {

    private EditText etnombreFormulario;
    private EditText etPrecioFormulario;
    private EditText etDescripcionFormulario;
    private EditText etImagenFormulario;
    private Button btFormularioProducto;
    private TextView txTituloFormulario;

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

            Intent miNuevaInt = new Intent(FormularioActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //Limpia todas las actividades anteriores
            startActivity(miNuevaInt);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        etnombreFormulario = findViewById(R.id.et_nombre_form);
        etPrecioFormulario = findViewById(R.id.et_precio_form);
        etImagenFormulario = findViewById(R.id.et_img_form);
        etDescripcionFormulario = findViewById(R.id.et_desc_form);
        btFormularioProducto = findViewById(R.id.bt_agg_form);
        txTituloFormulario = findViewById(R.id.tx_titulo_form);

        final Producto miProductoEditar = (Producto) getIntent().getSerializableExtra("producto");

        if (miProductoEditar != null) {
            txTituloFormulario.setText(R.string.tx_formulario_editar);
            etnombreFormulario.setText(miProductoEditar.getNombre());
            etPrecioFormulario.setText(String.valueOf(miProductoEditar.getPrecio()));
            etDescripcionFormulario.setText(miProductoEditar.getDescripcion());
            etImagenFormulario.setText(miProductoEditar.getUrlImagen());

            btFormularioProducto.setText(R.string.tx_formulario_editar);

            btFormularioProducto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nombre = etnombreFormulario.getText().toString();
                    double precio = Double.parseDouble(etPrecioFormulario.getText().toString());
                    String imagen = etImagenFormulario.getText().toString();
                    String descripcion = etDescripcionFormulario.getText().toString();

                    miProductoEditar.setNombre(nombre);
                    miProductoEditar.setPrecio(precio);
                    miProductoEditar.setUrlImagen(imagen);
                    miProductoEditar.setDescripcion(descripcion);

                    Intent iDatos = new Intent();
                    iDatos.putExtra("producto", miProductoEditar);
                    setResult(RESULT_OK, iDatos);
                    finish();
                }
            });
        } else {

            btFormularioProducto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nombre = etnombreFormulario.getText().toString();
                    double precio = Double.parseDouble(etPrecioFormulario.getText().toString());
                    String imagen = etImagenFormulario.getText().toString();
                    String descripcion = etDescripcionFormulario.getText().toString();

                    Producto miNuevoProducto = new Producto(nombre, precio, imagen);
                    if (!descripcion.equals("")) {
                        miNuevoProducto.setDescripcion(descripcion);
                    }

                    Intent iDatos = new Intent();
                    iDatos.putExtra("producto", miNuevoProducto);
                    setResult(RESULT_OK, iDatos);
                    finish();
                }
            });
        }
    }
}