package com.tolgahantutar.bexworkfloww.data.network.repositories

import androidx.room.Index
import com.tolgahantutar.bexworkfloww.data.models.getuser.GetUserValue
import com.tolgahantutar.bexworkfloww.data.network.responses.GetUserResponse
import javax.inject.Inject

class GetUserGlobalRepository @Inject constructor(){
var result : GetUserResponse ?= null

  fun setResult(getUserValue: GetUserValue,result: Boolean,description : String?,code : String){
    this.result= GetUserResponse(getUserValue, result, description,code)
  }
  companion object{
    val instance = GetUserGlobalRepository()
  }

}