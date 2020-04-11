#!/usr/bin/env bash

# change to script directory
cd "${0%/*}"

cd ..
./gradlew shadowJar

cd text-ui-test

rm -rf logs/
rm -rf historyflashcards/

java -jar -ea $(find ../build/libs/ -mindepth 1 -print -quit) < input.txt > ACTUAL.TXT

diff EXPECTED.TXT ACTUAL.TXT
if [ $? -eq 0 ]
then
    echo "Test 1 passed!"
else
    echo "Test 1 failed!"
    exit 1
fi

java -jar -ea $(find ../build/libs/ -mindepth 1 -print -quit) < input2.txt > ACTUAL2.TXT

diff EXPECTED2.TXT ACTUAL2.TXT
if [ $? -eq 0 ]
then
    echo "Test 2 passed!"
    exit 0
else
    echo "Test 2 failed!"
    exit 1
fi
