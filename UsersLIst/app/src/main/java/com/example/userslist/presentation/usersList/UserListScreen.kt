package com.example.userslist.presentation.userslist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.userslist.presentation.userlist.UserListUiState
import com.example.userslist.presentation.userlist.UserListViewModel

@Composable
fun UserListScreen(
    username: String = "hadley",
    viewModel: UserListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(username) {
        viewModel.fetchUsers(username)
    }

    when (val state = uiState) {
        is UserListUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is UserListUiState.Success -> {
            LazyColumn {
                items(state.users) { user ->
                    Row(Modifier.padding(16.dp)) {
                        AsyncImage(model = user.avatarUrl, contentDescription = user.name)
                        Spacer(Modifier.width(8.dp))
                        Column {
                            Text(user.name)
                            Text(user.fullName)
                        }
                    }
                }
            }
        }
        is UserListUiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${state.message}")
            }
        }
    }
}