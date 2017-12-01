package at.fhj.swengb.common

import java.nio.file.{Files, Path, Paths}

import at.fhj.utils.Csv
import org.scalatest.WordSpecLike

/**
  * 
  */
class GithubSpec extends WordSpecLike {

  private val basePath: Path = Paths.get(".")

  // uncomment if you want to know what is the base path
  // println(basePath.toAbsolutePath)

  private val githubCsv: Path = basePath.resolve("src/main/resources/at/fhj/swengb/common/github.csv")

  println(githubCsv.toAbsolutePath.toString)

  // read and parse csv to a type
  val csv: Csv = Csv(githubCsv)

  "github.csv" should {
    "exist" in assert(Files.exists(githubCsv))
  }
  
  "github.csv: account is defined" should {
    "be set for Gregor Apetauer : apetauer16" in assert(csv.entries("apetauer16").nonEmpty)
    "be set for Jakob Arneitz : arneitzj15" in assert(csv.entries("arneitzj15").nonEmpty)
    "be set for Michael Brunner : brunnemi16" in assert(csv.entries("brunnemi16").nonEmpty)
    "be set for Lukas David : davidluk15" in assert(csv.entries("davidluk15").nonEmpty)
    "be set for Michael Derler : derlermi16" in assert(csv.entries("derlermi16").nonEmpty)
    "be set for Gregor Fernbach : fernbach16" in assert(csv.entries("fernbach16").nonEmpty)
    "be set for Florian Gartner : gartnerf15" in assert(csv.entries("gartnerf15").nonEmpty)
    "be set for Miriam Grainer : grainerm15" in assert(csv.entries("grainerm15").nonEmpty)
    "be set for Stefan Heider : heiderst16" in assert(csv.entries("heiderst16").nonEmpty)
    "be set for Alexander Horina : horinaal15" in assert(csv.entries("horinaal15").nonEmpty)
    "be set for Martin Hutter : hutterma14" in assert(csv.entries("hutterma14").nonEmpty)
    "be set for Alexander Hödl : hoedlale16" in assert(csv.entries("hoedlale16").nonEmpty)
    "be set for Susanne Hütter : huetters16" in assert(csv.entries("huetters16").nonEmpty)
    "be set for Stefan Krasser : krassers16" in assert(csv.entries("krassers16").nonEmpty)
    "be set for Eliska Krnavkova : krnavkov16" in assert(csv.entries("krnavkov16").nonEmpty)
    "be set for Christian Lach : lachchri16" in assert(csv.entries("lachchri16").nonEmpty)
    "be set for Gerhard Lambrecht : lambrech16" in assert(csv.entries("lambrech16").nonEmpty)
    "be set for Verena Leifert : leifertv15" in assert(csv.entries("leifertv15").nonEmpty)
    "be set for Christoph Mali : malichri16" in assert(csv.entries("malichri16").nonEmpty)
    "be set for Nadine Neumann : neumannn16" in assert(csv.entries("neumannn16").nonEmpty)
    "be set for Thomas Ortner : ortnerth16" in assert(csv.entries("ortnerth16").nonEmpty)
    "be set for Dominik Pagger : paggerdo16" in assert(csv.entries("paggerdo16").nonEmpty)
    "be set for Elisabeth Panholzer : panholze15" in assert(csv.entries("panholze15").nonEmpty)
    "be set for Mathias Pützl : puetzlma16" in assert(csv.entries("puetzlma16").nonEmpty)
    "be set for Ardian Qerimi : qerimiar16" in assert(csv.entries("qerimiar16").nonEmpty)
    "be set for Patrik Rauchenschwandtner : rauchens16" in assert(csv.entries("rauchens16").nonEmpty)
    "be set for Thurid Reichel : reichelt16" in assert(csv.entries("reichelt16").nonEmpty)
    "be set for Thomas Rinnhofer : rinnhofe15" in assert(csv.entries("rinnhofe15").nonEmpty)
    "be set for Benjamin Ruggenthaler : ruggenth15" in assert(csv.entries("ruggenth15").nonEmpty)
    "be set for Georg Schmitzer : schmitze16" in assert(csv.entries("schmitze16").nonEmpty)
    "be set for Bob Smith : smithbob17" in assert(csv.entries("smithbob17").nonEmpty)
    "be set for Nikolaus Spieß : spiessni15" in assert(csv.entries("spiessni15").nonEmpty)
    "be set for Andreas Staudacher : staudach16" in assert(csv.entries("staudach16").nonEmpty)
    "be set for Maximilian Steiner : steinerm15" in assert(csv.entries("steinerm15").nonEmpty)
    "be set for Franz Wachter : wachterf16" in assert(csv.entries("wachterf16").nonEmpty)
    "be set for Barbara Wallner : wallnerb15" in assert(csv.entries("wallnerb15").nonEmpty)
    "be set for Sebastian Weiland : weilands16" in assert(csv.entries("weilands16").nonEmpty)
  }


}
