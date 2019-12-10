#!/bin/bash

rmiregistry &

sleep 2

java DaytimeServer &

sleep 2

java DaytimeClient localhost Boomerang


