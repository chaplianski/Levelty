package com.example.levelty.di

import android.content.Context
import com.example.levelty.presenter.dialogs.*
import com.example.levelty.presenter.ui.kid.DayKidDetailTasksFragment
import com.example.levelty.presenter.ui.parent.*
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
    fun taskKidDetailFragmentInject(dayKidDetailTasksFragment: DayKidDetailTasksFragment)
    fun newTaskFragmentInject(newTaskFragment: NewTaskFragment)
    fun dayPersonalTasksDialogInject(dayPersonalTasksDialogFragment: DayPersonalTasksDialogFragment)
    fun editTaskFragmentInject(editTaskFragment: EditTaskFragment)


    @Component.Builder
    interface Builder{
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}