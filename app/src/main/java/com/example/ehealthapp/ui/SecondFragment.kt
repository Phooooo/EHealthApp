package com.example.ehealthapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ehealthapp.R
import com.example.ehealthapp.application.HealthApp
import com.example.ehealthapp.databinding.FragmentSecondBinding
import com.example.ehealthapp.model.Health

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val healthViewModel: HealthViewModel by viewModels {
        HealthViewModelFactory((applicationContext as HealthApp).repository)
    }
    private val args : SecondFragmentArgs by navArgs()
    private var health: Health? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        health = args.health
        if (health != null){
            binding.deleteButton.visibility = View.VISIBLE
            binding.saveButton.text = "Ubah"
        }
        val name = binding.nameEditText.text
        val address = binding.addresseditText.text
        val phonenumber = binding.phonenumberEditText.text
        binding.saveButton.setOnClickListener {
        //update data tidak boleh kosong
            if (name.isEmpty()) {
                Toast.makeText(context,"Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            } else if (address.isEmpty()) {
                Toast.makeText(context,"Alamat Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            } else if (phonenumber.isEmpty()){
                Toast.makeText(context,"Nomor Telepon Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            } else {
                if (health == null) {
                    val health = Health(0,name.toString(), address.toString(), phonenumber.toString())
                    healthViewModel.insert(health)
                } else {
                    val health = Health(health?.id!!, name.toString(), address.toString(), phonenumber.toString())
                    healthViewModel.update(health)
                }
            findNavController().popBackStack()// untuk dismiss halaman ini
        }
        }
        binding.deleteButton.setOnClickListener{
            health?.let { healthViewModel.delete(it) }
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
