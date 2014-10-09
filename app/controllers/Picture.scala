package controllers

import play.api._
import play.api.mvc._

object Picture extends Controller {

    def pictures(from:Option[String], to:Option[String]) = Action {
        (from, to) match {
            case (Some(fromString), Some(toString)) => {
                Ok(s"OK! Your input '${fromString}' as 'from', '${toString}' as 'to'")
            }
            case _ => BadRequest("You should input 'from' and 'to' parameters.")
        }
    }

}
