package com.example.flores20100651ef2023.Database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class productoRepository (application: Application){
    private val productoDAO: productoDAO?
            = productoDatabase.getInstance(application)?.productoDAO()

    fun getCustomer(): LiveData<List<productEntity>> {
        return productoDAO?.getCustomerOrderByLastName() ?:
        MutableLiveData<List<productEntity>>().apply {
            value = emptyList()
        }
    }
    fun insert(productEntity: productEntity){
        if(productoDAO!=null) {
            InsertAsyncTask(productoDAO).execute(productEntity)
        }
    }
    private class InsertAsyncTask(private val productoDAO: productoDAO)
        : AsyncTask<productEntity, Void, Void>(){
        override fun doInBackground(vararg productos: productEntity?): Void? {
            for(productos in productos){
                if(productos!=null)
                    productoDAO.insert(productos)
            }
            return null
        }
    }

}