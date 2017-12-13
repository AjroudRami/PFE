resolvers += Resolver.url(
  "NetLogo-JVM",
  url("http://dl.bintray.com/content/netlogo/NetLogo-JVM"))(
  Resolver.ivyStylePatterns)
resolvers += Resolver.mavenLocal

addSbtPlugin("org.nlogo" % "netlogo-extension-plugin" % "3.0")