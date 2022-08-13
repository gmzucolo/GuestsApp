package com.example.gmzucolo.guests.view.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gmzucolo.guests.databinding.FragmentAllGuestsBinding
import com.example.gmzucolo.guests.view.adapter.GuestsAdapter
import com.example.gmzucolo.guests.view.listener.OnGuestListener

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, b: Bundle?
    ): View {
        allGuestsViewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)
        binding.recyclerAllGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(guestId: Int) {
                TODO("Not yet implemented")
            }

            override fun onDelete(guestId: Int) {
                allGuestsViewModel.delete(guestId)
                allGuestsViewModel.getAll()
            }

        }
        adapter.attachListener(listener)

        allGuestsViewModel.getAll()

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        allGuestsViewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}