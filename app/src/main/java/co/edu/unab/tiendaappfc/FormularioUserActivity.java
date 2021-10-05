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
import android.widget.Toast;

public class FormularioUserActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etCorreo;
    private EditText etContra;
    private EditText etUrlFoto;
    private Button btRegistrarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_user);

        etNombre = findViewById(R.id.et_nombre_usuario);
        etCorreo = findViewById(R.id.et_correo_usuario);
        etContra = findViewById(R.id.et_contra_usuario);
        etUrlFoto = findViewById(R.id.et_urlFoto_usuario);
        btRegistrarUsuario = findViewById(R.id.btn_registrar_usuario);

        btRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString();
                String correo = etCorreo.getText().toString();
                String contra = etContra.getText().toString();
                String urlFoto = etUrlFoto.getText().toString();

                Usuario miUsuario = new Usuario(nombre,correo,contra,urlFoto);
                Toast.makeText(FormularioUserActivity.this, "Usuario registrado", Toast.LENGTH_LONG).show();

                Intent iDatos = new Intent(FormularioUserActivity.this,ListadoUserActivity.class);
                iDatos.putExtra("usuario", miUsuario);
                setResult(RESULT_OK,iDatos);
                startActivity(iDatos);
                finish();
            }
        });
    }
}