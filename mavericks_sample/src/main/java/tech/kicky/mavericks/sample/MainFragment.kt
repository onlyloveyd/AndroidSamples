package tech.kicky.mavericks.sample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import tech.kicky.mavericks.sample.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main), MavericksView {

    private val mainViewModel: MainViewModel by fragmentViewModel()
    private val binding: FragmentMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            mainViewModel.incCount()
        }
    }

    override fun invalidate() {
        withState(mainViewModel) {
            binding.tvCount.text = it.count.toString()
        }
    }
}