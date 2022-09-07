package com.example.levelty.domain.exceptions

import androidx.annotation.StringRes

class NetworkException(@StringRes val errorMessage: Int): Exception() {
}