package br.com.mdr.packsburgers.view.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.viewpager.widget.ViewPager
import br.com.mdr.packsburgers.PacksApp

import br.com.mdr.packsburgers.R
import br.com.mdr.packsburgers.databinding.MainFragmentBinding
import br.com.mdr.packsburgers.model.Usuario
import br.com.mdr.packsburgers.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.main_fragment.*

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
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        mTabLayout = binding.tabLayout
        mViewPager = binding.viewPager
        mViewPager.adapter = PagerAdapter(childFragmentManager, fragmentList)
        mViewPager.clearOnPageChangeListeners()
        mTabLayout.post { mTabLayout.setupWithViewPager(mViewPager) }
        viewModel = MainViewModel(binding.layoutCabecalho)
        PacksApp.activity.mainViewModel = viewModel
        addObservers(binding)

        //Configura a toolbar
        PacksApp.instance.setActionBar(binding.toolbar, true)
        setHasOptionsMenu(true)
        NavigationUI.setupActionBarWithNavController(PacksApp.activity,
            PacksApp.activity.findNavController(R.id.nav_fragment))

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

        toolbar.title = ""
        viewModel.fetchPedidos()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_usuario -> {
                val direction = MainFragmentDirections.actionMainFragmentToUserDetailFragment(criaUsuario())
                findNavController().navigate(direction)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun criaUsuario(): Usuario {
        val usuario = Usuario()
        usuario.nome = "Marlon D. Rocha"
        usuario.cpf = "350.382.238-00"
        usuario.rg = "42.853.808-3"
        usuario.dataNasc = "30/01/1986"
        return usuario
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
