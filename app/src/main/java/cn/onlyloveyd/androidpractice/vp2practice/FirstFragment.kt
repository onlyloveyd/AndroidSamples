package cn.onlyloveyd.androidpractice.vp2practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cn.onlyloveyd.androidpractice.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    private lateinit var mBinding: FragmentFirstBinding
    private val viewModel: Vp2ViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (!this::mBinding.isInitialized) {
            mBinding = FragmentFirstBinding.inflate(inflater, container, false)
            mBinding.viewModel = viewModel
            viewModel.sample.observe(viewLifecycleOwner, Observer {
                mBinding.viewModel = viewModel
            })
        }
        return mBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FirstFragment()
    }
}