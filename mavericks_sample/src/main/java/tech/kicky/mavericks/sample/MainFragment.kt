package tech.kicky.mavericks.sample

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.navigation.navGraphViewModel
import com.airbnb.mvrx.withState
import com.google.android.material.snackbar.Snackbar
import tech.kicky.mavericks.sample.articles.Args
import tech.kicky.mavericks.sample.databinding.FragmentMainBinding
import java.io.Serializable
import kotlin.random.Random


class MainFragment : Fragment(R.layout.fragment_main), MavericksView {

    private val mainViewModel: MainViewModel by fragmentViewModel()
    private val binding: FragmentMainBinding by viewBinding()

    private val navViewModel: NavViewModel by navGraphViewModel(R.id.mavericks_nav)

    private val adapter: HotKeyAdapter by lazy {
        HotKeyAdapter {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                navViewModel.incCount()
                navigate(R.id.main_to_article_list, Args(it))
            }
        }
    }


    private fun navigate(@IdRes id: Int, args: Serializable? = null) {
        findNavController().navigate(
            id,
            Bundle().apply { putSerializable(Mavericks.KEY_ARG, args) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.onAsync(
            MainState::request,
            deliveryMode = uniqueOnly(),
            onFail = {
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    Snackbar.make(
                        binding.root,
                        "HotKey request failed.",
                        Snackbar.LENGTH_INDEFINITE
                    )
                        .apply {
                            setAction("DISMISS") {
                                this.dismiss()
                            }
                            show()
                        }
                }
            },
            onSuccess = {
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    Snackbar.make(
                        binding.root,
                        "HotKey request successfully.",
                        Snackbar.LENGTH_INDEFINITE
                    ).apply {
                        setAction("DISMISS") {
                            this.dismiss()
                        }
                        show()
                    }
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.adapter = adapter
        binding.list.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.refresh.setOnRefreshListener {
            mainViewModel.getHotKeys()
        }
    }

    override fun invalidate() {
        withState(mainViewModel) {
            binding.refresh.isRefreshing = !it.request.complete
            adapter.submitList(if (Random.nextBoolean()) it.hotKeys.reversed() else it.hotKeys)
        }
    }
}