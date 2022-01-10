package tech.kicky.paging3.sample.ui.repo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import tech.kicky.paging3.sample.R
import tech.kicky.paging3.sample.RepoAdapter
import tech.kicky.paging3.sample.databinding.FragmentReposBinding

class RepoFragment : Fragment(R.layout.fragment_repos), MavericksView {

    private val binding: FragmentReposBinding by viewBinding()
    private val viewModel: RepoViewModel by fragmentViewModel()
    private val adapter by lazy {
        RepoAdapter()
    }


    override fun invalidate() {
        withState(viewModel) {
//            adapter.submitData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.recyclerView.adapter = adapter
        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.recyclerView.visibility = View.VISIBLE
                }
                is LoadState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.INVISIBLE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(
                        context,
                        "Load Error: ${state.error.message}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }


}