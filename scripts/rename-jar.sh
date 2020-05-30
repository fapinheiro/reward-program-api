#!/bin/sh
for f in *.jar; do
    mv -- "$f" "webapp.jar"
done
