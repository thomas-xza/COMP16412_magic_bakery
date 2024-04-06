# Remove previously compiled code
rm ./bin/*.class ./bin/bakery/*.class ./bin/util/*.class ./bin/test/*/*.class

# Compile the game
javac src/main/*.java src/main/bakery/*.java src/main/util/*.java -d ./bin/

##  functional tests
javac -cp .:junit-platform-console-standalone.jar --source-path ./src/main/ ./src/test/test/functional/*.java -d ./bin/

##  structural tests
# javac -cp .:junit-platform-console-standalone.jar --source-path ./src/main/ ./src/test/test/structural/*.java -d ./bin/

##  javadoc tests
#javac -cp .:junit-platform-console-standalone.jar --source-path ./src/main/ ./src/test/test/javadoc/*.java -d ./bin/

# Run the tests
mv /tmp/tests /tmp/tests_prev
java -jar junit-platform-console-standalone.jar --class-path ./bin/ --scan-class-path --fail-if-no-tests 2>&1 > /tmp/tests
grep -v "Thanks for using JUnit" /tmp/tests | sed '/^Failures (/q' | less -R
