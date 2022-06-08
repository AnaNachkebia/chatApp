package com.example.firebaseproject

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfileActivity : AppCompatActivity() {
    //private lateinit var textview:TextView
   // private lateinit var buttonLogOut:Button
   // private lateinit var buttonChangePassword:Button
    private lateinit var userRecyclerView:RecyclerView
    private lateinit var userList:ArrayList<User>
    private lateinit var adapter:UserAdapter
    private lateinit var mAuth:FirebaseAuth
    private lateinit var mDbRef:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        userList=ArrayList()
        adapter=UserAdapter(this,userList)
        mAuth= FirebaseAuth.getInstance()
        mDbRef=FirebaseDatabase.getInstance().getReference()
        userRecyclerView=findViewById(R.id.userRecyclerView)

        userRecyclerView.layoutManager=LinearLayoutManager(this)
        userRecyclerView.adapter=adapter
        mDbRef.child("user").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for(postSnapshot in snapshot.children){
                    val currentUser=postSnapshot.getValue(User::class.java)
                    if(mAuth.currentUser?.uid!=currentUser?.uid) {
                        userList.add(currentUser!!)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        //init()
        //textview.text=FirebaseAuth.getInstance().currentUser?.uid
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.LogOutButton){

            mAuth.signOut()
            val intent=Intent(this@ProfileActivity,LoginActivity::class.java)
            finish()
            startActivity(intent)
            return true
        }
        return true
    }
    private fun init(){

        /*textview=findViewById(R.id.textView)
        buttonLogOut=findViewById(R.id.LogOutButton)
        buttonChangePassword=findViewById(R.id.ChangePassword)
        listeners()*/
    }
    private fun listeners(){
        /*buttonLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
        buttonChangePassword.setOnClickListener {
            startActivity(Intent(this,ChangePasswordActivity::class.java))
        }*/
    }
}