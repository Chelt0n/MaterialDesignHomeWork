package com.example.materialdesignhomework.recyclerviewnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.materialdesignhomework.databinding.NoteEditFragmentBinding
import com.example.materialdesignhomework.recyclerviewnotes.NotesFragment.Companion.RESULT_BUNDLE_KEY
import com.example.materialdesignhomework.recyclerviewnotes.NotesFragment.Companion.RESULT_KEY
import com.example.materialdesignhomework.recyclerviewnotes.model.NoteUiModel

class EditNoteFragment : Fragment() {
    private lateinit var binding: NoteEditFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NoteEditFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uiModel = requireArguments().getSerializable(KEY) as NoteUiModel
        binding.titleEditText.setText(uiModel.title)
        binding.descriptionEditText.setText(uiModel.description)


        binding.buttonSaveNote.setOnClickListener {
            val noteUiModel = NoteUiModel(
                uiModel.id,
                binding.titleEditText.text.toString(),
                binding.descriptionEditText.text.toString()
            )
            val bundle = Bundle().apply {
                putSerializable(RESULT_BUNDLE_KEY, noteUiModel)
            }
            setFragmentResult(RESULT_KEY, bundle)
            requireActivity().onBackPressed()
        }
    }

    companion object {
        private const val KEY = "kye"
        fun newInstance(uiModel: NoteUiModel) = EditNoteFragment().apply {
            val bundle = Bundle()
            bundle.putSerializable(KEY, uiModel)
            arguments = bundle
        }
    }

}
