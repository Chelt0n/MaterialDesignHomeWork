package com.example.materialdesignhomework.recyclerviewnotes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.materialdesignhomework.R
import com.example.materialdesignhomework.databinding.NotesFragmentBinding
import com.example.materialdesignhomework.recyclerviewnotes.model.NoteUiModel

class NotesFragment : Fragment() {

    companion object {
        const val RESULT_KEY = "RESULT_KEY"
        const val RESULT_BUNDLE_KEY = "RESULT_BUNDLE_KEY"
        fun newInstance() = NotesFragment()
    }

    private lateinit var binding: NotesFragmentBinding
    private val viewModel by viewModels<NotesViewModel>()
    private val adapter by lazy {
        NotesRecyclerViewAdapter(
            onItemClick = {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, EditNoteFragment.newInstance(it)).addToBackStack("note")
                    .commit()
            },
            onItemDelete = { viewModel.onItemRemoved(it) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NotesFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setFragmentResultListener(RESULT_KEY) { _, bundle ->
            viewModel.updateItem(bundle.getSerializable(RESULT_BUNDLE_KEY) as NoteUiModel)
        }


        binding.notesRecyclerView.adapter = adapter
        binding.notesRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )

        val callback = ItemDragTouchHelperCallback(
            onItemMove = { from, to ->
                viewModel.onItemMoved(from, to)
            },
            onItemSwiped = { position -> viewModel.onItemRemoved(position) },
        )
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.notesRecyclerView)

        binding.notesRecyclerFAB.setOnClickListener { viewModel.onItemAdd() }
        observeViewModel()
        viewModel.loadData()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeViewModel() {
        viewModel.getItems().observe(viewLifecycleOwner) { items ->

            val sampleDiffUtil = SampleDiffUtil(
                oldList = adapter.data,
                newList = items,
            )
            val sampleDiffResult = DiffUtil.calculateDiff(sampleDiffUtil)
            adapter.data = items
            sampleDiffResult.dispatchUpdatesTo(adapter)
        }
    }


}