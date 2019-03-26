#!/bin/bash
npm run dev
mvn compile quarkus:dev &
npm run watch &
