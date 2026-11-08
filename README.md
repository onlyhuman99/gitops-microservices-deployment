# GitOps Microservices Deployment

A production-inspired GitOps project demonstrating the deployment, continuous delivery, and monitoring of containerized microservices on Kubernetes using Docker, Helm, Argo CD, and GitHub Actions.

## Business Domain

**Smart Logistics Platform**

The platform consists of four independent Spring Boot microservices, each connected to its own PostgreSQL database.

## Architecture

```text
                    GitHub Repository
                           |
                     GitHub Actions
                           |
                    Build & Test
                           |
                    Build Docker Images
                           |
                          GHCR
                           |
                        Argo CD
                           |
                    Kubernetes Cluster
                           |
                  smart-logistics namespace
                           |
          +----------------+----------------+
          |                |                |
       Shipment         Tracking         Warehouse
       Service          Service          Service
          |                |                |
          +----------------+----------------+
                           |
                   Notification Service

             Each service has its own database.
                           |
                      PostgreSQL


                  monitoring namespace
                           |
                 +---------+---------+
                 |                   |
              Prometheus           Grafana
                 |                   |
                 +---------+---------+
                           |
                 Microservice Metrics
                 /actuator/prometheus
```

## Technology Stack

| Category | Technologies |
|---|---|
| Backend | Java 25, Spring Boot |
| Database | PostgreSQL 16 |
| Containerization | Docker, Docker Compose |
| Orchestration | Kubernetes, Minikube |
| Package Management | Helm |
| GitOps | Argo CD |
| CI/CD | GitHub Actions |
| Container Registry | GitHub Container Registry (GHCR) |
| Monitoring | Prometheus, Grafana |
| Secret Management | SOPS, age |

## Microservices

| Microservice | Port | Database | Kubernetes Service Type |
|---|---:|---|---|
| Shipment Service | 8081 | shipment_db | NodePort |
| Tracking Service | 8082 | tracking_db | ClusterIP |
| Warehouse Service | 8083 | warehouse_db | ClusterIP |
| Notification Service | 8084 | notification_db | ClusterIP |

Each microservice is independently containerized and maintains a separate PostgreSQL database.

## Repository Structure

```text
.
├── .github/
│   └── workflows/
│       └── ci-cd.yml
├── argocd/
│   ├── monitoring/
│   │   ├── grafana-application.yaml
│   │   └── prometheus-application.yaml
│   └── smart-logistics-application.yaml
├── db/
│   └── init.sql
├── helm/
│   └── smart-logistics/
│       ├── templates/
│       ├── Chart.yaml
│       └── values.yaml
├── k8s/
│   └── Kubernetes manifests
├── monitoring/
│   ├── grafana/
│   └── prometheus/
├── secrets/
│   ├── grafana-admin.enc.yaml
│   └── postgres-secret.enc.yaml
├── shipment-service/
├── tracking-service/
├── warehouse-service/
├── notification-service/
├── docker-compose.yml
├── .sops.yaml
└── README.md
```

## Local Deployment Using Docker Compose

### Prerequisites

- Docker Engine
- Docker Compose

### 1. Configure environment variables

Create a local `.env` file in the repository root:

```env
POSTGRES_PASSWORD=your_strong_password
```

The `.env` file is excluded from Git. Use `.env.example` as a template.

### 2. Start the application

```bash
docker compose up --build -d
```

This builds the four microservices, starts PostgreSQL, and launches the application containers.

### 3. Check running containers

```bash
docker compose ps
```

### 4. Stop the application

```bash
docker compose down
```

The PostgreSQL volume is retained when using this command.

## Kubernetes Deployment Using Helm and Argo CD

### Prerequisites

- Docker
- Minikube
- kubectl
- Helm
- Argo CD
- SOPS
- age

### 1. Start the Kubernetes cluster

```bash
minikube start --driver=docker
```

### 2. Configure encrypted secrets

The project uses SOPS with age encryption to protect PostgreSQL and Grafana credentials.

The age private key must be available locally at:

```text
~/.config/sops/age/keys.txt
```

Set restrictive permissions on the key:

```bash
chmod 700 ~/.config/sops/age
chmod 600 ~/.config/sops/age/keys.txt
```

Apply the encrypted Kubernetes Secrets:

```bash
sops --decrypt secrets/postgres-secret.enc.yaml | kubectl apply -f -
```

```bash
sops --decrypt secrets/grafana-admin.enc.yaml | kubectl apply -f -
```

These commands provision the Secrets in their respective namespaces. Secret provisioning is currently a manual step and is not reconciled by Argo CD.

### 3. Deploy Argo CD applications

Apply the application manifests:

```bash
kubectl apply -f argocd/smart-logistics-application.yaml
kubectl apply -f argocd/monitoring/prometheus-application.yaml
kubectl apply -f argocd/monitoring/grafana-application.yaml
```

Argo CD monitors the configured Git branch and automatically synchronizes the Helm charts.

### 4. Verify deployments

Check application pods:

```bash
kubectl get pods -n smart-logistics
kubectl get pods -n monitoring
```

Check Argo CD applications:

```bash
kubectl get applications -n argocd
```

The expected applications are:

- smart-logistics
- prometheus
- grafana

## CI/CD Pipeline

The GitHub Actions workflow is defined in:

```text
.github/workflows/ci-cd.yml
```

The pipeline runs on pushes and pull requests targeting the `week-5-monitoring-observability` branch.

### Pipeline Workflow

1. Checks out the repository.
2. Sets up Java 25 using Eclipse Temurin.
3. Starts a PostgreSQL 16 service container.
4. Creates separate databases for all four microservices.
5. Runs Maven `clean verify` for each microservice.
6. Builds Docker images for all four services.
7. Pushes images to GitHub Container Registry.
8. Updates image tags in Helm `values.yaml`.
9. Commits and pushes the updated Helm configuration.

### Image Registry

The service images are published to:

```text
ghcr.io/onlyhuman99/
```

Each image is tagged with the corresponding Git commit SHA.

The workflow excludes Helm image-tag updates from triggering another workflow run, preventing recursive CI/CD executions.

## GitOps with Argo CD

Argo CD continuously monitors the configured Git repository and manages three applications.

| Application | Source Path | Namespace |
|---|---|---|
| smart-logistics | helm/smart-logistics | smart-logistics |
| prometheus | monitoring/prometheus | monitoring |
| grafana | monitoring/grafana | monitoring |

Automated synchronization is configured with:

- Automatic synchronization
- Automatic pruning
- Self-healing
- Automatic namespace creation

Changes committed to the configured branch are reconciled by Argo CD.

## Monitoring and Observability

The project uses Prometheus and Grafana to monitor all four Spring Boot microservices.

### Prometheus

Prometheus collects application metrics every 15 seconds.

The configured scrape targets are:

| Service | Metrics Endpoint |
|---|---|
| Shipment | `/actuator/prometheus` |
| Tracking | `/actuator/prometheus` |
| Warehouse | `/actuator/prometheus` |
| Notification | `/actuator/prometheus` |

Prometheus discovers the services using their Kubernetes DNS names.

### Grafana

Grafana uses Prometheus as its default data source.

The project includes a dashboard with seven monitoring panels displaying application metrics.

### Accessing Monitoring Interfaces

Start port forwarding for Grafana:

```bash
kubectl port-forward -n monitoring svc/grafana 3000:80
```

Open:

```text
http://localhost:3000
```

Start port forwarding for Prometheus in another terminal:

```bash
kubectl port-forward -n monitoring svc/prometheus-server 9090:80
```

Open:

```text
http://localhost:9090
```

The port-forwarding terminals must remain open while accessing the interfaces.

Grafana administrator credentials are provisioned through the `grafana-admin` Kubernetes Secret.

## Secret Management

The project uses **SOPS and age** to encrypt sensitive Kubernetes configuration.

Encrypted manifests are stored in:

```text
secrets/
├── postgres-secret.enc.yaml
└── grafana-admin.enc.yaml
```

The age private key is stored outside the repository.

The repository does not contain the age private key or plaintext Kubernetes Secret manifests.

Secret provisioning currently requires manual decryption and application using SOPS and kubectl.

## Project Roadmap

- Week 1: Foundation and Microservices
- Week 2: Containerization and Kubernetes
- Week 3: Helm and CI Pipeline
- Week 4: GitOps with Argo CD
- Week 5: Observability, Scaling and Security
- Week 6: Production Readiness and Portfolio

## Project Status

The Week 5 implementation includes:

- Four containerized Spring Boot microservices.
- PostgreSQL with separate databases.
- Kubernetes deployment using Helm.
- Automated CI/CD using GitHub Actions.
- Container image publishing to GHCR.
- GitOps synchronization using Argo CD.
- Prometheus metrics collection.
- Grafana monitoring dashboard.
- SOPS and age-based secret encryption.
