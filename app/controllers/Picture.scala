package controllers

import play.api._
import play.api.mvc._

object Picture extends Controller {

    def pictures(from:Option[String], to:Option[String]) = Action {
        (from, to) match {
            /* 明治神宮前→表参道の場合のみに対応 */
            case (Some(fromString), Some(toString)) if fromString == "明治神宮前" && toString == "表参道" => { //FIXME 駅名が固定！
                //場所を決め打ち
                val meijiJinguumaeLocation = (35.669176, 139.704033)
                val omotesandoLocation = (35.665500, 139.712394)

                //緯度経度の差
                val latitudeDiff = meijiJinguumaeLocation._1 - omotesandoLocation._1
                val longitudeDiff = meijiJinguumaeLocation._2 - omotesandoLocation._2

                //10分割
                val divisionValue = 15
                val locationUrlList = (0 to divisionValue).map{ index =>
                    (meijiJinguumaeLocation._1 - (latitudeDiff / divisionValue * index),
                     meijiJinguumaeLocation._2 - (longitudeDiff / divisionValue * index))
                }.map{ location =>
                    //方向は45度(南東)を決め打ち
                    s"http://maps.googleapis.com/maps/api/streetview?size=600x300&location=${location}&heading=45&pitch=-0.76&sensor=false"
                }

                //JSON化
                import play.api.libs.json._
                Ok(Json.obj("urlList" -> Json.toJson(locationUrlList)))
            }

            /* パラメータが想定外 */
            case _ => BadRequest("You should input 'from' and 'to' parameters. ※現在対応しているのはfrom=明治神宮前とto=表参道のみ")
        }
    }

}
