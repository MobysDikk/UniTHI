#!/bin/bash

ME=`whoami`

IPCS_SEMAS=`ipcs -s | egrep "0x[0-9a-f]+ [0-9]+" | grep $ME | cut -f2 -d" "`

for id in $IPCS_SEMAS; do
  ipcrm -s $id;
done
