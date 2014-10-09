package controllers

import play.api._
import play.api.mvc._

object Picture extends Controller {

    def pictures(from:Option[String], to:Option[String]) = Action {
        (from, to) match {
            case (Some(fromString), Some(toString)) if fromString == "明治神宮前" && toString == "表参道" => { //FIXME 駅名が固定！
                Ok(s"OK! You input '${fromString}' as 'from', '${toString}' as 'to'")
            }
            case _ => BadRequest("You should input 'from' and 'to' parameters.")
        }
    }

}
