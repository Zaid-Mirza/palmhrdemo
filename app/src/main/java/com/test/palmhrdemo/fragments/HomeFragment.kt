package ae.rightfarm.fragments

import ae.rightfarm.activities.ActivityDetail
import ae.rightfarm.adapters.PhoneAdapter
import ae.rightfarm.database.PhonesDatabase
import ae.rightfarm.database.entities.PhoneDetail
import ae.rightfarm.databinding.FragmentHomeBinding
import ae.rightfarm.models.FilterObject
import ae.rightfarm.repositories.PhonesRepository
import ae.rightfarm.viewmodels.PhonesViewModel
import ae.rightfarm.viewmodels.PhonesViewModelFactory
import ae.rightfarm.viewmodels.SharedViewModel
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager


const val PHONE_DETAIL = "phone_detail";

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var phonesViewModel: PhonesViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var phones = arrayListOf<PhoneDetail>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = PhonesViewModelFactory(
            repository = PhonesRepository(
                phoneDetailDao = PhonesDatabase.getDatabase(requireContext()).phoneDetailDao()
            )
        )
        phonesViewModel = ViewModelProvider(this, viewModelFactory)[PhonesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initViews()
        return binding.root;
    }

    private fun initViews() {
        binding.clearFilterMaterialButton.setOnClickListener {
            sharedViewModel.setSelectedFilterData(null)
            binding.clearFilterMaterialButton.visibility = GONE
        }
        phonesViewModel.getAllPhones()
        phonesViewModel.phones.observe(viewLifecycleOwner) {
            phones.clear()
            phones.addAll(it)
            showData(phones)
            sharedViewModel.getSelectedFilterData().observe(viewLifecycleOwner) { filterObject ->

                handleFilters(filterObject)
            }
            if (!it.isNullOrEmpty()) {
                // Preparing Data for filter
                prepareFilterData(it)
            }

        }

    }

    private fun handleFilters(filterObject: FilterObject?) {
        var filteredData = phones.toList()
        if (filterObject != null) {

            if (filterObject.searchText.isNotEmpty()) {
                filteredData =
                    filteredData.filter { it.phoneName?.lowercase().toString().contains(filterObject.searchText.lowercase()) }
                        .toList()
            }

            if (filterObject.isAudioJack != null) {
                filteredData =
                    filteredData.filter { if (filterObject.isAudioJack == true) it.hasAudioJack?.lowercase() == "yes" else it.hasAudioJack?.lowercase() == "no" }
                        .toList()
            }
            if (filterObject.minPrice.isNotEmpty()) {
                filteredData =
                    filteredData.filter { it.priceEur!! >= filterObject.minPrice.toLong() }
                        .toList()
            }

            if (filterObject.maxPrice.isNotEmpty()) {
                filteredData =
                    filteredData.filter { it.priceEur!! <= filterObject.maxPrice.toLong() }
                        .toList()
            }
            if (filterObject.brands?.isNullOrEmpty() != true) {
                filteredData =
                    filteredData.filter { filterObject.brands?.contains(it.brandName) == true }
                        .toList()
            }
            if (filterObject.simTypes?.isNullOrEmpty() != true) {
                filteredData =
                    filteredData.filter { filterObject.simTypes?.contains(it.simType) == true }
                        .toList()
            }
            if (filterObject.gpsTypes?.isNullOrEmpty() != true) {
                filteredData =
                    filteredData.filter { filterObject.gpsTypes?.contains(it.hasGps) == true }
                        .toList()
            }


        }

        showData(filteredData)
    }

    private fun showData(it: List<PhoneDetail>?) {
        binding.emptyLayout.recordsRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)
        binding.emptyLayout.recordsRecyclerView.adapter = PhoneAdapter(it) {

            val intent = Intent(requireContext(), ActivityDetail::class.java);
            intent.putExtra(PHONE_DETAIL, it)
            startActivity(intent)
        }
        binding.emptyLayout.emptyStateLinearLayout.visibility =
            if (!it.isNullOrEmpty()) View.GONE else View.VISIBLE
    }

    private fun prepareFilterData(phones: List<PhoneDetail>) {

        val brands = phones.map { it.brandName }.distinct()
        val gpsTypes = phones.map { it.hasGps }.distinct()
        val simTypes = phones.map { it.simType }.distinct()

        val hashMap = HashMap<String, Any>()
        hashMap[BRANDS] = brands
        hashMap[GPS] = gpsTypes
        hashMap[SIMS] = simTypes

        sharedViewModel.setDataForFilter(hashMap)


    }

}