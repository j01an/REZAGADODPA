package com.example.flores20100651ef2023


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class Loginactivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)

        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)


        val btnLogin: Button =findViewById(R.id.btnLogin)
        val btnRegistrar: Button = findViewById(R.id.btnRegistrar)
        var auth = FirebaseAuth.getInstance()




        btnLogin.setOnClickListener {
            val correo= etEmail.text.toString()
            val password = etPassword.text.toString()


            auth.signInWithEmailAndPassword(correo,password).addOnCompleteListener(this){task ->
                if ( task.isSuccessful){
                    Snackbar.make(findViewById(android.R.id.content),"Ingreso Exitoso", Snackbar.LENGTH_LONG).show()
                    startActivity(Intent(this, productoActivity::class.java))
                }else{
                    Snackbar.make(findViewById(android.R.id.content),"Credenciales invalidas", Snackbar.LENGTH_LONG).show()


                }

            }

        }

        btnRegistrar.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

    }

}