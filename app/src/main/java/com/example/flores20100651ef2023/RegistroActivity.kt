package com.example.flores20100651ef2023

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.flores20100651ef2023.Models.UserModels
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistroActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etFullName: EditText
    private lateinit var etDireccion: EditText
    private lateinit var etDNI: EditText
    private lateinit var etFechaNac: EditText
    private lateinit var etConfirmarPass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etFullName = findViewById(R.id.etFullName)
        etDireccion = findViewById(R.id.etDireccion)
        etDNI = findViewById(R.id.etDNI)
        etFechaNac = findViewById(R.id.etFechaNac)
        etConfirmarPass = findViewById(R.id.etConfirmarPass)

        val btnRegister: Button = findViewById(R.id.btnRegister)
        var auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("users")


        btnRegister.setOnClickListener {
            val correo = etEmail.text.toString()
            val password = etPassword.text.toString()
            val fullname = etFullName.text.toString()
            val direccion = etDireccion.text.toString()
            val dni = etDNI.text.toString()
            val fechaNac = etFechaNac.text.toString()
            val confirpass = etConfirmarPass.text.toString()

            if (password != confirpass){
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Contraseña diferente",
                    Snackbar.LENGTH_LONG
                ).show()
            }else{

                auth.createUserWithEmailAndPassword(correo,password).addOnCompleteListener(this){task ->

                    if (task.isSuccessful){
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            "Registro Exitoso",
                            Snackbar.LENGTH_LONG
                        ).show()

                        if(correo.isNotEmpty() && password.isNotEmpty() && fullname.isNotEmpty() && direccion.isNotEmpty() && dni.isNotEmpty() && fechaNac.isNotEmpty() && confirpass.isNotEmpty()){

                            val nuevoRegistro = UserModels(
                                correo, password,fullname,direccion,dni,fechaNac
                            )
                            collectionRef.add(nuevoRegistro).addOnSuccessListener {

                                Log.d("FirestoreNuevo", "Se agrega collection")
                                Snackbar.make(findViewById(android.R.id.content),"Se agrega collection", Snackbar.LENGTH_LONG).show()

                            }.addOnFailureListener {
                                Log.d("FirestoreNuevo", "No agrega collection")
                                Snackbar.make(findViewById(android.R.id.content),"No agrega collection",
                                    Snackbar.LENGTH_LONG).show()
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
                startActivity(Intent(this, Loginactivity::class.java))
            }


        }

    }
}