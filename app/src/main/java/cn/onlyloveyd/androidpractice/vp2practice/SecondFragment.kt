package cn.onlyloveyd.androidpractice.vp2practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cn.onlyloveyd.androidpractice.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    companion object {
        fun newInstance() =
            SecondFragment()
    }

    private lateinit var mBinding: FragmentSecondBinding
    private val viewModel: Vp2ViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!this::mBinding.isInitialized) {
            mBinding = FragmentSecondBinding.inflate(inflater, container, false)
            mBinding.viewModel = viewModel
            viewModel.sample.observe(viewLifecycleOwner, Observer {
                mBinding.viewModel = viewModel
            })
        }
        return mBinding.root
    }


}