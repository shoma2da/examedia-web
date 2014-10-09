package controllers

import play.api._
import play.api.mvc._

object Picture extends Controller {

    def pictures(from:Option[String], to:Option[String]) = Action {
        (from, to) match {
            case (Some(fromString), Some(toString)) => Ok(views.html.index("to is " + toString + ". from is " + fromString + "."))
            case _                                  => Ok(views.html.index("You shoud input to and from strings"))
        }
    }

}
