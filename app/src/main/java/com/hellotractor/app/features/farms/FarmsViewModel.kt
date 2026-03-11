package com.hellotractor.app.features.farms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellotractor.notes.domain.models.Note
import com.hellotractor.notes.domain.usecases.GetNotesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class FarmsViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesListUseCase
) : ViewModel() {

    // State
    private val _state = MutableStateFlow(FarmsContract.State())
    val state: StateFlow<FarmsContract.State> = _state.asStateFlow()

    // SideEffects
    private val _sideEffect = MutableSharedFlow<FarmsContract.SideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    // Date formatter for parsing the string dates
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    init {
        loadNotes()
    }

    fun onEvent(event: FarmsContract.Event) {
        when (event) {
            is FarmsContract.Event.LoadNotes -> loadNotes()
            is FarmsContract.Event.ChangeOrder -> changeOrder(event.orderType)
        }
    }

    private fun loadNotes() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                val notes = getNotesUseCase.run() // Assuming this returns List<Note>
                // Apply sorting based on current order
                val sortedNotes = sortNotes(notes, _state.value.selectedOrder)
                _state.update {
                    it.copy(
                        isLoading = false,
                        notes = sortedNotes,
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Failed to load notes: ${e.message}"
                    )
                }
                _sideEffect.emit(FarmsContract.SideEffect.ShowError("Failed to load notes"))
            }
        }
    }

    private fun changeOrder(orderType: FarmsContract.OrderType) {
        _state.update { it.copy(selectedOrder = orderType) }
        // Re-sort the current notes list
        val currentNotes = _state.value.notes
        val sortedNotes = sortNotes(currentNotes, orderType)
        _state.update { it.copy(notes = sortedNotes) }
    }

    private fun sortNotes(notes: List<Note>, orderType: FarmsContract.OrderType): List<Note> {
        return when (orderType) {
            FarmsContract.OrderType.DATE -> {
                // Sort by dateCreated (most recent first)
                // Since dateCreated is a String, we need to parse it for comparison
                notes.sortedByDescending { note ->
                    try {
                        dateFormat.parse(note.dateCreated)?.time ?: 0L
                    } catch (e: Exception) {
                        0L // If date parsing fails, put it at the end
                    }
                }
            }
            FarmsContract.OrderType.CATEGORY -> {
                // Sort by the note type (category)
                notes.sortedBy { it.type.name }
            }
        }
    }
}