#!/bin/bash
mvn clean test -Dtest=Runner.RunSuite -Dsurefire.useFile=false
