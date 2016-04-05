package com.tomergabel.examples.json

import com.tomergabel.examples.json.AST.JsonValue

import scala.util.{Failure, Try}

object Serialization extends BaseSerializers {

  class JsonSerializationException(value: JsonValue, message: String, cause: Option[Throwable] = None)
    extends Exception(message, cause.orNull)

  trait Serializer[T] {
    def serialize(value: T): JsonValue
    def deserialize(value: JsonValue): Try[T]

    protected def error(value: JsonValue, message: String = "Unexpected value") =
      Failure(new JsonSerializationException(value, message))
  }

  def toJSON[T](value: T)(implicit ser: Serializer[T]): JsonValue = ser.serialize(value)
  def fromJSON[T](value: JsonValue)(implicit ser: Serializer[T]): Try[T] = ser.deserialize(value)
}
