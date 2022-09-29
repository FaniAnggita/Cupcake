// TODO 1: Membuat kelas ViewModel untuk menyimpan dan mengelola data terkait UI dengan cara yang berbasis lifecycle.
package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// Konstantan untuk harga per cupcake
private const val PRICE_PER_CUPCAKE = 2.00

// Konstanta biaya tambahan untuk pengambilan pesanan di hari yang sama */
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

// TODO 2: Menyiapkan data berupada informasi cupcake.
class OrderViewModel : ViewModel() {

    // Kita menggunakan LiveData dan serta memanggil kolom tertentu supaya properti dapat berinteraksi dengan UI  serta memperbarui data dari Model.
    // Untuk menghitung jumlah kue.
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    //untuk menyimpan data rasa cupcake yang dipesan.
    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    // Menyimpan data tanggal pesan
    val dateOptions: List<String> = getPickupOptions()

    // Menyimpan data tanggal pengambilan
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    // Menyimpan data total pesanan
    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        // Format the price into the local currency and return this as LiveData<String>
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        // Mengatur ulang nilai awal pesanan
        resetOrder()
    }


    // Fungsi ini untuk set jumlah cupcake yang dipesan.
    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
        updatePrice()
    }


    //  Fungsi ini untuk set nilai rasa cupcake yang dipesan user (hanya satu rasa yang dapat dipesan).
    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    // Fungsi ini untuk set tanggal pengambilan pesananan.
    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        updatePrice()
    }


    // Jika user tidak memilih rasa cupcake, maka fungsi ii akan mengambalikan nilai Null.
    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }


    // Set nilai variabel dengan nilai awal (0 atau Null).
    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

    // Fungsi ini untuk update data harga
    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        // Jika user mengambil pesanan hari ini, maka akan dikenakan biaya tambahan
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

    //   Fungsi ini akan mengambalikan tanggal saat ini dan 3 tanggal berikutnya.
    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }
}