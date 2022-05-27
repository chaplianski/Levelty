package com.example.levelty.di

import android.content.Context
import com.example.levelty.presenter.ui.ProfileFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

//    fun profileFragmentInject(profileFragment: ProfileFragment)
//    fun vesselsFragmentInject(vesselsFragment: VesselsFragment)
//    fun portFragmentFragmentInject(portFragment: PortFragment)
//    fun inspectionTypeFragmentInject(inspectionTypeFragment: InspectionTypeFragment)
//    fun inspectionSourceFragmentInject(inspectionSourceFragment: InspectionSourceFragment)
//    fun questionnairesFragmentInject(questionnairesFragment: QuestionnairesFragment)
//    fun questionsFragmentInject(questionsFragment: QuestionsFragment)
//    fun answersFragmentInject(answersFragment: AnswersFragment)
//    fun answerFragmentInject(answerFragment: AnswerFragment)
//    fun loginFragmentInject(loginFragment: LoginFragment)
//    fun notesFragmentInject(notesFragment: NotesFragment)
//    fun noteFragmentInject(noteFragment: NoteFragment)


    @Component.Builder
    interface Builder{
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}