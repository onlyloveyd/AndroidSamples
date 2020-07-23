package cn.onlyloveyd.androidpractice

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import cn.onlyloveyd.androidpractice.paging.PagingActivity
import cn.onlyloveyd.androidpractice.retrofitcoroutine.RetrofitCoroutineActivity
import cn.onlyloveyd.androidpractice.viewmodelpractice.ViewModelActivity
import cn.onlyloveyd.androidpractice.vp2practice.TabVp2Activity

class MainActivity : ListActivity() {

    private lateinit var listAdapter: SimpleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAdapter = SimpleAdapter(
            this,
            getData(),
            android.R.layout.simple_list_item_1,
            arrayOf("title"),
            intArrayOf(android.R.id.text1)
        )
        setListAdapter(listAdapter)
    }

    private fun getData(): List<Map<String, Any>> {
        val data = mutableListOf<Map<String, Any>>()

        data.add(
            mapOf(
                "title" to "ViewModel",
                "intent" to activityToIntent(ViewModelActivity::class.java.name)
            )
        )
        data.add(
            mapOf(
                "title" to "ViewPager2",
                "intent" to activityToIntent(TabVp2Activity::class.java.name)
            )
        )
        data.add(
            mapOf(
                "title" to "Retrofit Coroutine",
                "intent" to activityToIntent(RetrofitCoroutineActivity::class.java.name)
            )
        )

        data.add(
            mapOf(
                "title" to "Paging3",
                "intent" to activityToIntent(PagingActivity::class.java.name)
            )
        )


        return data
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        Toast.makeText(this, "position: $position id: $id", Toast.LENGTH_SHORT).show()

        val map = l.getItemAtPosition(position) as Map<*, *>
        val intent = map["intent"] as Intent
        intent.putExtra("title", map["title"] as String)
        startActivity(intent)
    }

    private fun activityToIntent(activity: String): Intent {
        return Intent().setClassName(packageName, activity)
    }
}