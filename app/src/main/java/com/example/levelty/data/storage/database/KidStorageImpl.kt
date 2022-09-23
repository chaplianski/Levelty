package com.example.levelty.data.storage.database

import com.example.levelty.data.storage.model.*
import com.example.levelty.data.storage.storage.KidStorage
import javax.inject.Inject

class KidStorageImpl @Inject constructor(): KidStorage {

    override fun getKids(): List<ChildrenItemDTO> {
        val goalList = mutableListOf<GoalsItemDTO>()
        goalList.add(GoalsItemDTO(0, 150, "150", "0", 0, "New version Iphone", "normal" ))
        goalList.add(GoalsItemDTO(0, 250, "", "New phone", 0, "Phone", "declined" ))
        goalList.add(GoalsItemDTO(0, 400, "", "New bike", 0, "Bike", "waiting_for_approval" ))
        goalList.add(GoalsItemDTO(0, 8000, "", "New trust", 0, "Car", "completed" ))
        goalList.add(GoalsItemDTO(0, 550, "", "Upgrade computer", 0, "Computer", "locked" ))
        goalList.add(GoalsItemDTO(0, 120, "", "New drill", 0, "Drill", "choose_goal_status" ))

        val goalList0 = mutableListOf<GoalsItemDTO>()
        for (goal in goalList){
            if (goal.childId == 0){
                goalList0.add(goal)
            }
        }
        val goalList1 = mutableListOf<GoalsItemDTO>()
        for (goal in goalList){
            if (goal.childId == 1){
                goalList1.add(goal)
            }
        }

        val kidList = mutableListOf<ChildrenItemDTO>()
        val user1 = UserDTO(
            0, "2334", null, false, "22 june 2015", true, "Petrov", "213541234123", "En", "Andrew"
        )
        val user2 = UserDTO(
            0, "2334", null, false, "25 december 2015", true, "Kelly", "213541234123", "En", "John"
        )
        kidList.add(ChildrenItemDTO(5, 0, user1, goalList0))
        kidList.add(ChildrenItemDTO(1, 1, user2, goalList1))
        return kidList.toList()
    }

    override fun getChild(): ChildDTO {
        val balance = BalanceDTO(233,0)
        val category1 = CategoryDTO(null,"",0,"School")
        val category2 = CategoryDTO(null,"",1,"Science")
        val category3 = CategoryDTO(null,"",2,"School")
        val category4 = CategoryDTO(null,"",3,"Physical growth")
        val choresList = mutableListOf<ChoresItemDTO>()
        choresList.add(ChoresItemDTO("09-13-2022",0,"09-27-2022","Any comment", 0, "pending"))
        choresList.add(ChoresItemDTO("09-14-2022",0,"09-27-2022","Any comment", 1, "done"))
        choresList.add(ChoresItemDTO("09-14-2022",0,"09-27-2022","Any comment", 2, "pending"))
        val choresList2 = mutableListOf<ChoresItemDTO>()
        choresList2.add(ChoresItemDTO("09-13-2022",0,"09-27-2022","Any comment", 0, "pending"))
        choresList2.add(ChoresItemDTO("09-14-2022",0,"09-27-2022","Any comment", 1, "done"))
        choresList2.add(ChoresItemDTO("09-14-2022",0,"09-27-2022","Any comment", 2, "pending"))
        val interestsList = mutableListOf<ChildInterestsItemDTO>()
        interestsList.add(ChildInterestsItemDTO("0","Happy"))
        interestsList.add(ChildInterestsItemDTO("1","Second"))
        val assignedTasks = mutableListOf<AssignedTasksItemDTO>()
        assignedTasks.add(AssignedTasksItemDTO(15,"10-10-2022","dsdsdfsdfsdf","","09-09-2022",interestsList,choresList,"Walk Dog", 0,0,3,0,category1,true,"10-09-2022",0,"pending"))
        assignedTasks.add(AssignedTasksItemDTO(25,"11-11-2022","erwerwerwer","","09-11-2022",interestsList,choresList2,"Go to school", 1,0,1,1,category2,true,"11-09-2022",0,"pending"))
        assignedTasks.add(AssignedTasksItemDTO(55,"11-11-2022","xcbvcbxcbcc","","09-11-2022",interestsList,choresList,"watch TV", 1,0,1,2,category3,true,"11-09-2022",0,"pending"))
        val interests = mutableListOf<InterestsItemDTO>()
        interests.add(InterestsItemDTO(0,"Phone"))
        interests.add(InterestsItemDTO(1,"Computer"))
        interests.add(InterestsItemDTO(2,"Cat"))
        val parents = mutableListOf<ParentsItemDTO>()
        parents.add(ParentsItemDTO(0))
        parents.add(ParentsItemDTO(1))
        val user = UserDTO(0,"23432",null,true,"05-06-2010",true,"Bond","+52345234523","En","James")
        val goals = mutableListOf<GoalsItemDTO>()
        goals.add(GoalsItemDTO(0, 400, "", "New bike", 0, "Bike", "waiting_for_approval" ))
        goals.add(GoalsItemDTO(0, 8000, "", "New trust", 0, "Car", "completed" ))
        goals.add(GoalsItemDTO(0, 550, "", "Upgrade computer", 0, "Computer", "locked" ))

        return ChildDTO(
            2,3, balance, 14, 0, assignedTasks, 10, 122,interests,user,parents,goals
        )
    }


    override fun getKid(kidId: Int): ChildrenItemDTO {
        val goalList = mutableListOf<GoalsItemDTO>()
        goalList.add(GoalsItemDTO(0, 150, "150", "0", 0, "New version Iphone", "normal" ))
        goalList.add(GoalsItemDTO(0, 250, "", "New phone", 0, "Phone", "declined" ))
        goalList.add(GoalsItemDTO(0, 400, "", "New bike", 0, "Bike", "waiting_for_approval" ))
        goalList.add(GoalsItemDTO(0, 8000, "", "New trust", 0, "Car", "completed" ))
        goalList.add(GoalsItemDTO(0, 550, "", "Upgrade computer", 0, "Computer", "locked" ))
        goalList.add(GoalsItemDTO(0, 120, "", "New drill", 0, "Drill", "choose_goal_status" ))



        val user1 = UserDTO(
            0, "2334", null, false, "22 june 2015", true, "Petrov", "213541234123", "En", "Andrew"
        )

        return ChildrenItemDTO(
            5, 0, user1, goalList
        )
    }
}