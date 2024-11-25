package com.example.lista2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lista2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private  val navController: NavController by lazy{
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
            as NavHostFragment
        navHostFragment.findNavController()
    }
    private val all_exercise_list: UsersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {


        // Create 5 users shared by every Fragment
//        for (nums in 1..5){
        all_exercise_list.generateExerciseLists(20)
//        }




        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.bottomNav.setupWithNavController(navController)
    }
}

//class WordListAdapter(
//    private val wordList: MutableList<String>,
//    private val onItemClick: (String) -> Unit
//) : RecyclerView.Adapter<WordListViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListViewHolder {
//        return WordListViewHolder(
//            WordListItemBinding.inflate(
//                LayoutInflater.from(parent.context), parent, false
//            )
//        ){onItemClick(wordList[it])}
//    }
//    binding.recyclerView.apply {
//        adapter = WordListAdapter(wordList){
//            Toast.makeText(this@MainActivity, "Clicked + $it", Toast.LENGTH_SHORT).show()
//        }
//        layoutManager = LinearLayoutManager(this@MainActivity)
//    }
//}



