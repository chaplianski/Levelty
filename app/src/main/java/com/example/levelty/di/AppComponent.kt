package com.example.levelty.di

import android.content.Context
import com.example.levelty.presenter.dialogs.*
import com.example.levelty.presenter.ui.kid.KidDayTasksFragment
import com.example.levelty.presenter.ui.parent.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun profileFragmentInject(parentProfileFragment: ParentProfileFragment)
    fun dayPersonalTasksFragmentInject(parentDayKidTasksFragment: ParentDayKidTasksFragment)
    fun repeatChooseFragmentInject(repeatChooseFragment: RepeatChooseFragment)
    fun categoryChooseFragmentInject(categoryChooseFragment: CategoryChooseFragment)
    fun parentPurposeChooseFragmentInject(parentsPurposeChooseFragment: ParentsPurposeChooseFragment)
    fun interestChooseFragmentInject(kidsInterestChooseFragment: KidsInterestChooseFragment)
    fun kidGoalsFragmentInject(kidsGoalsFragment: KidsGoalsFragment)
    fun tasksFragmentInject(tasksFragment: TasksFragment)
    fun taskKidDetailFragmentInject(kidDayTasksFragment: KidDayTasksFragment)
    fun newTaskFragmentInject(newTaskFragment: NewTaskFragment)
    fun dayPersonalTasksDialogInject(dayPersonalTasksDialogFragment: DayPersonalTasksDialogFragment)
    fun editTaskFragmentInject(editTaskFragment: EditTaskFragment)
    fun categoryFragmentInject(categoryFragment: CategoryFragment)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}