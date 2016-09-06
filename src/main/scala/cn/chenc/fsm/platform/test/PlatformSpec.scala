package cn.chenc.fsm.platform.test

import akka.actor.FSM.{CurrentState, SubscribeTransitionCallBack, Transition}
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import cn.chenc.fsm.platform.PlatformMachine
import cn.chenc.fsm.platform.PlatformMachine._
import org.scalatest.{FunSpecLike, MustMatchers}

import scala.collection.mutable

/**
 * Created by ChenC on 09/03/16.
 */
class PlatformSpec extends TestKit(ActorSystem("platform-system")) with MustMatchers with FunSpecLike with ImplicitSender {


  describe("The Coffee Machine") {

    it("should allow setting and getting of price of coffee") {
      val platformMachine = TestActorRef(Props(new PlatformMachine()))

      //expectMsg(CurrentState(platformMachine, Open))
//      platformMachine ! SetInitData(true,false,true,true)
      platformMachine ! SetInitData(true,false,false,true)
      //platformMachine ! Open
      platformMachine ! ToNotFound
      platformMachine ! ToReturnDimension
      platformMachine ! ToPreciseAdvertising
      platformMachine ! ToTop9


      //expectMsg(7)
    }

  }





}
