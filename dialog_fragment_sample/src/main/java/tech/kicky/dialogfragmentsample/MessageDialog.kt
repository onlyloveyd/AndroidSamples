package tech.kicky.dialogfragmentsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import tech.kicky.dialogfragmentsample.databinding.DialogMessageBinding

class MessageDialog : DialogFragment() {

    private val viewModel by activityViewModels<SampleViewModel>()

    private val binding by lazy {
        DialogMessageBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btClose.setOnClickListener {
            dismiss()
        }
        binding.btAdd.setOnClickListener {
            viewModel.incCount()
        }
    }

}