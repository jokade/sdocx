//     Project: sdocx
//      Module: tests
// Description: Runs all E2E tests

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package tests

object Main extends App {
  lazy val tests = Seq(Test01)

  println("Running all E2E-Tests")

  tests.foreach{ t =>
    print(s"  ${t.name} ... ")
    t.run()
    println("done")
  }
}
