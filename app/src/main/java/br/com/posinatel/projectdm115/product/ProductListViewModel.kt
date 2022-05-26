package br.com.posinatel.projectdm115.product

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.posinatel.projectdm115.network.SalesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


private const val TAG = "ProductListViewModel"

class ProductListViewModel : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getProducts()
    }

    private fun getProducts() {
        Log.i(TAG, "Preparing to request products list")
        coroutineScope.launch {
            val getProductsDeferred = SalesApi.retrofitService.getProducts()
            Log.i(TAG, "Loading products")
            val productsList = getProductsDeferred.await()
            Log.i(TAG, "Number of products ${productsList.size}")
        }
        Log.i(TAG, "Products list requested")
    }
}
