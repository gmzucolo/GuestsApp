package com.example.gmzucolo.guests.view.present

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gmzucolo.guests.constants.DataBaseConstants
import com.example.gmzucolo.guests.databinding.FragmentPresentBinding
import com.example.gmzucolo.guests.view.adapter.GuestsAdapter
import com.example.gmzucolo.guests.view.all.GuestsViewModel
import com.example.gmzucolo.guests.view.form.GuestFormActivity
import com.example.gmzucolo.guests.view.listener.OnGuestListener

class PresentFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var presentViewModel: GuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        presentViewModel =
            ViewModelProvider(this).get(GuestsViewModel::class.java)

        _binding = FragmentPresentBinding.inflate(inflater, container, false)

        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)
        binding.recyclerAllGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                presentViewModel.delete(id)
                presentViewModel.getPresent()
            }

        }
        adapter.attachListener(listener)

        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presentViewModel.getPresent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        presentViewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}