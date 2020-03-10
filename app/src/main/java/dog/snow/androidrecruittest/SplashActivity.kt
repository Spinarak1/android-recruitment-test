package dog.snow.androidrecruittest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dog.snow.androidrecruittest.repository.ApiClient
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity(R.layout.splash_activity) {
    var userList = ArrayList<RawUser>()
    var photoList = ArrayList<RawPhoto>()
    var albumList = ArrayList<RawAlbum>()
    var isUser: Boolean = true
    var isAlbum: Boolean = true
    var iterator: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        while(getUser(iterator) != false) {
            iterator++
        }
        iterator = 1
        while(getAlbum(iterator) != false) {
            iterator++
        }
        getPhoto()
        val bundle = Bundle()
        bundle.putParcelableArrayList("RawUser", userList)
        bundle.putParcelableArrayList("RawPhoto", photoList)
        bundle.putParcelableArrayList("RawAlbum", albumList)

    }
    private fun showError(errorMessage: String?) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.cant_download_dialog_title)
            .setMessage(getString(R.string.cant_download_dialog_message, errorMessage))
            .setPositiveButton(R.string.cant_download_dialog_btn_positive) { _, _ -> /*tryAgain()*/ }
            .setNegativeButton(R.string.cant_download_dialog_btn_negative) { _, _ -> finish() }
            .create()
            .apply { setCanceledOnTouchOutside(false) }
            .show()
    }
    private fun getUser(iterator: Int) :Boolean{
        val call: Call<RawUser> = ApiClient.getUser.userDownload(iterator)
        call.enqueue(object : Callback<RawUser> {

            override fun onResponse(call: Call<RawUser>?, response: Response<RawUser>?) {
                if(response!!.body()!! != null) {
                    userList.add(response!!.body()!!)
                    isUser = true
                }else{
                    isUser = false
                }
            }

            override fun onFailure(call: Call<RawUser>?, t: Throwable?) {
                showError("")
            }

        })
        return isUser
    }
    private fun getAlbum(iterator: Int) :Boolean{
        val call: Call<RawAlbum> = ApiClient.getAlbum.albumDownload(iterator)
        call.enqueue(object : Callback<RawAlbum> {

            override fun onResponse(call: Call<RawAlbum>?, response: Response<RawAlbum>?) {
                if(response!!.body()!! != null) {
                    albumList.add(response!!.body()!!)
                    isAlbum = true
                }else{
                    isAlbum = false
                }
            }

            override fun onFailure(call: Call<RawAlbum>?, t: Throwable?) {
            }

        })
        return isAlbum
    }
    private fun getPhoto() {
        val call: Call<List<RawPhoto>> = ApiClient.getPhoto.photosDownload()
        call.enqueue(object : Callback<List<RawPhoto>> {

            override fun onResponse(call: Call<List<RawPhoto>>?, response: Response<List<RawPhoto>>?) {
                photoList.addAll(response!!.body()!!)
            }

            override fun onFailure(call: Call<List<RawPhoto>>?, t: Throwable?) {
            }

        })
    }
}