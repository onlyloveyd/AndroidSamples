package tech.kicky.viewpager2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import tech.kicky.viewpager2.databinding.FragmentSampleBinding

class SampleFragment : Fragment(R.layout.fragment_sample) {
    val binding by viewBinding<FragmentSampleBinding> { }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val content = arguments?.getString(ARG_TITLE)
        binding.content.text = content
    }

    companion object {
        const val ARG_TITLE = "title"
        fun newInstance(title: String): SampleFragment {
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            val fragment = SampleFragment()
            fragment.arguments = args
            return fragment
        }
    }
}