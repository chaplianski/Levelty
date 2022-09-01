package com.example.levelty.data.storage.database

import com.example.levelty.data.storage.model.ChildrenItemDTO
import com.example.levelty.data.storage.model.GoalsItemDTO
import com.example.levelty.data.storage.model.UserDTO
import com.example.levelty.data.storage.storage.KidStorage
import javax.inject.Inject

class KidStorageImpl @Inject constructor(): KidStorage {

    override fun getKids(): List<ChildrenItemDTO> {
        val goalList = mutableListOf<GoalsItemDTO>()
        goalList.add(GoalsItemDTO(0, 0, 250, "New phone", "March 12 2022", "Phone", "need approval" ))
        goalList.add(GoalsItemDTO(1, 1, 350, "New bike", "June 22 2022", "Bike", "need approval" ))
        goalList.add(GoalsItemDTO(2, 0, 200, "New trust", "June 10 2022", "Trust", "need approval" ))
        goalList.add(GoalsItemDTO(3, 0, 550, "Upgrade computer", "August 04 2022", "Computer", "need approval" ))
        goalList.add(GoalsItemDTO(4, 1, 150, "New drill", "March 27 2022", "Drill", "need approval" ))

        val goalList0 = mutableListOf<GoalsItemDTO>()
        for (goal in goalList){
            if (goal.childId == 0){
                goalList0.add(goal)
            }
        }
        val goalList1 = mutableListOf<GoalsItemDTO>()
        for (goal in goalList){
            if (goal.childId == 1){
                goalList0.add(goal)
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

    override fun getKid(kidId: Int): ChildrenItemDTO {
        val goalList = mutableListOf<GoalsItemDTO>()
        goalList.add(GoalsItemDTO(0, 0, 250, "New phone", "March 12 2022", "Phone", "need approval" ))
        goalList.add(GoalsItemDTO(0, 1, 350, "New bike", "June 22 2022", "Bike", "need approval" ))
        goalList.add(GoalsItemDTO(0, 0, 200, "New trust", "June 10 2022", "Trust", "need approval" ))
        goalList.add(GoalsItemDTO(0, 0, 550, "Upgrade computer", "August 04 2022", "Computer", "need approval" ))
        goalList.add(GoalsItemDTO(0, 1, 150, "New drill", "March 27 2022", "Drill", "need approval" ))



        val user1 = UserDTO(
            0, "2334", null, false, "22 june 2015", true, "Petrov", "213541234123", "En", "Andrew"
        )

        return ChildrenItemDTO(
            5, 0, user1, goalList
        )
    }
}