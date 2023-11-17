package br.senai.sp.jandira.proliseumtcc.firebase

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.util.UUID

class StorageTeamUtil {

    companion object {

        fun uploadToTeamStorage(uri: Uri, context: Context, type: String, id: String, local: String) {
            val storage = Firebase.storage

            // Create a storage reference from our app
            var storageRef = storage.reference

            val unique_image_name = UUID.randomUUID()

            var spaceRef: StorageReference

            spaceRef = storageRef.child("$type/$id/$local")

            val byteArray: ByteArray? = context.contentResolver
                .openInputStream(uri)
                ?.use { it.readBytes() }

            byteArray?.let{

                var uploadTask = spaceRef.putBytes(byteArray)
                uploadTask.addOnFailureListener {
                    Toast.makeText(
                        context,
                        "upload failed",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Handle unsuccessful uploads
                }.addOnSuccessListener { taskSnapshot ->
                    // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                    // ...
                    Toast.makeText(
                        context,
                        "upload successed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }



        }

    }
}