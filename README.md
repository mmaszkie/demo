# Student environment check

Ready-to-run Spring Boot application for verifying development environment. No additional code is required.

Supported environments: Linux and Windows PowerShell. On Windows, use native JDK 25; SDKMAN! is supported only on Linux.

## Application

| Method | URL                               | Purpose                  |
|--------|-----------------------------------|--------------------------|
| `GET`  | `http://localhost:8080/api/notes` | Reads notes from MongoDB |
| `POST` | `http://localhost:8080/api/notes` | Saves a note in MongoDB  |

Default database: `student_environment`. Default collection: `notes`.

## Checklist

```text
[ ] Discord: account, application, Allegro UMK 26/27, #backend
[ ] GitHub: active account
[ ] Git: works
[ ] Repository: cloned
[ ] Java and javac: version 25
[ ] Gradle: JVM 25
[ ] IntelliJ IDEA: import, compilation and application startup
[ ] Podman and Podman Desktop: work
[ ] Podman hello-world: works
[ ] mongosh: version > 2.3
[ ] MongoDB: container and connection work
[ ] Testcontainers: tests work from IDE and terminal
[ ] Postman: GET 200, POST 201
[ ] MongoDB Compass: displays saved document
[ ] Podman: image and application container work
[ ] Compose: application and MongoDB work
[ ] minikube: cluster works with Podman
[ ] kubectl: Kubernetes pods work
[ ] Android Studio: emulator runs an application
```

## 1. Discord and GitHub

**What is installed:** Discord application and an active GitHub account.

**What is checked:** Discord server/channel access and GitHub sign-in.

1. Install Discord: <https://discord.com/download>.
2. Create or sign in to an account.
3. Join the `Allegro UMK 26/27` server and open `#backend`.
4. Create or sign in to a GitHub account: <https://github.com/join>.

## 2. Git and application repository

**What is installed:** Git and the course repository.

**What is checked:** Git availability, repository cloning and repository contents.

### Linux

```bash
sudo apt update
sudo apt install -y git
git --version
git clone https://github.com/mmaszkie/demo.git
cd demo
git status
```

### Windows PowerShell

```powershell
winget install --id Git.Git -e
git --version
git clone https://github.com/mmaszkie/demo.git
cd demo
git status
```

Use SSH, if already configured:

```bash
git clone git@github.com:mmaszkie/demo.git
```

Check:

```text
[ ] `git --version` displays a Git version
[ ] `git status` finishes without an error
[ ] Directory contains `build.gradle`, `gradlew`, `gradlew.bat` and `src`
```

## 3. Java 25

**What is installed:** JDK 25, including the Java compiler.

**What is checked:** `java`, `javac` and the Java version used by Gradle.

### Linux SDKMAN!

```bash
sudo apt install -y curl zip unzip
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 25.0.2-open
sdk default java 25.0.2-open
java -version
javac -version
```

Check if `25.0.2-open` is unavailable:

```bash
sdk list java
```

If yes, install other available JDK 25 distribution.

### Windows PowerShell

Install JDK 25 through WinGet or from <https://jdk.java.net/archive>:

```powershell
winget search OpenJDK
winget install --id Microsoft.OpenJDK.25 -e
java -version
javac -version
```

If PowerShell cannot find Java, set `JAVA_HOME`, add `%JAVA_HOME%\bin` to `Path`, open a new terminal and run the commands again.

Check:

```text
[ ] `java -version` indicates Java 25
[ ] `javac -version` indicates Java 25
[ ] Linux: Java was installed through SDKMAN!
[ ] Windows: JDK 25 is available in `Path`
```

## 4. Gradle wrapper

**What is installed:** Nothing separately. The repository provides Gradle Wrapper.

**What is checked:** Gradle starts and uses Java 25.

Run Gradlew wrapper in the cloned repository directory.

### Linux

```bash
chmod +x gradlew
./gradlew --version
```

### Windows PowerShell

```powershell
.\gradlew.bat --version
```

Check:

```text
[ ] Gradle Wrapper starts
[ ] `JVM` line indicates Java 25
[ ] No separate Gradle installation is used
```

Linux troubleshooting: fix `Permission denied` with `chmod +x gradlew`.

## 5. Podman and Podman Desktop

**What is installed:** Podman CLI, Podman Desktop and the container engine or Podman machine.

**What is checked:** Podman information, container execution and the Podman Desktop application.

Install Podman and Podman Desktop: <https://podman.io/>.

### Linux

Install the package for your distribution, then run:

```bash
podman --version
podman info
podman run hello-world
```

### Windows PowerShell

Install Podman Desktop: <https://podman-desktop.io/downloads>.

```powershell
podman machine list
podman machine start
podman --version
podman info
podman run hello-world
```

Check:

```text
[ ] `podman --version` works
[ ] `podman info` works
[ ] `podman run hello-world` output contains `Hello Podman World`
[ ] Podman Desktop starts without an error
```

Windows troubleshooting: if no machine exists, run `podman machine init` once. If it is stopped, run `podman machine start`.

## 6. MongoDB Shell and container

**What is installed:** MongoDB Shell and a MongoDB 8 container.

**What is checked:** Shell version, MongoDB container status, connectivity and database ping.

Install MongoDB Shell: <https://www.mongodb.com/docs/mongodb-shell/install/>.

Check in the terminal where `mongosh` was installed:

```bash
mongosh --version
```

The version must be greater than `2.3`.

Start MongoDB in the terminal used for Podman:

```bash
podman run --name student-mongo -p 27017:27017 -d docker.io/library/mongo:8
podman ps
mongosh "mongodb://localhost:27017/student_environment"
```

If `student-mongo` already exists, use `podman start student-mongo` instead of running `podman run` again.

Inside `mongosh`:

```javascript
db.runCommand({ ping: 1 })
exit
```

Check:

```text
[ ] `student-mongo` is running
[ ] `mongosh` connects to `localhost:27017`
[ ] Ping returns `ok: 1`
```

Troubleshooting: if port `27017` is busy, run `podman ps -a`. Stop and remove the old `student-mongo` only if its data is no longer needed:

```bash
podman stop student-mongo
podman rm student-mongo
```

## 7. IntelliJ IDEA

**What is installed:** IntelliJ IDEA Community Edition and the imported Gradle project.

**What is checked:** Java 25 configuration, Gradle configuration, source compilation and application startup.

Install IntelliJ IDEA: <https://www.jetbrains.com/idea/download/>.

### 7.1 Open the project

1. Select `Open` and choose repository directory `demo`.
2. Do not select only `src`.
3. Accept `Trust Project` if IntelliJ IDEA asks.
4. Wait for Gradle import to finish.
5. Open the Gradle tool window and confirm that tasks `build`, `test` and `bootRun` are visible.

### 7.2 Configure Java 25

IntelliJ IDEA needs the JDK directory, not the Java executable.

Linux SDKMAN! JDK path:

```bash
sdk home java current
```

Windows JDK path:

```powershell
$env:JAVA_HOME
Get-Command java | Select-Object -ExpandProperty Source
```

Configure the project SDK:

1. Open `File` -> `Project Structure` -> `Project`.
2. Set `Project SDK` to Java 25.
3. If Java 25 is missing, choose `Add SDK` -> `JDK` and select the JDK root directory.
4. Set `Language level` to `SDK default` or Java 25.
5. Open `Modules` -> `Dependencies`.
6. Set `Module SDK` to `Project SDK`.

### 7.3 Configure Gradle

1. Open `File` -> `Settings`.
2. Select `Build, Execution, Deployment` -> `Build Tools` -> `Gradle`.
3. Set `Gradle distribution` to `Wrapper`.
4. Set `Gradle JVM` to Java 25 or `Project SDK`.
5. Set `Build and run using` to `Gradle`.
6. Set `Run tests using` to `Gradle`.
7. Select `Apply` and `OK`.
8. In the Gradle tool window, select `Reload All Gradle Projects`.

Verify Java from the IntelliJ IDEA terminal:

Linux:

```bash
java -version
./gradlew --version
```

Windows PowerShell:

```powershell
java -version
.\gradlew.bat --version
```

Both outputs must indicate Java 25. In Gradle output, check the `JVM` line.

### 7.4 Compile the project

1. Select `Build` -> `Build Project`.
2. Wait for the Build tool window.
3. Confirm that compilation finishes successfully.

Expected result:

```text
Build completed successfully
```

### 7.5 Run the application

Before starting the application, check that `student-mongo` is running:

```bash
podman ps
```

Open `src/main/java/com/example/demo/DemoApplication.java` and select the green icon next to `main`.

Expected log contains:

```text
Started DemoApplication
```

Verify the application:

```text
http://localhost:8080/api/notes
```

Expected HTTP status: `200 OK`.

Leave the application running for steps checking Postman and Compass. Stop it before starting the standalone application container.

Check:

```text
[ ] Spring and JUnit imports are not red
[ ] `Build Project` finishes successfully
[ ] Project SDK, Module SDK and Gradle JVM use Java 25
[ ] Gradle Wrapper is selected
[ ] Log contains `Started DemoApplication`
[ ] `http://localhost:8080/api/notes` returns `200 OK`
```

### Troubleshooting

If imports are red, select `Reload All Gradle Projects` and check Gradle JVM.

If IntelliJ IDEA reports `invalid source release: 25`, check Project SDK, Module SDK and Gradle JVM.

If the application cannot connect to MongoDB, check `podman ps` and:

```text
mongodb://localhost:27017/student_environment
```

## 8. Testcontainers

**What is installed:** Nothing separately; Testcontainers is already included in the project dependencies.

**What is checked:** Podman, automatic MongoDB container startup and Spring Boot integration with a real MongoDB instance.

This step verifies that the application can connect to MongoDB and use it during automated tests.

The test starts a temporary, real MongoDB container through Testcontainers and then runs the application test context against that database. It verifies the complete integration flow:

```text
Spring Boot -> MongoDB container -> repository -> REST controller
```

The test performs the following actions:

1. Checks that the notes collection is initially empty.
2. Sends a POST request through `MockMvc`.
3. Verifies that the note is saved in MongoDB.
4. Sends a GET request through `MockMvc`.
5. Verifies that the saved note is returned.

The manually managed `student-mongo` container from step 6 is not used by these tests. Testcontainers creates an isolated database container and removes it after the test run. Podman must be running because Testcontainers needs a container engine.

The application default URI is used for normal local startup. During this test, `@ServiceConnection` overrides it with the temporary container's URI and dynamic port.

### Run tests in IntelliJ IDEA

1. Open `src/test/java/com/example/demo/NoteControllerIntegrationTest.java`.
2. Run `NoteControllerIntegrationTest` using the green icon.
3. Run all tests by right-clicking `src/test/java` and selecting `Run 'All Tests'`.

### Run tests from terminal

Linux:

```bash
./gradlew clean test
```

Windows PowerShell:

```powershell
.\gradlew.bat clean test
```

Check:

```text
[ ] Integration test is green in IntelliJ IDEA
[ ] All tests are green in IntelliJ IDEA
[ ] Terminal ends with `BUILD SUCCESSFUL`
```

### Troubleshooting

Run `podman info`. On Windows also run `podman machine start`. If Testcontainers cannot connect to Podman, follow <https://java.testcontainers.org/supported_docker_environment/>.

## 9. Build application

**What is installed:** Compiled application and executable Spring Boot JAR.

**What is checked:** Full Gradle compilation, automated tests and successful JAR creation.

Linux:

```bash
./gradlew clean build
```

Windows PowerShell:

```powershell
.\gradlew.bat clean build
```

Check:

```text
[ ] Output contains `BUILD SUCCESSFUL`
[ ] Executable JAR exists in `build/libs/`
```

## 10. Postman and MongoDB Compass

**What is installed:** Postman and MongoDB Compass.

**What is checked:** HTTP GET/POST requests, MongoDB persistence and visibility of the saved document in Compass.

Install Postman: <https://www.postman.com/downloads/>.

Ensure that the IntelliJ application and `student-mongo` are running.

1. Send from Postman:

```http
GET http://localhost:8080/api/notes
```

Expected status: `200 OK`. Response is a JSON array and may be empty.

2. Send from Postman:

```http
POST http://localhost:8080/api/notes
Content-Type: application/json
```

```json
{
  "text": "MongoDB connection test",
  "author": "Student"
}
```

Expected status: `201 Created`; response contains `id`, `text` and `author`. Send GET again and verify the saved note.

Install MongoDB Compass: <https://www.mongodb.com/try/download/compass>. Connect to:

```text
mongodb://localhost:27017
```

Refresh and open `student_environment` -> `notes`. The Postman document must be visible.

Check:

```text
[ ] GET returns `200 OK`
[ ] POST returns `201 Created`
[ ] GET returns the saved note
[ ] Compass connects to MongoDB
[ ] Saved document is visible in `student_environment.notes`
```

Compass troubleshooting: run `podman ps` and check `student-mongo`.

## 11. Podman image and application container

**What is installed:** Container image and a containerized instance of the Spring Boot application.

**What is checked:** Image build, application startup, MongoDB connection and API requests from the container.

Stop the IntelliJ application to release port `8080`. Keep `student-mongo` running.

Build the image:

```bash
podman build -t student-environment-check:latest .
podman images
```

### Linux

```bash
podman run --rm --name student-environment-app -p 8080:8080 \
  -e SPRING_MONGODB_URI=mongodb://host.containers.internal:27017/student_environment \
  student-environment-check:latest
```

### Windows PowerShell

```powershell
podman run --rm --name student-environment-app -p 8080:8080 -e SPRING_MONGODB_URI=mongodb://host.containers.internal:27017/student_environment student-environment-check:latest
```

Repeat GET and POST requests from step 10.

Check:

```text
[ ] Image `student-environment-check` exists
[ ] Container starts on port 8080
[ ] GET returns `200 OK`
[ ] POST returns `201 Created`
```

Troubleshooting: stop IntelliJ or another container if port `8080` is busy. For MongoDB errors check `student-mongo` and `SPRING_MONGODB_URI`.

## 12. Compose

**What is installed:** Compose support for Podman and the two services defined in `compose.yaml`.

**What is checked:** Compose service startup, MongoDB health, application-to-MongoDB networking and both API endpoints.

Compose command starts MongoDB and the application from `compose.yaml`.

1. Stop the application from step 11 with `Ctrl+C` and stop standalone MongoDB:

```bash
podman stop student-mongo
```

2. Configure Compose using <https://podman-desktop.io/docs/compose/setting-up-compose>.
3. Run in one terminal:

```bash
podman compose version
podman compose up --build -d
podman compose ps
```

4. Repeat GET and POST from step 10.
5. Stop services:

```bash
podman compose down
```

Check:

```text
[ ] Compose provider works
[ ] `app` and `mongo` services are running
[ ] GET returns `200 OK`
[ ] POST returns `201 Created`
[ ] `podman compose down` stops services
```

The named MongoDB volume remains for later runs. Use `podman compose down -v` only when you intentionally want to delete Compose data.

Troubleshooting:

```bash
podman compose logs app
podman compose logs mongo
```

If `docker-credential-desktop` appears, stale Docker Desktop configuration is probably present. This is not expected on a clean student environment; ask the instructor before changing Docker configuration.

## 13. minikube

**What is installed:** minikube cluster using the Podman driver and CRI-O runtime.

**What is checked:** minikube installation, cluster startup, Kubernetes component status and cluster resources.

Install minikube: <https://minikube.sigs.k8s.io/docs/start/>.

### Linux

```bash
minikube version
minikube start --driver=podman --container-runtime=cri-o
minikube status
```

### Windows PowerShell

```powershell
winget install Kubernetes.minikube
podman machine start
podman info
minikube version
minikube start --driver=podman --container-runtime=cri-o
minikube status
```

Check:

```text
[ ] `minikube version` works
[ ] Components in `minikube status` are `Running`
[ ] Cluster uses Podman and CRI-O
```

Troubleshooting: use `minikube logs`. After interrupted startup:

```bash
minikube delete
minikube start --driver=podman --container-runtime=cri-o
```

On Windows, check `podman machine list`. Common causes are insufficient CPU/RAM, stopped machine, proxy or firewall blocking image downloads.

## 14. kubectl and Kubernetes

**What is installed:** kubectl client, MongoDB deployment and Spring Boot deployment in Kubernetes.

**What is checked:** kubectl access, `kube-system` pods, image, pod readiness, services and API access through port forwarding.

Install kubectl:

- Linux: <https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/>
- Windows: <https://kubernetes.io/docs/tasks/tools/install-kubectl-windows/>

Check client and system pods:

```bash
kubectl version --client
kubectl get pods --all-namespaces
```

The output must contain pods in `kube-system`.

Build the JAR and image inside minikube:

### Linux

```bash
./gradlew clean build
minikube image build -t student-environment-check:latest -f Containerfile .
```

### Windows PowerShell

```powershell
.\gradlew.bat clean build
minikube image build -t student-environment-check:latest -f Containerfile .
```

Deploy:

```bash
kubectl apply -f k8s/mongo.yaml
kubectl apply -f k8s/app.yaml
kubectl rollout status deployment/mongo
kubectl rollout status deployment/student-environment-app
kubectl get pods
kubectl get services
```

Pods must be `Running` and ready `1/1`.

Forward the application port in a separate terminal:

```bash
kubectl port-forward service/student-environment-app 8080:8080
```

Run GET and POST requests from step 10. Stop forwarding with `Ctrl+C`, then clean up:

```bash
kubectl delete -f k8s/app.yaml
kubectl delete -f k8s/mongo.yaml
minikube stop
```

Check:

```text
[ ] `kubectl get pods --all-namespaces` shows `kube-system`
[ ] MongoDB and application pods are `Running` and ready `1/1`
[ ] GET and POST work through port-forward
[ ] Kubernetes resources and minikube are stopped after the test
```

Troubleshooting:

```bash
kubectl get pods
kubectl describe pod <pod-name>
kubectl logs deployment/student-environment-app
kubectl logs deployment/mongo
```

For `ErrImageNeverPull`, run `minikube image build -t student-environment-check:latest -f Containerfile .` again, then:

```bash
kubectl rollout restart deployment/student-environment-app
```

## 15. Android Studio

**What is installed:** Android Studio, an Android project and an emulator.

**What is checked:** Android Studio startup, emulator startup and execution of a basic application.

Install Android Studio: <https://developer.android.com/studio/install>.

1. Create an empty application: <https://developer.android.com/studio/projects/create-project>.
2. Create an emulator: <https://developer.android.com/studio/run/managing-avds>.
3. Run the application: <https://developer.android.com/studio/run/emulator>.

Check: Android Studio, emulator and empty application start without errors.

## Final Result

Linux:

```bash
./gradlew clean test
./gradlew clean build
```

Windows PowerShell:

```powershell
.\gradlew.bat clean test
.\gradlew.bat clean build
```

Expected result:

```text
BUILD SUCCESSFUL
```
