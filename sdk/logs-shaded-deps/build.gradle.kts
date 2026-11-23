plugins {
  id("otel.java-conventions")

  id("com.gradleup.shadow")
}

// This project is not published, it is bundled into :sdk:logs

description = "Internal use only - shaded dependencies of OpenTelemetry Log SDK"
otelJava.moduleName.set("io.opentelemetry.sdk.logs.internal")
otelJava.requireSuppressWarningsExplanation.set(false)
otelJava.osgiEnabled.set(false)

dependencies {
  implementation("org.jctools:jctools-core")
}

tasks {
  shadowJar {
    minimize()

    exclude("META-INF/maven/**")
    relocate("org.jctools", "io.opentelemetry.sdk.logs.internal.shaded.jctools")
  }

  register<Sync>("extractShadowJar") {
    dependsOn(shadowJar)
    from(zipTree(shadowJar.get().archiveFile))
    // JCTools package annotations otherwise instruct Bnd to export the relocated packages.
    exclude("io/opentelemetry/sdk/logs/internal/shaded/jctools/**/package-info.class")
    into("build/extracted/shadow")
  }
}
