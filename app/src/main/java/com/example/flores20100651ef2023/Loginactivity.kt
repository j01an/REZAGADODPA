package com.example.flores20100651ef2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.flores20100651ef2023.Models.UserModels
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class Loginactivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)

        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        val btnLogin: Button=findViewById(R.id.btnLogin)
        var auth = FirebaseAuth.getInstance()
        val db= FirebaseFirestore.getInstance()
        val collectionRef=db.collection("users")


        btnLogin.setOnClickListener {
            val correo= etEmail.text.toString()
            val password = etPassword.text.toString()

            auth.signInWithEmailAndPassword(correo,password).addOnCompleteListener(this){task ->
                if ( task.isSuccessful){
                    Snackbar.make(findViewById(android.R.id.content),"Ingreso Exitoso", Snackbar.LENGTH_LONG).show()
                    startActivity(Intent(this, productoActivity::class.java))
                }else{
                    auth.createUserWithEmailAndPassword(correo,password).addOnCompleteListener(this){task ->
                        if (task.isSuccessful){
                            Snackbar.make(
                                findViewById(android.R.id.content),
                                "Registro Exitoso",
                                Snackbar.LENGTH_LONG
                            ).show()
                            if(correo.isNotEmpty() && password.isNotEmpty()){

                                val nuevoRegistro = UserModels(
                                    correo, password
                                )
                                collectionRef.add(nuevoRegistro).addOnSuccessListener {

                                    Log.d("FirestoreNuevo", "Se agrega collection")
                                    Snackbar.make(findViewById(android.R.id.content),"Se agrega collection", Snackbar.LENGTH_LONG).show()

                                }.addOnFailureListener {
                                    Log.d("FirestoreNuevo", "No agrega collection")
                                    Snackbar.make(findViewById(android.R.id.content),"No agrega collection",Snackbar.LENGTH_LONG).show()
                                }

                            }else{
                                Snackbar.make(findViewById(android.R.id.content),"Por favor completa los campos",
                                    Snackbar.LENGTH_LONG).show()
                            }

                        }else{
                            Snackbar.make(findViewById(android.R.id.content),"Ocurrio un error al registrarse",
                                Snackbar.LENGTH_LONG).show()

                        }
                    }

                }

            }
            limpiarDatosDelFormulario()



        }






    }

    private fun limpiarDatosDelFormulario() {

        etEmail.text.clear()
        etPassword.text.clear()
    }
}