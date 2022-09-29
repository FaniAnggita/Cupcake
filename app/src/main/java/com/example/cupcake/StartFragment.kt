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
import com.example.cupcake.databinding.FragmentStartBinding
import com.example.cupcake.model.OrderViewModel

// TODO 3: Kelas untuk mengatur halaman di mana user dapat mulai pesan cupcake
class StartFragment : Fragment() {

    // Melakukan binding ke layout fragment_start.xml
    private var binding: FragmentStartBinding? = null

    // Melakukan  referensi ke model tampilan bersama sebagai variabel class dengan pemberian properti activityViewModels() dari artefak fragmen-ktx
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this
    }


    //    Fungsi ini untuk memilih jumlah Kue
    fun orderCupcake(quantity: Int) {
        // Memperbarui tampilan dengan argumen 'quantity'
        sharedViewModel.setQuantity(quantity)

        // Jika user tidak memilih rasa cupcake, maka akan ditetapkan rasa "Vanilla"
        if (sharedViewModel.hasNoFlavorSet()) {
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }

        // TODO 4: Navigasi ke kelas FlavorFragment
        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}