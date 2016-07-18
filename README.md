## Project
c8driver_perf is a Cassandra Driver Performance Testing tool. This tool compares the performance of Cassandra Drivers. It also provides a sample application in case you are trying to learn how a particular Cassandra Driver works and does provide a Hello World kind of application for these drivers.

### Note
This is not a tool to test Cassandra Performance. This is designed to only test different Cassandra drivers and compare their performance which help in choosing the right Driver based on the requirements.

### Pre requisites
1. JDK 1.8+
2. maven
3. Docker


## Get Started

### Build

*   % git clone https://github.com/shaikhjamir/c8_driver_perf
*   % cd c8_driver_perf
*   % mvn clean package

### Run
*   % bin/c8_driver_perf.sh  -n 1000 run:dsx_drv_3_0_2,sprng_data_1_4_2

Output: data/c8_performance_runs.dat

This will run for 1000 records i.e. Partition Keys with 4 clustering columns for each Partition Key with default 10 Threads using both drivers dsx_drv_3_0_2 and sprng_data_1_4_2 on after another.


For help on different parameters:
*   % bin/c8_driver_perf.sh help

```bash

usage: c8_driver_perf.sh [options] [commands]
options:
  -n | --noofrecords [ long ]            : Total no of records(partition keys to be written, there are 4 cluserting columns for every partition key); default: 1000
  -t | --noofthreads [ integer ]         : no of threads to run; default: 10
  -d | --debug [ true | false ]          : debug ; default: false
  -h | --help                            : help message

commands:
  run[:dsx_drv_3_0_2,sprng_data_1_4_2]     : run the performance test for driver dsx_drv_3_0_2,sprng_data_1_4_2
  help                                     : help message

results: will be written to base_dir/data/c8_performance_runs.dat ( if debug is true results are on console)

e.g
bin/c8_driver_perf.sh  -n 1000 run:dsx_drv_3_0_2,sprng_data_1_4_2

```

## Project Structure

### c8driver_perf
This is top most project. This will just build the child projects under modules/*

Since different drivers have different dependencies this top level project does not have any dependencies whatsoever.
Only task of this is to build the other projects.

* bin : This directory contains the shell scripts to execute the performance test and a .cql file used to setup keypsace and create table.

* data : This is the folder where result output file will be created on successful execution. Name of the file is "c8_performance_runs.dat"

* modules : This contains the child projects viz. a common project and different driver projects.

### c8driver_perf_test_common
This is the common Project and contains:
* Common interface which Driver Projects should follow.
* Basic execution logic of creating Threads calling different functionalities viz INSERT READ UPDATE and DELETE of driver project.
* This contains the Clustering Columns which will be created in file EventTypes.java

### dsx_drv_3_0_2
* Datastax Native Driver 3.0.2 :
http://docs.datastax.com/en/latest-java-driver/java-driver/whatsNew2.html?local=true&nav=toc


### sprng_data_1_4_2
* Spring Data Cassandra 1.4.2 :
http://projects.spring.io/spring-data-cassandra/

#### Note:
Spring Data is a wrapper around the Datastax Driver.
But it does comes with Spring. It adds a thin layer around the Datastax native Driver.

For this particular version it is dependent on
Cassandra Driver Core 2.1.7.1

So if you use Spring Data you are binded to the Datastax Driver Version used by Spring Data( though there must be a way to override this but the that won't be supported by Spring Data)
