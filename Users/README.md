# Users App — Build Guide

Recreation of the **UsersLIst** project. Clean Architecture (data/domain/presentation) + MVVM, Jetpack Compose, Hilt, Retrofit.

## Stack
- Kotlin, Jetpack Compose, Material3
- Hilt (DI) + KSP
- Retrofit + kotlinx.serialization + OkHttp
- Coil (image loading)
- minSdk 24 / targetSdk 36 / compileSdk 36

## Package structure
```
com.example.userslist
├── data
│   ├── mapper/         UserMapper.kt        (DTO -> domain model)
│   ├── remote/api/     UserService.kt       (Retrofit interface)
│   ├── remote/dto/     UserDto.kt           (API response model)
│   └── repository/     UserDataRepositoryImpl.kt
├── domain
│   ├── model/          User.kt
│   ├── repository/     UserDataRepository.kt (interface)
│   └── usecase/        GetUserDataUseCase.kt
├── modules/            NetworkModule.kt, RepositoryModule.kt (Hilt)
├── presentation/usersList/
│   ├── UserListScreen.kt
│   └── UserListViewModel.kt   (UiState: Loading/Success/Error)
├── ui/theme/           Color.kt, Theme.kt, Type.kt
├── MainActivity.kt
└── UsersListApplication.kt   (@HiltAndroidApp)
```

## TODO — step by step

1. **Project setup**
   - [ ] New Compose project, package `com.example.userslist`
   - [ ] Add dependencies: Retrofit, OkHttp, kotlinx-serialization, retrofit2-kotlinx-serialization-converter, Hilt (+ ksp), hilt-navigation-compose, Coil
   - [ ] Apply plugins: `kotlin-serialization`, `ksp`, `hilt`
   - [ ] Add internet permission in `AndroidManifest.xml`

2. **Domain layer**
   - [ ] `domain/model/User.kt` — plain data class
   - [ ] `domain/repository/UserDataRepository.kt` — interface with `suspend fun getUserData(name: String): List<User>`
   - [ ] `domain/usecase/GetUserDataUseCase.kt` — invoke() calls repository

3. **Data layer**
   - [ ] `data/remote/dto/UserDto.kt` — `@Serializable` response model
   - [ ] `data/remote/api/UserService.kt` — Retrofit `@GET("users/{name}/repos")`
   - [ ] `data/mapper/UserMapper.kt` — DTO -> domain mapping function
   - [ ] `data/repository/UserDataRepositoryImpl.kt` — implements domain repository using `UserService` + mapper

4. **DI (Hilt)**
   - [ ] `UsersListApplication.kt` — `@HiltAndroidApp`
   - [ ] `modules/NetworkModule.kt` — provides Retrofit, OkHttp, UserService (base URL: GitHub API `https://api.github.com/`)
   - [ ] `modules/RepositoryModule.kt` — `@Binds` repository interface to impl

5. **Presentation layer**
   - [ ] `UserListUiState` sealed interface: `Loading`, `Success(users)`, `Error(message)`
   - [ ] `UserListViewModel` — `@HiltViewModel`, exposes `StateFlow<UserListUiState>`, `fetchUsers(username)`
   - [ ] `UserListScreen.kt` — Compose UI: input field for username, list of repos, loading/error states, Coil for avatars if needed

6. **Wiring**
   - [ ] `MainActivity.kt` — `@AndroidEntryPoint`, sets Compose content with theme + `UserListScreen`
   - [ ] `ui/theme/` — Color, Theme, Type (default Compose Material3 theme)

7. **Testing**
   - [ ] Unit test for `GetUserDataUseCase` / mapper
   - [ ] Instrumented smoke test for `MainActivity`

8. **Polish**
   - [ ] Handle empty/error states in UI
   - [ ] Verify ProGuard rules for release build
   - [ ] Update `strings.xml` / app icon

## Reference
Original project: `C:\android-apps\UsersLIst`
