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
import com.example.cupcake.databinding.FragmentFlavorBinding
import com.example.cupcake.model.OrderViewModel

// TODO 5: Kelas untuk memilih jenis rasa cupcake
class FlavorFragment : Fragment() {

    // Melakukan binding ke layout fragment_flavor.xml
    private var binding: FragmentFlavorBinding? = null

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
        val fragmentBinding = FragmentFlavorBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


    /**
     * Memberikan subkelas  untuk menginisialisasi diri mereka sendiri setelah mhierarki tampilantelah dibuat.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            // Set fragment sebagai livecycle
            lifecycleOwner = viewLifecycleOwner

            // Menetapkan model tampilan ke properti di kelas pengikatan
            viewModel = sharedViewModel

            // menetapkan fragment pada FlavorFragament
            flavorFragment = this@FlavorFragment
        }
    }

    //  TODO 6: Navigasi ke kelas PickupFragment
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
    }


    //    Fungsi ini untuk menghapus objek fragment yang terikat.
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}