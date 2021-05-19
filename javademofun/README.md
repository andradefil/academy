# Kotlin Java App Demo for fun

Demo created for fun following a minimal setup to create a java kotlin app, with Gradle build system

Two setup is available in `build.gradle.kts` file
chose the setup by uncomment section, SPRING_BOOT section or DEFAULT section

The DEFAULT app is only a single app prepared for console and test running with JUNIT 5.

### Try out

Build with gradle
```bash
$ ./gradlew build
```

Generating a jar and run a hello world from Java Swing
```bash
cd app/build/classes/java/main \
&& jar cvfe myjar.jar HelloWorldSwing *.class \
&& java -jar myjar.jar
```

Build with docker and display a graceful Hello World!
```bash
 docker build . -t demojavafun && docker run demojavafun
```