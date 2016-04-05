package com.tomergabel.examples.json

import com.tomergabel.examples.json.AST._
import com.tomergabel.examples.json.Serialization.Serializer

import scala.collection.generic.CanBuildFrom
import scala.util.{Success, Try}

/**
  * Created by tomerga on 05/04/2016.
  */
trait BaseSerializers {

  implicit val stringSerializer = new Serializer[String] {
    def serialize(value: String): JsonValue = JsonString(value)

    def deserialize(value: JsonValue): Try[String] =
      value match {
        case JsonString(string) => Success(string)
        case _ => error(value)
      }
  }

  implicit val intSerializer = new Serializer[Int] {
    def serialize(value: Int): JsonValue = JsonNumber(BigDecimal(value))

    def deserialize(value: JsonValue): Try[Int] =
      value match {
        case JsonNumber(n) if n.isValidInt => Try(n.toInt)
        case _ => error(value)
      }
  }

  implicit val doubleSerializer = new Serializer[Double] {
    def serialize(value: Double): JsonValue = JsonNumber(BigDecimal(value))

    def deserialize(value: JsonValue): Try[Double] =
      value match {
        case JsonNumber(n) if n.isExactDouble => Try(n.toDouble)
        case _ => error(value)
      }
  }

  implicit val booleanSerializer = new Serializer[Boolean] {
    def serialize(value: Boolean): JsonValue = JsonBoolean(value)

    def deserialize(value: JsonValue): Try[Boolean] =
      value match {
        case JsonBoolean(bool) => Success(bool)
        case _ => error(value)
      }
  }

  implicit def optionSerializer[T](implicit ser: Serializer[T]) = new Serializer[Option[T]] {
    def serialize(value: Option[T]): JsonValue =
      value.map(ser.serialize).getOrElse(JsonNull)

    def deserialize(value: JsonValue): Try[Option[T]] =
      value match {
        case JsonNull => Success(None)
        case _ => ser.deserialize(value).map(Option.apply)
      }
  }

  implicit def seqSerializer[T](implicit ser: Serializer[T]) = new Serializer[Seq[T]] {
    def serialize(value: Seq[T]): JsonValue = JsonArray(value map ser.serialize :_*)

    def deserialize(value: JsonValue): Try[Seq[T]] =
      value match {
        case JsonArray(elements @ _*) => Try(elements.map(ser.deserialize).map(_.get))
        case _ => error(value)
      }
  }
}
