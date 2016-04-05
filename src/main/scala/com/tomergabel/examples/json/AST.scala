package com.tomergabel.examples.json

/**
  * Created by tomerga on 05/04/2016.
  */
object AST {
  sealed trait JsonValue
  case class JsonString(string: String) extends JsonValue
  case class JsonNumber(repr: BigDecimal) extends JsonValue
  case class JsonBoolean(repr: Boolean) extends JsonValue
  case object JsonNull extends JsonValue
  case class JsonArray(elements: JsonValue*) extends JsonValue
  case class JsonObject(fields: JsonField*) extends JsonValue {
    def /(name: String): Option[JsonValue] = fields.find(_.name == name).map(_.value)
  }

  case class JsonField(name: String, value: JsonValue)
}
