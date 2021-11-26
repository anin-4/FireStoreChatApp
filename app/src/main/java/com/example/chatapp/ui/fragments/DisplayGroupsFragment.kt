package com.example.chatapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.databinding.FragmentGroupsBinding
import com.example.chatapp.ui.adapters.GroupsRecyclerViewAdapter
import com.example.chatapp.ui.addGroupDialog.AddGroupButtonListener
import com.example.chatapp.ui.addGroupDialog.AddGroupDialog
import com.example.chatapp.ui.viewmodels.GroupsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class DisplayGroupsFragment:Fragment() {

    private lateinit var binding:FragmentGroupsBinding
    private val groupsRecyclerViewAdapter:GroupsRecyclerViewAdapter= GroupsRecyclerViewAdapter()
    private val groupsViewModel:GroupsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding= FragmentGroupsBinding.inflate(inflater)
        return binding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply{
            groupsRecyclerView.adapter=groupsRecyclerViewAdapter
            groupsRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        }

        groupsViewModel.groups.observe(viewLifecycleOwner,{
            groupsRecyclerViewAdapter.submitList(it)
        })

        binding.addGroup.setOnClickListener {
            AddGroupDialog(requireContext(),object : AddGroupButtonListener{
                override fun onAddButtonListener(name: String) {
                    groupsViewModel.addNewGroup(name)
                }
            }).show()
        }

    }
}