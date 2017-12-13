enablePlugins(org.nlogo.build.NetLogoExtension)

netLogoExtName      := "dbi_agents"

netLogoClassManager := "primitive.ExtensionClassManager"

scalaVersion           := "2.11.7"

scalaSource in Compile := baseDirectory.value / "src"

unmanagedSourceDirectories in Compile += baseDirectory.value / "kobdig/src/main/java"

scalacOptions          ++= Seq("-deprecation", "-unchecked", "-Xfatal-warnings", "-encoding", "us-ascii")

netLogoVersion := "6.0.2"

fork in run := true

libraryDependencies += "com.novocode" % "junit-interface" % "0.11"

