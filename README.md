# Shary Backend

This project is a Spring Boot application that serves as the backend for the Shary platform. The application is containerized using Docker and includes monitoring and logging capabilities via Prometheus, Grafana, Logstash, Elasticsearch, and Kibana.

## Prerequisites

Before you start, ensure that you have the following installed:

- [Docker](https://www.docker.com/get-started) (version 20.10 or later)
- [Docker Compose](https://docs.docker.com/compose/install/) (version 1.29 or later)
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (version 11 or later)

## Project Structure

- **shary**: Spring Boot application
- **prometheus**: Monitoring service to collect metrics
- **grafana**: Visualization tool for metrics
- **elasticsearch**: Storage for logs
- **logstash**: Log pipeline to process and forward logs to Elasticsearch
- **kibana**: Visualization tool for logs
- **mysql**: Database for the application
- **phpmyadmin**: Web interface for MySQL

## Getting Started

### Step 1: Clone the Repository

Clone the project repository to your local machine:

```sh
git clone https://github.com/amouddane/shary.git
cd shary
```
### Step 2: Build the Docker Images

Before running the services, build the Docker image for the Spring Boot application:

```sh
docker-compose build
```

### Step 3: Configure Prometheus

Ensure the Prometheus configuration file **prometheus.yml** is set up correctly:

```yml
global:
  scrape_interval: 15s

scrape_configs:
  - job_name: 'shary'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['shary:8080']

```
### Step 4: Start the Services
Start all the services using Docker Compose:

```sh
docker-compose up -d
```
This command will start the following services:

- **shary**: The Spring Boot backend service.
- **mysql**: The MySQL database service.
- **phpmyadmin**: A web interface to interact with MySQL.
- **elasticsearch**: Stores logs and other data.
- **logstash**: Processes logs and forwards them to Elasticsearch.
- **kibana**: Visualizes logs stored in Elasticsearch.
- **prometheus**: Collects metrics from the Spring Boot application.
- **grafana**: Visualizes metrics collected by Prometheus.

### Step 5: Access the Services

After starting the services, you can access them using the following URLs:

- **Shary Backend**: [http://localhost:8080](http://localhost:8080)
- **phpMyAdmin**: [http://localhost:8081](http://localhost:8081)
- **Kibana**: [http://localhost:5601](http://localhost:5601)
- **Prometheus**: [http://localhost:9090](http://localhost:9090)
- **Grafana**: [http://localhost:3000](http://localhost:3000)

### Step 6: Configure Grafana

1. **Login**: Open Grafana at [http://localhost:3000](http://localhost:3000). The default login is `admin/admin`.

2. **Add Prometheus as a Data Source**:
    - Navigate to "Configuration" -> "Data Sources" -> "Add data source".
    - Choose "Prometheus".
    - Set the URL to `http://prometheus:9090`.
    - Click "Save & Test".

3. **Create a Dashboard**:
    - Go to "Create" -> "Dashboard".
    - Add new panels with Prometheus queries to visualize the metrics. Refer to the Monitoring Metrics section below for example queries.

### Step 7: Monitor Logs in Kibana

Kibana provides a web interface to explore and visualize logs stored in Elasticsearch.

1. **Access Kibana**: Go to [http://localhost:5601](http://localhost:5601).

2. **Set Up Index Patterns**:
    - Navigate to "Stack Management" -> "Index Patterns".
    - Create a new index pattern that matches your log indices, e.g., `logstash-*`.

3. **Explore Logs**:
    - Go to "Discover" and start exploring logs.

## Monitoring Metrics

Here are some example Prometheus queries to get started with monitoring your application in Grafana:

- **Total HTTP Requests**:
    ```promql
    sum(rate(http_server_requests_seconds_count[5m]))
    ```

- **Heap Memory Usage**:
    ```promql
    jvm_memory_used_bytes{area="heap"}
    ```
- **Thread Count**:
    ```promql
    jvm_threads_current
    ```
- **Database Connection Pool Usage**:
    ```promql
    tomcat_jdbc_connections_active
    ```
- **Database Query Duration**:
    ```promql
    database_query_duration_seconds_sum
    ```
- **Database Query Count**:
    ```promql
    database_query_duration_seconds_count
    ```


## Troubleshooting

### Prometheus Shows "Instance Down" for `shary`

If Prometheus shows the `shary` instance as "DOWN," check the following:

- Ensure that the `shary_back` service is running and accessible at [http://shary_back:8080/actuator/prometheus](http://shary_back:8080/actuator/prometheus) from within the Docker network.
- Verify that the Spring Boot application has the `micrometer-registry-prometheus` dependency and that the `/actuator/prometheus` endpoint is enabled.

### Grafana Shows "No Data"

If Grafana dashboards show "No Data":

- Ensure that Prometheus is successfully scraping the metrics from the Spring Boot application.
- Check Prometheus targets by visiting [http://localhost:9090/targets](http://localhost:9090/targets) and ensure the `shary_back` target is listed and UP.

### Logs Are Not Appearing in Kibana

If you don't see logs in Kibana:

- Ensure that Logstash is correctly forwarding logs to Elasticsearch.
- Check the Logstash logs for any errors that might indicate why logs are not being processed.

## Stopping the Services

To stop all running services:

```sh
docker-compose down

```
This will stop and remove the containers, but data in volumes will persist.

## Cleaning Up
If you want to remove all containers and volumes created by Docker Compose:

```sh
docker-compose down -v
```
This command will stop and remove the containers and delete the volumes.

## License
Let's see 