package com.example.retrofitcall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitcall.adapter.RecyclerViewAdapter
import com.example.retrofitcall.models.RecyclerList
import com.example.retrofitcall.viewmodel.MainActivityViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class RecyclerListFragment : Fragment() {

    private lateinit var recyclerAdapter:RecyclerViewAdapter
    private lateinit var viewModel:MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_recycler_list, container, false)

        viewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)

        val recyclerView=view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(requireContext())

        recyclerAdapter=RecyclerViewAdapter()
        recyclerView.adapter=recyclerAdapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
    }

    private fun observeData(){
        viewModel.getRecyclerListObserver().observe(this, Observer<RecyclerList> {
            if(it!=null){
                recyclerAdapter.setUpdatedData(it.items)
            }
            else{
                Toast.makeText(context,"Error in getting data",Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecyclerListFragment()
    }
}