package br.com.mdr.packsburgers.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import br.com.mdr.packsburgers.PacksApp

import br.com.mdr.packsburgers.R
import br.com.mdr.packsburgers.databinding.MainFragmentBinding
import br.com.mdr.packsburgers.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout

class MainFragment : Fragment() {
    private var fragmentList = ArrayList<Fragment>()

    companion object {
        private lateinit var mTabLayout: TabLayout
        private lateinit var mViewPager: ViewPager
        private lateinit var pedidosAFragment: PedidosFragment
        private lateinit var pedidosFFragment: PedidosFragment
        private lateinit var viewModel: MainViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentList.clear()
        pedidosAFragment = PedidosFragment()
        pedidosFFragment = PedidosFragment()
        fragmentList.add(pedidosFFragment)
        fragmentList.add(pedidosAFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = MainViewModel()
        PacksApp.activity.mainViewModel = viewModel
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        mTabLayout = binding.tabLayout
        mViewPager = binding.viewPager
        mViewPager.adapter = PagerAdapter(childFragmentManager, fragmentList)
        mViewPager.clearOnPageChangeListeners()
        mTabLayout.post { mTabLayout.setupWithViewPager(mViewPager) }
        addObservers(binding)

        return binding.root
    }

    private fun addObservers(binding: MainFragmentBinding) {
        viewModel.pedidosA.observe(viewLifecycleOwner, Observer { pedidos ->
            pedidosAFragment.setPedidos(pedidos)
        })
        viewModel.pedidosF.observe(viewLifecycleOwner, Observer { pedidos ->
            pedidosFFragment.setPedidos(pedidos)
        })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.isLoading = isLoading
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchPedidos()
    }

    internal inner class PagerAdapter(fm: FragmentManager, private val pages: ArrayList<Fragment>): FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? {
            return pages[position]
        }

        override fun getPageTitle(position: Int): CharSequence? {
            var strTitle = ""
            when(position) {
                0 -> strTitle = getString(R.string.finalizados)
                1 -> strTitle = getString(R.string.agendados)
            }
            return strTitle
        }
        override fun getCount() = pages.size
    }
}
