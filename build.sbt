

name := "Scalacache ZIO effect"

version := "0.1"

scalaVersion := "2.13.5"

scalacOptions := Seq(
  "-feature",
  "-deprecation",
  "-explaintypes",
  "-unchecked",
  "-encoding",
  "UTF-8",
  "-language:higherKinds",
  "-language:existentials"
)

libraryDependencies += "dev.zio"  %% "zio" % "1.0.5"

libraryDependencies += "com.github.cb372" %% "scalacache-core" % "0.28.0"

addCompilerPlugin(("org.typelevel" % "kind-projector"      % "0.11.3").cross(CrossVersion.full))

