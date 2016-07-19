#!/usr/bin/env bash
###############################################################################

# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
###############################################################################

noofrecords_default=1000
noofthreads_default=10
debug_default=false
result_file=data/c8_performance_runs.dat

usage() {
  [ "${1}" ] && echo "${red}error: ${1}${reset}"

  cat << EOF
${green}
usage: `basename ${0}` [options] [commands]

options:
  -n | --noofrecords [ long ]            : Total no of records(partition keys to be written, there are 4 cluserting columns for every partition key); default: $noofrecords_default
  -t | --noofthreads [ integer ]         : no of threads to run; default: $noofthreads_default
  -d | --debug [ true | false ]          : debug ; default: $debug_default
  -h | --help                            : help message

commands:
  run[:dsx_drv_3_0_2,sprng_data_1_4_2]     : run the performance test for driver dsx_drv_3_0_2,sprng_data_1_4_2
  help                                     : help message

results: will be written to base_dir/$result_file ( if debug is true results are on console)

e.g
bin/c8_driver_perf.sh  -n 1000 run:dsx_drv_3_0_2,sprng_data_1_4_2
${reset}
EOF

  exit ${2:-0}
}

optspec=":d:n:t:h-:"

while getopts "${optspec}" opt; do
  case "${opt}" in
    -)
      case "${OPTARG}" in
        run) run="${!OPTIND}"; OPTIND=$(( ${OPTIND} + 1 ));;
        help) usage;;
        *) [ "${OPTERR}" = 1 ] && [ "${optspec:0:1}" != ":" ] && echo "unknown option --${OPTARG}";;
      esac;;
    n) noofrecords=${OPTARG};;
    t) noofthreads=${OPTARG};;
    d) debug=${OPTARG};;
    h) usage;;
    :) usage "option -${OPTARG} requires an argument" 1;;
    \?) [ "${OPTERR}" != 1 ] || [ "${optspec:0:1}" = ":" ] && usage "unknown option -${OPTARG}" 1;;
  esac
done

docker_name=c8_perf

run() {

  driver=${1}
  echo "Params:"
  echo "noofrecords: $noofrecords"
  echo "noofthreads: $noofthreads "
  echo "debug: $debug "
  echo "driver: $driver "
  echo ""

  ls modules/sprng_data_1_4_2/target/*-c8-perf-all.jar | grep 'c8-perf-all' &> /dev/null
  if [ $? != 0 ]; then
     echo "Error: jar not found: modules/sprng_data_1_4_2/target/*-c8-perf-all.jar"
     echo "Need to build the project execute: mvn clean package"
     return -1
  fi

  ls modules/dsx_drv_3_0_2/target/*-c8-perf-all.jar | grep 'c8-perf-all' &> /dev/null
  if [ $? != 0 ]; then
     echo "Error: jar not found: modules/dsx_drv_3_0_2/target/*-c8-perf-all.jar"
     echo "Need to build the project execute: mvn clean package"
     return -1
  fi

  docker --version | grep 'version' &> /dev/null
  if [ $? != 0 ]; then
     echo "Error: Docker not installed"
     echo "Please install Docker"
     return -1
  fi

  docker stop -f $docker_name 2> /dev/null
  docker rm -f $docker_name 2> /dev/null
  docker run -d --name $docker_name -p 9042:9042 -p 9160:9160 -d cassandra:2.1

  cql_setup=`cat ./bin/c8_driver_perf_setup.cql`
  echo "Setting up Cassandra: $cql_setup"

  # Check if docker ready to accept cqlsh commands then remove sleep
  sleep 20
  docker exec -it $docker_name cqlsh -e "$cql_setup"
  echo "Command for cqlsh: docker exec -it $docker_name cqlsh"

  mkdir -p data

  start_date=`date`
  if [ "$debug" == true ] ; then
    java -jar modules/$driver/target/*-c8-perf-all.jar $noofrecords $noofthreads
  else
    java -jar modules/$driver/target/*-c8-perf-all.jar $noofrecords $noofthreads | grep "__DATA__" | tee -a $result_file
  fi
  end_date=`date`

  echo "Process Started at: $start_date"
  echo "Process Ended at: $end_date"
  docker stop $docker_name
  docker rm $docker_name
}

[ $# -eq 0 ] && usage "unspecified command" 1

noofrecords_default=1000
noofthreads_default=10
debug_default=false


noofrecords=${noofrecords:=${noofrecords_default}}
noofthreads=${noofthreads:=${noofthreads_default}}
debug=${debug:=${debug_default}}


[[ $# -eq 0 ]] && usage

for command in ${@:$OPTIND}; do
  case "${command}" in
    run) run;;
    run:*) commands=$(echo ${command} | cut -d ':' -f 2)
      (IFS=','; for cmd in ${commands}; do run ${cmd}; done)
      echo "checkout the results in the file $result_file";;
    help) usage;;
    *) usage "unknown command: ${command}" 1;;
  esac
done
