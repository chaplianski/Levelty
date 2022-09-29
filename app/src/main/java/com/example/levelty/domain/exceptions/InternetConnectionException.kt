package com.example.levelty.domain.exceptions

import androidx.annotation.StringRes

//TODO я бы использовал тут или enum or sealed для того чтобы сообщение для пользователя брать на этапе вью. домейн слой по минимуму желательно связывать с андройд ресурсами
class InternetConnectionException constructor(@StringRes val errorMessage: Int): Exception()  {
}