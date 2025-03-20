#!/bin/sh
mvn clean test -Dskip.testng=false -Dskip.cucumber=true -Dskip.junit=true -DsuiteXmlFile=src/test/resources/testng.xml 