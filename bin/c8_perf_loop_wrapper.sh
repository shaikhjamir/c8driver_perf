#!/usr/bin/env bash

# iterate for 20 times over 10,000 to 200,000 records
for j in {1..20}
do
  for i in {1..20}
  do
      numberofrecords_var=$(($i*10000))
      bin/c8_driver_perf.sh  -n $numberofrecords_var run:dsx_drv_3_0_2,sprng_data_1_4_2
  done
done
