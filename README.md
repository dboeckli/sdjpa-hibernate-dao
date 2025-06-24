# Spring Data JPA - Hibernate DAO
This repository contains source code examples to support my course Spring Data JPA and Hibernate Beginner to Guru

## Flyway

To enable Flyway in the MySQL profile, override the following properties when starting the application:
- `spring.flyway.enabled = true`
- `spring.docker.compose.file = compose-mysql.yaml`

This profile starts MySQL on port 3306 using the Docker Compose file `compose-mysql-.yaml`.

## Docker

Docker Compose file initially use the startup script located in `src/scripts`. These scripts create the database and users.

## Kubernetes

### Generate Config Map for mysql init script

When updating 'src/scripts/init-mysql-mysql.sql', apply the changes to the Kubernetes ConfigMap:
```bash
kubectl create configmap mysql-init-script --from-file=init.sql=src/scripts/init-mysql.sql --dry-run=client -o yaml | Out-File -Encoding utf8 k8s/mysql-init-script-configmap.yaml
```

### K8s Deployment

To deploy all resources:
```bash
kubectl apply -f k8s/
```

To remove all resources:
```bash
kubectl delete -f k8s/
```

Check
```bash
kubectl get deployments -o wide
kubectl get pods -o wide
```

### Deployment with Helm

Be aware that we are using a different namespace here (not default).

Go to the directory where the tgz file has been created after 'mvn install'
```powershell
cd target/helm/repo
```

unpack
```powershell
$file = Get-ChildItem -Filter sdjpa-hibernate-dao-v*.tgz | Select-Object -First 1
tar -xvf $file.Name
```

install
```powershell
$APPLICATION_NAME = Get-ChildItem -Directory | Where-Object { $_.LastWriteTime -ge $file.LastWriteTime } | Select-Object -ExpandProperty Name
helm upgrade --install $APPLICATION_NAME ./$APPLICATION_NAME --namespace sdjpa-hibernate-dao --create-namespace --wait --timeout 5m --debug
```

show logs and show event
```powershell
kubectl get pods -n sdjpa-hibernate-dao
```
replace $POD with pods from the command above
```powershell
kubectl logs $POD -n sdjpa-hibernate-dao --all-containers
```

Show Details and Event

$POD_NAME can be: sdjpa-hibernate-dao-mysql, sdjpa-hibernate-dao
```powershell
kubectl describe pod $POD_NAME -n sdjpa-hibernate-dao
```

Show Endpoints
```powershell
kubectl get endpoints -n sdjpa-hibernate-dao
```

test
```powershell
helm test $APPLICATION_NAME --namespace sdjpa-hibernate-dao --logs
```

uninstall
```powershell
helm uninstall $APPLICATION_NAME  --namespace sdjpa-hibernate-dao
```

## Running the Application
1. Choose between h2 or mysql for database schema management. (you can use one of the preconfigured intellij runners)
2. Start the application with the appropriate profile and properties.
3. The application will use Docker Compose to start MySQL and apply the database schema changes.