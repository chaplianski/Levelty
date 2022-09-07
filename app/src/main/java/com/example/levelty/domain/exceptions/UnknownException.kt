package com.example.levelty.domain.exceptions

import androidx.annotation.StringRes

class UnknownException(@StringRes val errorMessage: Int): Exception() {
}