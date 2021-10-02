package com.android.odevler.ebraryucel.airplane

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.ebraryucel.data.Airplane
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.util.jar.Manifest

class AddAirplaneActivity : AppCompatActivity() {   //Firebase storage erişimi yetkisi olmadığından dolayı resim ekleme ile
                                                    // alakalı işlemler yorum satırına alındı.

    val inputManifacturer by lazy { findViewById<EditText>(R.id.editTextManifacturer) }
    val inputModel by lazy { findViewById<EditText>(R.id.editTextModel) }
    val inputOwner by lazy { findViewById<EditText>(R.id.editTextOwner) }
 //   val inputImage by lazy { findViewById<ImageView>(R.id.imageView2) }
    val inputCapacity by lazy { findViewById<EditText>(R.id.editTextCapacity) }

    private lateinit var firestore: FirebaseFirestore
 //   private lateinit var storage: FirebaseStorage
 //   private lateinit var activityResultLauncher:ActivityResultLauncher<Intent>
 //   private lateinit var permissionLauncher: ActivityResultLauncher<String>
 //   var selectedPicture: Uri?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_airplane)
        firestore= Firebase.firestore
    //    storage=Firebase.storage
    //    registerLauncher()

    }

/*
    fun pickImg(view: View){
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                Snackbar.make(view,"Permission needed!",Snackbar.LENGTH_INDEFINITE).setAction("Give permission"){

                }.show()
            }
            else{
                permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
        else{
            val intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intent)
        }

    }

    private fun registerLauncher(){
        activityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode== RESULT_OK){
                val intentResult=it.data
                intentResult.let {
                    selectedPicture=intentResult?.data
                    selectedPicture?.let {
                        inputImage.setImageURI(it)
                    }
                }

            }
        }

        permissionLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if(it){
                val intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intent)

            }
            else{
                Toast.makeText(this,"Permission needed",Toast.LENGTH_LONG).show()
            }

        }

    }
*/
    fun saveOnClick(view:View) {

    val manifacturer = inputManifacturer.text.toString()
    val model = inputModel.text.toString()
    val owner = inputOwner.text.toString()
    val capacity = inputCapacity.text.toString()



    if (!(manifacturer.isNullOrBlank() || model.isNullOrBlank() || owner.isNullOrBlank() || capacity.isNullOrBlank())) {


        //val imgName="airplane-"+manifacturer+model+owner
        //val imgReference=storage.reference.child("img").child(imgName)

        //selectedPicture.let {
        //imgReference.putFile(selectedPicture!!).addOnSuccessListener {


        // imgReference.downloadUrl.addOnSuccessListener {
        //    val downloadUrl=it.toString()

        // val airplane=Airplane(manifacturer,model,owner,downloadUrl,capacity.toInt())

        val hashmap =
            hashMapOf<String, Any>() // Input olarak verilen değerlerin hashmap şeklinde firebase'e kaydedilmesi
        hashmap.put("manifacturer", manifacturer)
        hashmap.put("model", model)
        hashmap.put("owner", owner)
        hashmap.put("capacity", capacity)

        firestore.collection("ebraryucel").add(hashmap).addOnSuccessListener {
            Toast.makeText(
                this@AddAirplaneActivity,
                "Airplane instance upload successful!!",
                Toast.LENGTH_LONG
            ).show()
            finish()

        }.addOnFailureListener {
            Toast.makeText(this@AddAirplaneActivity, it.localizedMessage, Toast.LENGTH_LONG).show()
        }

    }else{
        Toast.makeText(this,"All inputs must be filled except image!",Toast.LENGTH_LONG).show()
    }


    // }.addOnFailureListener{
    //    Toast.makeText(this@AddAirplaneActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
    //  }
    //}

    }
    }



