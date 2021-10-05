package co.edu.unab.tiendaappfc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txTitulo;
    private EditText etCorreo;
    private Button btIniciar;
    private EditText etContra;
    private Button btRegistrar;
    private SharedPreferences preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txTitulo = findViewById(R.id.tx_titulo_inicio);
        etCorreo = findViewById(R.id.tx_correo);
        btIniciar = findViewById(R.id.bt_iniciar_sesion);
        etContra = findViewById(R.id.tx_contraseña);
        btRegistrar = findViewById(R.id.bt_registrar);

        preferencias = getSharedPreferences(getString(R.string.txt_nombre_preferencia), MODE_PRIVATE);

        boolean loguear = preferencias.getBoolean(getString(R.string.txt_preferencia_login),false);
        if (loguear){
            Intent miIntencion = new Intent(MainActivity.this,ListadoActivity.class);
            startActivity(miIntencion);
            finish();
        }

        btIniciar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                String correo = etCorreo.getText().toString();
                String contra = etContra.getText().toString();
                if(correo.equals("fcordero@unab.edu.co") && contra.equals("12345")){
                    Toast.makeText(MainActivity.this, "Bienvenido "+correo, Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editablePref = preferencias.edit();
                    editablePref.putBoolean(getString(R.string.txt_preferencia_login), true);
                    editablePref.putString("email",correo);
                    editablePref.apply();

                    Intent miIntencion = new Intent(MainActivity.this, ListadoActivity.class);
                    startActivity(miIntencion);

                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Datos incorrectos", Toast.LENGTH_LONG).show();
                }
            }
        });

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent miIntencion = new Intent(MainActivity.this, FormularioUserActivity.class);
                startActivity(miIntencion);
            }
        });

        Log.d("CicloVida", "Ejecutando OnCreate...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("CicloVida", "Ejecutando OnStart...");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("CicloVida", "Ejecutando OnResume...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("CicloVida", "Ejecutando OnPause...");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("CicloVida", "Ejecutando OnRestart...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("CicloVida", "Ejecutando OnStop...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("CicloVida", "Ejecutando OnDestroy...");
    }
}