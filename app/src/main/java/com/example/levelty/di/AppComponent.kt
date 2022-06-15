package com.example.levelty.di

import android.content.Context
import com.example.levelty.presenter.dialogs.CategoryChooseFragment
import com.example.levelty.presenter.dialogs.KidsInterestChooseFragment
import com.example.levelty.presenter.dialogs.ParentsPurposeChooseFragment
import com.example.levelty.presenter.dialogs.RepeatChooseFragment
import com.example.levelty.presenter.ui.DayPersonalTasksFragment
import com.example.levelty.presenter.ui.KidsGoalsFragment
import com.example.levelty.presenter.ui.ProfileFragment
import com.example.levelty.presenter.ui.parent.TasksFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun profileFragmentInject(profileFragment: ProfileFragment)
    fun dayPersonalTasksFragmentInject(dayPersonalTasksFragment: DayPersonalTasksFragment)
    fun repeatChooseFragmentInject(repeatChooseFragment: RepeatChooseFragment)
    fun categoryChooseFragmentInject(categoryChooseFragment: CategoryChooseFragment)
    fun parentPurposeChooseFragmentInject(parentsPurposeChooseFragment: ParentsPurposeChooseFragment)
    fun interestChooseFragmentInject(kidsInterestChooseFragment: KidsInterestChooseFragment)
    fun kidGoalsFragmentInject(kidsGoalsFragment: KidsGoalsFragment)
    fun tasksFragmentInject(tasksFragment: TasksFragment)
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