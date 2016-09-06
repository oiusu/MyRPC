package cn.chenc.fsm.platform.test

import akka.actor.{ActorRef, Props, ActorSystem}
import cn.chenc.fsm.platform.PlatformMachine
import cn.chenc.fsm.platform.PlatformMachine._

/**
  * Created by ChenC on 2016/9/6.
  */
object PlatformTest  extends  App{

  val system = ActorSystem()
  private val platformMachine: ActorRef = system.actorOf(Props[PlatformMachine],"plantformTest")

  platformMachine ! SetInitData(true,false,false,true)
  //platformMachine ! Open
  platformMachine ! ToNotFound
  platformMachine ! ToReturnDimension
  platformMachine ! ToPreciseAdvertising
  platformMachine ! ToTop9

  system.awaitTermination()

}
