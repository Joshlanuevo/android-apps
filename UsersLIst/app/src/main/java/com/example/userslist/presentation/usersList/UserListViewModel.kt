package com.example.userslist.presentation.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userslist.domain.model.User
import com.example.userslist.domain.usecase.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface UserListUiState {
    object Loading : UserListUiState
    data class Success(val users: List<User>) : UserListUiState
    data class Error(val message: String) : UserListUiState
}

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUserData: GetUserDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserListUiState>(UserListUiState.Loading)
    val uiState: StateFlow<UserListUiState> = _uiState.asStateFlow()

    fun fetchUsers(username: String) {
        viewModelScope.launch {
            _uiState.value = UserListUiState.Loading
            try {
                val users = getUserData(username)
                _uiState.value = UserListUiState.Success(users)
            } catch (e: Exception) {
                _uiState.value = UserListUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}