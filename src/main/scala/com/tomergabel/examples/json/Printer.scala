package com.tomergabel.examples.json

import com.tomergabel.examples.json.AST._

/**
  * Created by tomerga on 05/04/2016.
  */
object Printer {
  private def renderField(field: JsonField): String =
    "\"" + field.name + "\":" + compact(field.value)

  def compact(ast: JsonValue): String =
    ast match {
      case JsonNull => "null"
      case JsonNumber(repr) => repr.toString
      case JsonBoolean(repr) => repr.toString
      case JsonArray(elements @ _*) => elements.map(compact).mkString("[", ",", "]")
      case JsonObject(fields @ _*) => fields.map(renderField).mkString("{", ",", "}")
      case JsonString(string) => "\"" + string + "\""
    }
}
