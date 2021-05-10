package com.shankar.udemykotlin.firebase

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.database.SnapshotParser
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.ActivityFirebaseBinding
import de.hdodenhof.circleimageview.CircleImageView

class FirebaseActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    companion object {
        const val TAG = "TAG_FIREBASE"
        const val ANONYMOUS = "anonymous"
        const val MESSAGE_CHILD = "messages"
        const val REQUEST_IMAGE = 102
        const val LOADING_IMAGE_URL = "https://upload.wikimedia.org/wikipedia/commons/b/b1/Loading_icon.gif"
    }

    private var userName: String? = null
    private var userPhotoUrl: String? = null

    private var firebaseAuth: FirebaseAuth? = null
    private var firebaseUser: FirebaseUser? = null

    private var googleApiClient: GoogleApiClient? = null

    lateinit var linearLayoutManager: LinearLayoutManager

    private var firebaseDatabase: DatabaseReference? = null
    private var firebaseAdapter: FirebaseRecyclerAdapter<Message, MessageViewHolder>? = null

    private lateinit var binding: ActivityFirebaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance().reference


        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth!!.currentUser

        userName = ANONYMOUS

        checkSigninStatus()
        loadMessages()

        binding.sendButton.setOnClickListener {

            val message = Message(binding.textMessageEditText.text.toString(), userName!!, userPhotoUrl, null)
            firebaseDatabase!!.child(MESSAGE_CHILD).push().setValue(message)

            binding.textMessageEditText.setText("")
        }

        binding.addImageImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)

            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_IMAGE)

        }

    }

    private fun loadMessages() {

        linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.stackFromEnd = true


        val parser = SnapshotParser { snapshot: DataSnapshot ->

            val chatMessage = snapshot.getValue(Message::class.java)

            if (chatMessage != null) {
                chatMessage.id = snapshot.key
            }
            chatMessage!!

        }

        val messageReference = firebaseDatabase!!.child(MESSAGE_CHILD)

        val options = FirebaseRecyclerOptions.Builder<Message>()
            .setQuery(messageReference, parser)
            .build()


        firebaseAdapter = object : FirebaseRecyclerAdapter<Message, MessageViewHolder>(options) {
            override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MessageViewHolder {
                val inflater = LayoutInflater.from(viewGroup.context)
                return MessageViewHolder(inflater.inflate(R.layout.item_message, viewGroup, false))
            }

            override fun onBindViewHolder(holder: MessageViewHolder, position: Int, model: Message) {

                binding.progressBar.visibility = View.INVISIBLE

                holder.bind(model)

            }

        }

        firebaseAdapter!!.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                super.onItemRangeChanged(positionStart, itemCount)

                val messageCount = firebaseAdapter!!.itemCount
                val lastVisiblePosition =
                    linearLayoutManager.findLastCompletelyVisibleItemPosition()

                if (lastVisiblePosition == -1 || (positionStart >= messageCount - 1 && lastVisiblePosition == positionStart - 1)) {
                    binding.recyclerView.scrollToPosition(positionStart)
                }

            }
        })

        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = firebaseAdapter

    }

    private fun checkSigninStatus() {
        if (firebaseUser == null) {
            val intent = Intent(this, AuthenticationActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            userName = firebaseUser!!.displayName

            if (firebaseUser!!.photoUrl != null) {
                userPhotoUrl = firebaseUser!!.photoUrl!!.toString()
            }
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

        Log.e(TAG, "${p0.errorMessage}")
        Toast.makeText(this, "${p0.errorMessage}", Toast.LENGTH_SHORT).show()
    }

    class MessageViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        lateinit var message: Message

        var messageTextView: TextView
        var messageImageView: ImageView
        var nameTextView: TextView
        var userImage: CircleImageView

        init {
            messageTextView = itemView.findViewById(R.id.message_text_view)
            messageImageView = itemView.findViewById(R.id.message_image_view)
            nameTextView = itemView.findViewById(R.id.name_text_view)
            userImage = itemView.findViewById(R.id.messenger_image_view)
        }

        fun bind(message: Message) {
            this.message = message
            if (message.text != null) {
                messageTextView.apply {
                    text = message.text
                    visibility = View.VISIBLE
                }

                messageImageView.visibility = View.GONE
            } else if (message.imageUrl != null) {
                messageImageView.visibility = View.VISIBLE
                messageTextView.visibility = View.GONE

                val imageUrl = message.imageUrl

                if (imageUrl!!.startsWith("gs://")) {
                    val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl)
                    storageReference.downloadUrl.addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            val downloadUrl = task.result!!.toString()
                            Glide.with(messageImageView.context).load(downloadUrl).into(messageImageView)

                        } else {
                            Toast.makeText(messageImageView.context, "${task.exception}", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {

                    Glide.with(messageImageView.context)
                        .load(Uri.parse(message.imageUrl))
                        .into(messageImageView)
                }

            }

            nameTextView.text = message.name

            if (message.photoUrl == null) {
                userImage.setImageDrawable(
                    ContextCompat.getDrawable(userImage.context,
                        R.drawable.ic_account_circle_black_36dp
                    )
                )
            } else {
                Glide.with(userImage.context)
                    .load(Uri.parse(message.photoUrl))
                    .into(userImage)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        firebaseAdapter!!.stopListening()
    }

    override fun onResume() {
        super.onResume()
        firebaseAdapter!!.startListening()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_IMAGE) {
            if(resultCode == Activity.RESULT_OK) {
                if(data != null) {

                    val uri = data.data
                    val tempMessage = Message(null, userName, userPhotoUrl, LOADING_IMAGE_URL)
                    firebaseDatabase!!.child(MESSAGE_CHILD).push().setValue(tempMessage){
                        error: DatabaseError?, ref: DatabaseReference ->

                        if(error == null) {
                            val key = ref.key
                            val storageReference = FirebaseStorage.getInstance()
                                .getReference(firebaseUser!!.uid)
                                .child(key!!)
                                .child(uri!!.lastPathSegment!!)


                            putImageInStorage(storageReference, uri, key)
                        } else {
                            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                        }

                    }

                }
            }
        }
    }

    private fun putImageInStorage(storageReference: StorageReference, uri: Uri?, key: String?) {

        val uploadTask = storageReference.putFile(uri!!)
        uploadTask.continueWithTask { task ->
            if(!task.isSuccessful) {
                throw task.exception!!

            }
            storageReference.downloadUrl

        }.addOnCompleteListener {
            task ->
            if(task.isSuccessful) {
                val downloadUrl = task.result.toString()
                val message = Message(null,userName, userPhotoUrl, downloadUrl)
                firebaseDatabase!!.child(MESSAGE_CHILD).child(key!!).setValue(message)
            } else {
                Toast.makeText(this, "${task.exception}", Toast.LENGTH_SHORT).show()
            }
        }


    }
}