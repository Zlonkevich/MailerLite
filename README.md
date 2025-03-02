**Required:**
JRE 21 or higher;
Gradle installed / supported by IDE;

**Optional:**
It's better to install the 'Lombok' plugin in your IDE to avoid error messages, but it will work without it.

**Steps to run:**
You can run all test by running command line command:   ./gradlew :test --tests "suites.JUnitTestSuite" -Dbrowser=chrome 
-Dheadless=false
No additional parameters required.

**Note:**