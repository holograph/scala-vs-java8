package com.tomergabel.examples.json

import scala.util.{Success, Try}


object SerializationExample extends App {

  import AST._
  import Serialization._

  case class Person(name: String, surname: String, address: Seq[String], age: Int)

  implicit val personSerializer = new Serializer[Person] {
    def serialize(value: Person): JsonValue =
      JsonObject(
        JsonField("name", toJSON(value.name)),
        JsonField("surname", toJSON(value.surname)),
        JsonField("address", toJSON(value.address)),
        JsonField("age", toJSON(value.age))
      )

    def deserialize(value: JsonValue): Try[Person] = {
      value match {
        case obj: JsonObject =>
          def field[T](name: String)(implicit ser: Serializer[T]): Try[T] =
            (obj / name).map(fromJSON[T]).getOrElse(error(obj, "Missing field \"" + name + "\""))

          for {
            name <- field[String]("name")
            surname <- field[String]("surname")
            address <- field[Seq[String]]("address")
            age <- field[Int]("age")
          }
          yield Person(name, surname, address, age)

        case _ =>
          error(value)
      }
    }
  }


  val json = """{"name":"Jeffry","surname":"Lebowski","address":["609 Venezia Avenue","Venice"],"age":47}"""
  val dude = Person("Jeffry", "Lebowski", Seq("609 Venezia Avenue", "Venice"), 47)
  val obj =
    JsonObject(
      JsonField("name", JsonString("Jeffry")),
      JsonField("surname", JsonString("Lebowski")),
      JsonField("address", JsonArray(JsonString("609 Venezia Avenue"), JsonString("Venice"))),
      JsonField("age", JsonNumber(47))
    )


  val dudeSer = toJSON(dude)
  val objDeser = fromJSON[Person](obj).get
  val printed = Printer.compact(dudeSer)

  assert(dudeSer == obj)
  assert(objDeser == dude)
  assert(printed == json)
}
