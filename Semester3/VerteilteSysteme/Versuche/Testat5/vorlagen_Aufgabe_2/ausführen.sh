#!/bin/bash

set lars/UniTHI/Semester3/VerteilteSysteme/Versuche/testat5 = bin 

rmiregistry

java -cp bin DaytimeServer

java -Djava.security.manager -Djava-security.policy=policy.txt -cp bin DaytimeClient localhost Hallo


