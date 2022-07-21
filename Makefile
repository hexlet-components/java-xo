clean:
	./gradlew clean

build:
	./gradlew clean build

lint:
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

install:
	./gradlew clean install

run:
	./build/install/java-xo/bin/java-xo

check-updates:
	./gradlew dependencyUpdates

.PHONY: build
