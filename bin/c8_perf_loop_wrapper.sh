#!/usr/bin/env bash

numberofrecords_var=10000
for i in {20..100}
do
    numberofrecords_var=$(($i*10000))
    bin/c8_driver_perf.sh  -n $numberofrecords_var run:dsx_drv_3_0_2,sprng_data_1_4_2
done
