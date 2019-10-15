"""Script to insert simulation results in InfluxDB"""
import re
import os
import json
from influxdb import InfluxDBClient


# Databases client Config
dbClient = InfluxDBClient('172.18.239.81', 8086, 'admin', 'admin', 'gatlingdb')
measurement = "gatling"

# JenkinsConsolePath
url = "https://127.0.0.1:8080/job/"
jobname = str(os.getenv("JOB_NAME"))
buildNo = str(os.getenv("BUILD_NUMBER"))
endpoint = url + jobname + "/" + buildNo + "/console"

# Extracting metrics from Stats.json in each simulation
dir = "target/gatling"
startspath = []
def traversedirectory(path):
    for (root,dirs,files) in os.walk(path):
        for file in files:
            if file.startswith("stats.json"):
                startspath.append(os.path.join(root, file))
    return startspath


traversedirectory(dir)


for dir in range(len(startspath)):
    # print startspath[dir]
    simulation = (re.split('[-]',startspath[dir].split(os.sep)[-3])[0] + "_" + jobname).lower()
    print "Updating metrics for Simulation: ", simulation
    # Deserializationg Json simulation stats
    with open(startspath[dir]) as f:
        statsjson = json.load(f)
    # Data
    request = "allRequests"
    status = ["all", "ok", "ko"]

    # Influx Query
    simulationstats = [
    {"measurement": measurement,

     "tags": {
         "request": request,
         "simulation": simulation,
         "status": status[0],
         "jobName": jobname,
         "buildURL": endpoint
     },
     "fields":
         {
             "count": float(statsjson['stats']['numberOfRequests']['total']),
             "max": float(statsjson['stats']['maxResponseTime']['total']),
             "mean": float(statsjson['stats']['meanResponseTime']['total']),
             "min": float(statsjson['stats']['minResponseTime']['total']),
             "percentiles50": float(statsjson['stats']['percentiles1']['total']),
             "percentiles75": float(statsjson['stats']['percentiles2']['total']),
             "percentiles95": float(statsjson['stats']['percentiles3']['total']),
             "percentiles99": float(statsjson['stats']['percentiles4']['total']),
             "stdDev": float(statsjson['stats']['standardDeviation']['total']),
             "Throughput": float(statsjson['stats']['meanNumberOfRequestsPerSecond']['total'])
         }
     },
    {"measurement": measurement,

     "tags": {
         "request": request,
         "simulation": simulation,
         "status": status[1],
         "jobName": jobname,
         "buildURL": endpoint
     },
     "fields":
         {
             "count": float(statsjson['stats']['numberOfRequests']['ok']),
             "max": float(statsjson['stats']['maxResponseTime']['ok']),
             "mean": float(statsjson['stats']['meanResponseTime']['ok']),
             "min": float(statsjson['stats']['minResponseTime']['ok']),
             "percentiles50": float(statsjson['stats']['percentiles1']['ok']),
             "percentiles75": float(statsjson['stats']['percentiles2']['ok']),
             "percentiles95": float(statsjson['stats']['percentiles3']['ok']),
             "percentiles99": float(statsjson['stats']['percentiles4']['ok']),
             "stdDev": float(statsjson['stats']['standardDeviation']['ok']),
             "Throughput": float(statsjson['stats']['meanNumberOfRequestsPerSecond']['ok'])
         }
     },
    {"measurement": measurement,

     "tags": {
         "request": request,
         "simulation": simulation,
         "status": status[2],
         "jobName": jobname,
         "buildURL": endpoint
     },
     "fields":
         {
             "count": float(statsjson['stats']['numberOfRequests']['ko']),
             "max": float(statsjson['stats']['maxResponseTime']['ko']),
             "mean": float(statsjson['stats']['meanResponseTime']['ko']),
             "min": float(statsjson['stats']['minResponseTime']['ko']),
             "percentiles50": float(statsjson['stats']['percentiles1']['ko']),
             "percentiles75": float(statsjson['stats']['percentiles2']['ko']),
             "percentiles95": float(statsjson['stats']['percentiles3']['ko']),
             "percentiles99": float(statsjson['stats']['percentiles4']['ko']),
             "stdDev": float(statsjson['stats']['standardDeviation']['ko']),
             "Throughput": float(statsjson['stats']['meanNumberOfRequestsPerSecond']['ko'])
         }
     }
]

    # Write the time series data points into database
    dbClient.write_points(simulationstats)
    f.close()
dbClient.close()
