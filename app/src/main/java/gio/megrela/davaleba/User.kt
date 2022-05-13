package gio.megrela.davaleba

data class UserList(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<User>
)

data class UserResponse(
    val data: User
)

data class CreateUserRequest(
    val name: String,
    val job: String
)

data class CreateUserResponse(
    val id: Int,
    val name: String,
    val job: String,
    val createdAt: String
)

data class User(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String?
)
