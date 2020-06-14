package ge.msda.myapplication

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MyActivity : Activity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(myDataset)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter

        }
    }
}
// _____Adapter________
class MyAdapter(private val myDataset: Array<String>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)


    // Create new views
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_text_view, parent, false) as TextView
        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.textView.text = myDataset[position]
    }

    // Return the size of your dataset
    override fun getItemCount() = myDataset.size
}

// display text into recycleview
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("MyApplication", Context.MODE_PRIVATE)
        val savedText = sharedPreferences.getString("NOTE", "")
        my_recycler_view.recycledViewPool() = savedText

        addBtn.setOnClickListener {

            val input = inputText.my_recycler_view.toString()

            if (!TextUtils.isEmpty(input)) {

                val text = my_recycler_view.recycledViewPool.toString()

                val resultText = input + "\n" + text

                my_recycler_view.recycledViewPool = resultText

                inputText.setText("")

                sharedPreferences.edit().putString("NOTE", resultText).apply()

            }

        }

    }

}