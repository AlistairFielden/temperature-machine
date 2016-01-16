package bad.robot.temperature

import java.io.{ByteArrayOutputStream, PrintStream}

import org.specs2.mutable.Specification

import scalaz.{-\/, \/-}

class MeasurementTest extends Specification {

  "Take a measurement" >> {
    val input = new TemperatureReader {
      def read = \/-(List(Temperature(69.9)))
    }
    val output = new TemperatureWriter {
      var temperatures = List[Temperature]()
      def write(data: List[Temperature]): Unit = {
        this.temperatures = data
      }
    }
    Measurement(input, output).run
    output.temperatures must_== List(Temperature(69.9))
  }

  "Fail to take a measurement" >> {
    val input = new TemperatureReader {
      def read = -\/(UnexpectedError("whatever"))
    }
    val output = new TemperatureWriter {
      def write(data: List[Temperature]) = ???
    }
    val log = new ByteArrayOutputStream()
    val error = new PrintStream(log)
    Measurement(input, output, error).run
    log.toString must contain("UnexpectedError(whatever)")
  }

}
