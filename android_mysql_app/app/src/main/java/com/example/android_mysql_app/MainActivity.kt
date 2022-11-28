package com.example.android_mysql_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    private lateinit var etNombre: EditText
    private lateinit var etEmail: EditText
    private lateinit var etTelefeno:EditText
    private lateinit var etPass:EditText
    private lateinit var etId:EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.et_nombre)
        etEmail = findViewById(R.id.et_email)
        etTelefeno = findViewById(R.id.et_telefono)
        etPass = findViewById(R.id.et_pass)
        etId = findViewById(R.id.et_id)

    }

    fun guardar(Vista: View){
        val url = "http://192.168.43.157/android_mysql_app/insertar.php"
        val procesoEnCola: RequestQueue = Volley.newRequestQueue(this)
        var resultdoPost = object : StringRequest(Request.Method.POST,url,
                Response.Listener<String> { respuesta->
                    Toast.makeText(this,"Usuario ingresado",Toast.LENGTH_LONG).show()
                    etNombre.setText("")
                    etEmail.setText("")
                    etTelefeno.setText("")
                    etPass.setText("")

                }, Response.ErrorListener { error->
                Toast.makeText(this,"Error $error",Toast.LENGTH_LONG).show()
            }
            ){
            override fun getParams(): MutableMap<String, String>? {
                val parametos = HashMap<String,String>()

                parametos.put("nombre",etNombre.text.toString())
                parametos.put("email",etEmail.text.toString())
                parametos.put("telefono",etTelefeno.text.toString())
                parametos.put("pass",etPass.text.toString())
                return parametos

            }
        }
        procesoEnCola.add(resultdoPost)
    }

    fun enviarId(Vista:View){

        var ventana:Intent = Intent(this,RegistroActivity::class.java)
        ventana.putExtra("id",etId.text.toString())
        startActivity(ventana)


    }
}