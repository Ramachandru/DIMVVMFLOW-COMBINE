package com.example.dimvvmflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dimvvmflow.extensions.combineStates
import com.example.dimvvmflow.model.ProcessedData
import com.example.dimvvmflow.model.User
import com.example.dimvvmflow.network.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val apiInterface: ApiInterface
) : ViewModel() {
    var userListSTate : StateFlow<ProcessedData> =MutableStateFlow<ProcessedData>(ProcessedData.Success(emptyList()))
    init {
         val userList: StateFlow<List<User>> =apiInterface.getUser()
             .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
        val moreuserList: StateFlow<List<User>> =apiInterface.getMoreUser()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), mutableListOf())
        var userAllList =viewModelScope.combineStates(flows=arrayOf(userList,moreuserList)) {
            val Alllist= mutableListOf<List<User>>()
            Alllist.add(it[0])
            Alllist.add(it[1])
           return@combineStates Alllist
        }.map {
           // userListSTate.value=ProcessedData.Success(it)
        }
    }
}