package dog.snow.androidrecruittest.ui

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser
import dog.snow.androidrecruittest.ui.adapter.ListAdapter


class ListFragment : Fragment(R.layout.list_fragment) {
    /*var userList = ArrayList<RawUser>()
    var photoList = ArrayList<RawPhoto>()
    var albumList = ArrayList<RawAlbum>()*/

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.list_fragment, container, false)
        var recyclerView: RecyclerView? = null
        var layoutManager: RecyclerView.LayoutManager? = null
        val userList = getArguments()?.getParcelableArrayList<RawUser>("RawUser");
        val photoList = getArguments()?.getParcelableArrayList<RawPhoto>("RawPhoto");
        val albumList = getArguments()?.getParcelableArrayList<RawAlbum>("RawAlbum");
        //val adapter = ListAdapter(photoList)
        //addListenerOnRatingBar(view);
        recyclerView = view.findViewById(R.id.rv_items)
        layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setHasFixedSize(true)
        //recyclerView.setAdapter(adapter)


        return view
    }
}
