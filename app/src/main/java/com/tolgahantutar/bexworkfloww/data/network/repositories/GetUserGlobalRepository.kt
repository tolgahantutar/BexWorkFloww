package com.tolgahantutar.bexworkfloww.data.network.repositories

import com.tolgahantutar.bexworkfloww.data.models.getuser.GetUserValue
import com.tolgahantutar.bexworkfloww.data.network.responses.GetUserResponse
import javax.inject.Inject

class GetUserGlobalRepository @Inject constructor(){

  var apiKey : String ? =null
  fun setKey(apikey : String){
    apiKey=apikey
  }

  companion object{
    val instance = GetUserGlobalRepository()
  }

}