package me.hirotask.android.cafeapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.hirotask.android.cafeapp.domain.Cafe
import me.hirotask.android.cafeapp.repository.CafeRepository

class CafeViewModel(private val cafeRepository: CafeRepository) : ViewModel() {

    private val _cafeList: MutableStateFlow<List<Cafe>> = MutableStateFlow(listOf())

    val cafeList = _cafeList.asStateFlow()

    init {
        getCafeList()
    }

    fun getCafeList() {
        viewModelScope.launch {
            cafeRepository.getCafeList(
                20,
                onSuccess = { _cafeList.value = it },
                onFailure = {}
            )
        }
    }
}