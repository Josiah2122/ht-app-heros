package com.hellotractor.app.features.farms

import com.hellotractor.notes.domain.models.Note

class FarmsContract {
    // State
    data class State(
        val isLoading: Boolean = false,
        val notes: List<Note> = emptyList(),
        val selectedOrder: OrderType = OrderType.DATE,
        val errorMessage: String? = null
    )

    // Event (User actions)
    sealed class Event {
        object LoadNotes : Event()
        data class ChangeOrder(val orderType: OrderType) : Event()
    }

    // SideEffect (One-time actions)
    sealed class SideEffect {
        data class ShowError(val message: String) : SideEffect()
    }

    // OrderType enum
    enum class OrderType {
        DATE, CATEGORY
    }
}