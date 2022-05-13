package gio.megrela.davaleba

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var api: API

    private lateinit var btnAddUser: MaterialButton
    private lateinit var rvUsers: RecyclerView

    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        api = API.create()

        btnAddUser = findViewById(R.id.btnAddUser)
        rvUsers = findViewById(R.id.rvUsers)
        adapter = UserAdapter {
            startActivity(
                Intent(this, UserDetailsActivity::class.java)
                    .putExtra("user_id", it.id)
            )
        }

        btnAddUser.setOnClickListener {
            startActivity(Intent(this, CreateUserActivity::class.java))
        }

        rvUsers.adapter = adapter

        api.getUsers().enqueue(object : Callback<UserList> {
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                adapter.submitList(response.body()?.data)
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}