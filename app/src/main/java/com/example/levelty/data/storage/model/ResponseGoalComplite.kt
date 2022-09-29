package com.example.levelty.data.storage.model

//TODO за такое по рукам надо бить) нельзя так делать. каждое поле желательно помечать SerializedName или аналоги так как при обфускации кода это может тебе выстрелить в интересные баги)
data class ResponseGoalComplete(
	val balanceId: Int? = null,
	val amount: Int? = null,
	val childGoal: GoalsItemDTO? = null,
	val childGoalId: Int? = null,
	val id: Int? = null,
	val purchaseDatetime: String? = null
)


