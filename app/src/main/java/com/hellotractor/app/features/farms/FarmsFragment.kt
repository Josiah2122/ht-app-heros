package com.hellotractor.app.features.farms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hellotractor.android.notes.databinding.FragmentFarmsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FarmsFragment : Fragment() {

    private var _binding: FragmentFarmsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FarmsViewModel by viewModels()

    private lateinit var adapter: FarmsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFarmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSpinner()
        observeState()
        observeSideEffects()
    }

    private fun setupRecyclerView() {
        adapter = FarmsAdapter()
        binding.recyclerFarms.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerFarms.adapter = adapter
    }

    private fun setupSpinner() {
        // Create an ArrayAdapter for the spinner options
        val options = listOf("Order by Date", "Order by Category")
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSort.adapter = spinnerAdapter

        // Set default selection (Date = index 0)
        binding.spinnerSort.setSelection(0)

        // Handle item selection
        binding.spinnerSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val orderType = when (position) {
                    0 -> FarmsContract.OrderType.DATE
                    1 -> FarmsContract.OrderType.CATEGORY
                    else -> FarmsContract.OrderType.DATE
                }
                viewModel.onEvent(FarmsContract.Event.ChangeOrder(orderType))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    renderState(state)
                }
            }
        }
    }

    private fun renderState(state: FarmsContract.State) {
        // Show/hide views based on state
        binding.progressLoading.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        binding.recyclerFarms.visibility = if (!state.isLoading && state.notes.isNotEmpty()) View.VISIBLE else View.GONE
        binding.textEmpty.visibility = if (!state.isLoading && state.notes.isEmpty() && state.errorMessage == null) View.VISIBLE else View.GONE
        binding.textError.visibility = if (!state.isLoading && state.errorMessage != null) View.VISIBLE else View.GONE

        // Update error text if needed
        state.errorMessage?.let { binding.textError.text = it }

        // Submit list to adapter
        adapter.submitList(state.notes)
    }

    private fun observeSideEffects() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sideEffect.collect { effect ->
                    when (effect) {
                        is FarmsContract.SideEffect.ShowError -> {
                            // Show a toast or snackbar
                            android.widget.Toast.makeText(requireContext(), effect.message, android.widget.Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
