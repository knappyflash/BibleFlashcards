package com.example.bibleflashcards.ui.home

import Passage
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bibleflashcards.databinding.FragmentHomeBinding
import android.content.Context
import java.io.IOException
import kotlin.random.Random

class HomeFragment : Fragment() {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var _binding: FragmentHomeBinding? = null


    fun readAssetFile(context: Context, filename: String): String {
        return try {
            val assetManager = context.assets
            val inputStream = assetManager.open(filename)
            inputStream.bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            "Error reading file"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var Passages: MutableList<Passage> = mutableListOf()

        var TViewPassageIndex: TextView = binding.TViewPassageIndex
        var TViewPassage: TextView = binding.TViewPassage
        var textViewCorrectRatio: TextView = binding.textViewCorrectRatio
        var BtnNextVerse: Button = binding.BtnNextVerse
        var BtnShowAnswer: Button = binding.BtnShowAnswer
        var BtnWrong: Button = binding.BtnWrong
        var BtnRight: Button = binding.BtnRight

        var txtBibleVerses = readAssetFile(requireContext(), "Passages.txt")
        var txtBibleVersesAry = txtBibleVerses.split("\n")

        for (line: String in txtBibleVersesAry) {
            var LineSplit = line.split("|")
            Passages.add(Passage(LineSplit[0],LineSplit[1].toBoolean(),
                LineSplit[2].toLong(),LineSplit[3].toLong(),LineSplit[4]))
        }

        var RandVerseIndex: Int = Random.nextInt(Passages.count())
        var RandVerseIndexBuffer: Int = RandVerseIndex

        TViewPassageIndex.text = Passages[RandVerseIndex].PassageIndex
        TViewPassage.text = ""

        fun Get_Correct_Percentage(MyCorrect: Long, MyTotal: Long): String {
            try {
                return (MyCorrect / MyTotal).toString()
            } catch (e: ArithmeticException) {
                return "0"
            }
        }
          
        textViewCorrectRatio.text = Passages[RandVerseIndex].Get_Correct_Ratio()

        BtnNextVerse.setOnClickListener {
            while (RandVerseIndex == RandVerseIndexBuffer) {
                RandVerseIndex = Random.nextInt(txtBibleVersesAry.count())
            }
            RandVerseIndexBuffer = RandVerseIndex
            TViewPassageIndex.text = Passages[RandVerseIndex].PassageIndex
            TViewPassage.text = ""
            textViewCorrectRatio.text = Passages[RandVerseIndex].Get_Correct_Ratio()
        }

        BtnShowAnswer.setOnClickListener {
            TViewPassage.text = Passages[RandVerseIndex].Passage
            textViewCorrectRatio.text = Passages[RandVerseIndex].Get_Correct_Ratio()
        }

        BtnWrong.setOnClickListener {
            Passages[RandVerseIndex].Wrong()
            textViewCorrectRatio.text = Passages[RandVerseIndex].Get_Correct_Ratio()
        }

        BtnRight.setOnClickListener {
            Passages[RandVerseIndex].Correct()
            textViewCorrectRatio.text = Passages[RandVerseIndex].Get_Correct_Ratio()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}