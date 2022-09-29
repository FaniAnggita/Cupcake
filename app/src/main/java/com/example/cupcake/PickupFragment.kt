/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentPickupBinding
import com.example.cupcake.model.OrderViewModel

/**
 * TODO 7:  Kelas untuk memilih tanggal pengambilan untuk pesanan cupcake.
 */

class PickupFragment : Fragment() {

    // Melakukan binding ke layout fragment_pickup.xml
    private var binding: FragmentPickupBinding? = null

    // Melakukan  referensi ke model tampilan bersama sebagai variabel class dengan pemberian properti activityViewModels() dari artefak fragmen-ktx
    private val sharedViewModel: OrderViewModel by activityViewModels()

    /**
     * Sistem akan memanggilnya saat fragmen menggambar antarmuka penggunanya untuk yang pertama kali.
     * Untuk menggambar UI fragmen, kita harus mengembalikan View dari metode ini yang menjadi root layout fragmen.
     * Hasil yang dikembalikan bisa berupa null jika fragmen tidak menyediakan UI.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPickupBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    /**
     * Memberikan subkelas  untuk menginisialisasi diri mereka sendiri setelah mhierarki tampilantelah dibuat.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            pickupFragment = this@PickupFragment
        }
    }

    //  TODO 8: avigasi ke halaman SummaryFragment.
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
    }

    //    Fungsi ini untuk menghapus objek fragment yang terikat.
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}