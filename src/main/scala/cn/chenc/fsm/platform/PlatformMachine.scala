package cn.chenc.fsm.platform


import akka.actor.FSM
import PlatformMachine._
import cn.chenc.fsm.platform1

import scala.collection.mutable

/**
  * Created by ChenC on 2016/9/5.
  */

object PlatformMachine{
  sealed trait PlatformState
  //定义6个State  open 404  返回尺寸  精准投放 Top9   HoldOn
  case object Open extends PlatformState
  case object HoldOn extends PlatformState
  case object NotFound extends PlatformState
  case object ReturnDimension extends PlatformState
  case object PreciseAdvertising extends PlatformState
  case object Top9 extends PlatformState
  // state data container
  case class PlatformData(isValid: Boolean, isFirst: Boolean, isBusinessLine: Boolean,district:Boolean)
  //case class PlatformData(map : mutable.HashMap[String, Boolean])

  //定义交互消息
  sealed trait UserMessage
  case object ToHoldOn extends  UserMessage
  case object ToNotFound extends  UserMessage
  case object ToReturnDimension extends UserMessage
  case object ToPreciseAdvertising extends UserMessage
  case object ToTop9 extends UserMessage

  //case class SetInitData(map : mutable.HashMap[String, Boolean]) extends UserMessage
  case class SetInitData(isValid: Boolean, isFirst: Boolean, isBusinessLine: Boolean,district:Boolean) extends UserMessage

}


class PlatformMachine extends FSM[PlatformState,PlatformData]{

//  startWith(Open, PlatformData(new mutable.HashMap[String,Boolean](){
//     ("isValid",false)
//     ("isFirst",false)
//     ("isBusinessLine",false)
//     ("district",false)
//  }))
  startWith(Open, PlatformData(false,false,false,false))

  when(Open){


    case Event(SetInitData(isValid_,isFirst_,isBusinessLine_,district_), _) =>{
      println(s"SetInitData:$isValid_ , $isFirst_ ,$isBusinessLine_ ,$district_  ")
      //println(s"stateData:$stateData")
      stay using stateData.copy(isValid = isValid_,isFirst=isFirst_,isBusinessLine=isBusinessLine_,district=district_)

    }

    case Event(ToNotFound, PlatformData(isValid,_,_,_))   => {
      println(s"isValid:$isValid")
      if (isValid equals  false)  {
        println("goto NotFound!404")
        goto(NotFound)
      }else{
        println("goto HoldOn!  step1")
        goto(HoldOn)
      }
    }
  }

  when(HoldOn){

    case Event(ToReturnDimension, PlatformData(_,isFirst,_,_))   => {
      println(s"isFirst:$isFirst")
      //是否第一次请求 /是  返回广告位大小尺寸数据
      if (isFirst equals  true){
        println("goto ReturnDimension! 返回尺寸 ")
        goto(ReturnDimension)
      }else{
        println("goto HoldOn! step2")
        goto(HoldOn)
      }
    }

    case Event(ToPreciseAdvertising, PlatformData(_,_,isBusinessLine,_))   => {
      println(s"isBusinessLine:$isBusinessLine")
      //是否业务线广告位 /是  返回精准投放
      if (isBusinessLine equals  false){
        println("goto PreciseAdvertising! 精准投放 ")
        goto(PreciseAdvertising)
      }else{
        println("goto HoldOn! step3")
        goto(HoldOn)
      }
    }

    case Event(ToTop9, PlatformData(_,_,_,district))   => {
      println(s"district:$district")
      //是否有地域 /是  返回9素材
      if (district equals  true){
        println("goto Top9! 返回9素材 ")
        goto(Top9)
      }else{
        println("goto NotFound!404")
        goto(HoldOn)
      }
    }
  }

  when(ReturnDimension) {
    case _ =>{
      println("尺寸 100 * 100 px ")
      stay()
    }
  }

  when(PreciseAdvertising) {
    case _ =>{
      println("精准投放！ ")
      stay()
    }
  }

  when(Top9) {
    case _ =>{
      println("Top9！ ")
      stay()
    }
  }


//  whenUnhandled {
//    case _ => {
//      goto(Open)
//    }
//  }

  onTransition {
    case Open -> NotFound => println("-------------------------onTransition : from Open to NotFound ! ")
    case Open -> HoldOn   => println("-------------------------onTransition : from Open to HoldOn ! ")
    case HoldOn -> ReturnDimension   => println("-------------------------onTransition : from HoldOn to ReturnDimension ! ")
    case HoldOn -> PreciseAdvertising   => println("-------------------------onTransition : from HoldOn to PreciseAdvertising ! ")
    case HoldOn -> Top9   => println("-------------------------onTransition : from HoldOn to Top9 ! ")
  }

}
