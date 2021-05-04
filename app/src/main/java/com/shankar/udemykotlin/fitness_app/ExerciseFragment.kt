package com.shankar.udemykotlin.fitness_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.FragmentExerciseBinding
import kotlin.math.min


class ExerciseFragment : Fragment() {

    data class Exercise(
        val exerciseType: String,
        val exerciseCount: Int
    )

    private val exercisesList: MutableList<Exercise> = mutableListOf(
        Exercise(exerciseType = "exercise_one", exerciseCount = 8),
        Exercise(exerciseType = "exercise_two", exerciseCount = 5),
        Exercise(exerciseType = "exercise_three", exerciseCount = 10),
        Exercise(exerciseType = "exercise_four", exerciseCount = 15),
        Exercise(exerciseType = "exercise_five", exerciseCount = 20)
    )

    private lateinit var binding: FragmentExerciseBinding

    private lateinit var currentExercise: Exercise
    private var count: Int = 0
    private var exerciseIndex: Int = 0
    private var exerciseSize = min((exercisesList.size + 1) / 2, 3)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_exercise, container, false)
        binding = FragmentExerciseBinding.inflate(layoutInflater)
//        nextButton = view.findViewById(R.id.next_button)


        binding.nextButton.apply {
            setOnClickListener {

                exerciseIndex++

                if (exerciseIndex < exerciseSize) {

                    currentExercise = exercisesList[exerciseIndex]
                    setExercise()

                } else {

                    Navigation.findNavController(it)
                        .navigate(R.id.action_exerciseFragment_to_wellDoneFragment)
                }

            }
        }

        binding.exitButton.apply {

            setOnClickListener {
                
                Navigation.findNavController(it)
                    .navigate(R.id.action_exerciseFragment_to_tryAgainFragment)

            }
        }

        randomizeExercise()

        return binding.root
    }


    private fun randomizeExercise() {
        exercisesList.shuffle()
        exerciseIndex = 0
        setExercise()
    }

    private fun setExercise() {

        currentExercise = exercisesList[exerciseIndex]

        count = currentExercise.exerciseCount

        binding.exerciseTextView.text = String.format(getString(R.string.exercise_text_view), count)

        binding.exerciseImageView.setImageResource(
            resources.getIdentifier(
                currentExercise.exerciseType,
                "drawable",
                (activity as AppCompatActivity).packageName
            )
        )

        (activity as AppCompatActivity).supportActionBar?.title =
            String.format(getString(R.string.toolbar_title), exerciseIndex + 1, exerciseSize)
    }


}