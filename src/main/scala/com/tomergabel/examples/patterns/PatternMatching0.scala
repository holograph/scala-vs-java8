package com.tomergabel.examples.patterns


object PatternMatching0 extends App {

  case class HttpRequest(method: String,
                         path: String,
                         headers: Map[String, String] = Map.empty)

  case class HttpResponse(status: Int, body: Array[Byte])

  def execute(req: HttpRequest): HttpResponse = {
    // Mock implementation, for example's sake
    val index = """<html><body>Hi!</body></html>"""
    val json = """{"response":"ok"}"""

    req match {
      case HttpRequest("GET", "/", _) =>
        HttpResponse(200, index.getBytes)

      case HttpRequest("GET", _, _) =>
        HttpResponse(404, Array.empty)

      case HttpRequest("POST", _, headers) if headers.get("Content-type").contains("application/json") =>
        HttpResponse(200, json.getBytes)

      case HttpRequest("POST", _, _) =>
        HttpResponse(415, Array.empty)

      case _ =>
        HttpResponse(405, Array.empty)
    }
  }

  def render(resp: HttpResponse) = s"[${resp.status}] ${new String(resp.body)}"
  println("Index: " + render(execute(HttpRequest("GET", "/"))))
  println("Invalid GET: " + render(execute(HttpRequest("GET", "/test"))))
  println("Valid POST: " + render(execute(HttpRequest("POST", "/", headers = Map("Content-type" -> "application/json")))))
  println("Invalid POST: " + render(execute(HttpRequest("POST", "/", headers = Map("Content-type" -> "application/xml")))))
  println("PUT: " + render(execute(HttpRequest("PUT", "/"))))
}
