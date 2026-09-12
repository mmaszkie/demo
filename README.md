# Student Environment Check

Minimal Spring Boot application used to verify backend development environment.

Supported paths:

- Linux terminal,
- native Windows PowerShell.

Student does not need to write or modify code.

Application uses Java 25, Spring Boot, Gradle, MongoDB, Podman, Compose, minikube, kubectl, Postman and Testcontainers.

## Application

Application exposes two endpoints:

| Method | URL | Description |
|---|---|---|
| `GET` | `/api/notes` | Reads all notes from MongoDB |
| `POST` | `/api/notes` | Adds one note to MongoDB |

Default configuration:

| Setting | Value |
|---|---|
| Application URL | `http://localhost:8080` |
| MongoDB URI | `mongodb://localhost:27017/student_environment` |
| Database | `student_environment` |
| Collection | `notes` |

## Choose One Path

### Linux

Run commands marked `Linux` in a terminal. Install Java 25 through SDKMAN!.

### Windows

Run commands marked `Windows PowerShell` in PowerShell. Install Git for Windows, JDK 25 and Podman Desktop natively. WSL is not required.

SDKMAN! does not support native PowerShell. Therefore the Windows path installs JDK 25 directly. Both paths must produce Java 25 in `java -version` and in Gradle.

## Final Checklist

```text
[ ] Discord account, application and #backend channel
[ ] GitHub account
[ ] Git works
[ ] Repository is cloned
[ ] Java 25 is installed
[ ] `java -version` and `javac -version` indicate Java 25
[ ] Gradle uses Java 25
[ ] IntelliJ IDEA opens the project
[ ] Podman CLI works
[ ] Podman Desktop starts
[ ] podman run hello-world works
[ ] MongoDB Shell version is greater than 2.3
[ ] MongoDB container starts
[ ] MongoDB Shell connects to MongoDB
[ ] MongoDB Compass connects to MongoDB
[ ] Testcontainers integration test passes
[ ] Gradle build passes
[ ] Application starts from IntelliJ IDEA
[ ] GET endpoint works in Postman
[ ] POST endpoint works in Postman
[ ] Saved note is visible in MongoDB Compass
[ ] Application image builds with Podman
[ ] Application runs in Podman
[ ] Compose starts the application and MongoDB
[ ] minikube starts with the Podman driver
[ ] kubectl shows running Kubernetes pods
[ ] Application works in Kubernetes
[ ] Android Studio and emulator work
```

## 1. Discord

Install Discord:

```text
https://discord.com/download
```

Create an account or sign in. Join the Allegro UMK 25/26 server and open channel:

```text
#backend
```

Check:

```text
[ ] Discord account is active
[ ] Discord application is installed
[ ] Allegro UMK 25/26 server is available
[ ] #backend channel is available
```

## 2. GitHub

Open:

```text
https://github.com/join
```

Create an account or sign in.

Check:

```text
[ ] GitHub account is active
[ ] Student can sign in to GitHub
```

## 3. Git

### Linux

Debian or Ubuntu:

```bash
sudo apt update
sudo apt install -y git
```

For another distribution, follow:

```text
https://git-scm.com/book/en/v2/Getting-Started-Installing-Git
```

Check:

```bash
git --version
```

### Windows PowerShell

Install Git for Windows:

```text
https://git-scm.com/download/win
```

Alternatively use WinGet:

```powershell
winget install --id Git.Git -e
```

Close and reopen PowerShell, then check:

```powershell
git --version
```

Expected result: Git version is printed.

Check:

```text
[ ] Git is installed
[ ] `git --version` works
```

## 4. Clone Repository

Clone the repository using the URL provided by the course.

HTTPS example:

```bash
git clone https://github.com/<owner>/<repository>.git
```

SSH example:

```bash
git clone git@github.com:<owner>/<repository>.git
```

Enter the repository directory:

### Linux

```bash
cd <repository-directory>
git status
ls
```

### Windows PowerShell

```powershell
cd <repository-directory>
git status
Get-ChildItem
```

The directory must contain:

```text
build.gradle
gradlew
gradlew.bat
src/
```

Check:

```text
[ ] Repository was cloned
[ ] `git status` works
[ ] `build.gradle`, Gradle Wrapper and `src` exist
```

## 5. Java 25

### Linux With SDKMAN!

Install required tools:

```bash
sudo apt install -y curl zip unzip
```

Install SDKMAN!:

```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
```

Check SDKMAN!:

```bash
sdk version
```

Install Java 25:

```bash
sdk install java 25.0.2-open
sdk default java 25.0.2-open
```

Check:

```bash
java -version
javac -version
```

### Windows PowerShell

Install JDK 25 natively. Use one method.

Method A, WinGet:

```powershell
winget search OpenJDK
```

Install a listed JDK 25 distribution. Example, if available:

```powershell
winget install --id Microsoft.OpenJDK.25 -e
```

Method B, download JDK 25:

```text
https://jdk.java.net/archive
```

After installation, close and reopen PowerShell. Check:

```powershell
java -version
javac -version
```

Both commands must indicate Java 25.

### Troubleshooting

On Linux, if `25.0.2-open` is unavailable, find an available JDK 25:

```bash
sdk list java
```

Install an available Java 25 distribution and set it as default.

On Windows, if the system cannot find Java, configure `JAVA_HOME`, add `%JAVA_HOME%\bin` to the system `Path`, and open a new PowerShell window. Then repeat:

```powershell
$env:JAVA_HOME
java -version
javac -version
```

Check:

```text
[ ] Java 25 is installed
[ ] `java -version` indicates Java 25
[ ] `javac -version` indicates Java 25
[ ] On Linux, Java was installed through SDKMAN!
[ ] On Windows, `JAVA_HOME` and `Path` point to JDK 25 if required
```

## 6. Gradle Wrapper

Do not install Gradle separately. Use the wrapper included in the repository.

### Linux

```bash
chmod +x gradlew
./gradlew --version
```

### Windows PowerShell

```powershell
.\gradlew.bat --version
```

The `JVM` line must indicate Java 25.

Check:

```text
[ ] Gradle Wrapper starts
[ ] Gradle uses JVM 25
```

### Troubleshooting

If Linux reports `Permission denied` for `gradlew`:

```bash
chmod +x gradlew
./gradlew --version
```

## 7. Podman CLI and Podman Desktop

Install Podman CLI and Podman Desktop:

```text
https://podman.io/
```

### Linux

Install Podman according to the documentation for your distribution. Start Podman Desktop and check:

```bash
podman --version
podman info
```

### Windows PowerShell

Install Podman Desktop for Windows:

```text
https://podman-desktop.io/downloads
```

Open PowerShell. Initialize a machine only if one does not exist:

```powershell
podman machine init
```

Start it:

```powershell
podman machine start
```

Check:

```powershell
podman --version
podman info
```

### Hello World

Linux:

```bash
podman run hello-world
```

Windows PowerShell:

```powershell
podman run hello-world
```

Expected output contains:

```text
Hello Podman World
```

Check:

```text
[ ] `podman --version` works
[ ] `podman info` works
[ ] `podman run hello-world` works
[ ] Output contains `Hello Podman World`
[ ] Podman Desktop starts
```

### Troubleshooting

If the Podman machine is not running on Windows:

```powershell
podman machine start
podman info
```

## 8. MongoDB Shell

Install MongoDB Shell:

```text
https://www.mongodb.com/docs/mongodb-shell/install/
```

Install the Linux or Windows package according to official instructions.

### Linux

```bash
mongosh --version
```

### Windows PowerShell

```powershell
mongosh --version
```

The version must be greater than `2.3`.

Check:

```text
[ ] `mongosh --version` works
[ ] Version is greater than 2.3
```

## 9. MongoDB Container

### Linux

```bash
podman run --name student-mongo -p 27017:27017 -d docker.io/library/mongo:8
```

### Windows PowerShell

```powershell
podman run --name student-mongo -p 27017:27017 -d docker.io/library/mongo:8
```

If the container already exists:

```bash
podman start student-mongo
```

Check:

```bash
podman ps
```

Connect with MongoDB Shell:

```bash
mongosh "mongodb://localhost:27017/student_environment"
```

Run inside `mongosh`:

```javascript
db.runCommand({ ping: 1 })
```

Expected result contains:

```text
ok: 1
```

Exit:

```javascript
exit
```

Check:

```text
[ ] MongoDB container starts
[ ] `podman ps` shows `student-mongo`
[ ] `mongosh` connects
[ ] MongoDB ping returns `ok: 1`
```

### Troubleshooting

If port `27017` is already in use, list all containers:

```bash
podman ps -a
```

Stop and remove the old `student-mongo` container only if its data is no longer needed:

```bash
podman stop student-mongo
podman rm student-mongo
```

Then run the MongoDB container again.

## 10. IntelliJ IDEA

Install IntelliJ IDEA Community Edition:

```text
https://www.jetbrains.com/idea/download/
```

### Open Project

1. Start IntelliJ IDEA.
2. Select `Open` from the welcome screen.
3. Select the cloned repository directory, not the `src` directory.
4. If IntelliJ IDEA asks whether to trust the project, select `Trust Project`.
5. IntelliJ IDEA should detect `build.gradle` and import the project as a Gradle project.
6. Wait until dependency download and Gradle synchronization finish.

After import, the Gradle tool window should contain tasks such as `build`, `test` and `bootRun`.

### Find Java 25 Directory

IntelliJ IDEA needs a path to the JDK installation directory. Do not select the `java` executable itself.

#### Linux With SDKMAN!

Display the active Java directory:

```bash
sdk home java current
```

Typical result:

```text
/home/<user>/.sdkman/candidates/java/25.0.2-open
```

You can also check where the active Java command points:

```bash
readlink -f "$(which java)"
```

Select the JDK directory under `.sdkman/candidates/java`, not its `bin/java` file.

#### Windows PowerShell

Check configured Java home:

```powershell
$env:JAVA_HOME
```

If `JAVA_HOME` is empty, locate Java:

```powershell
Get-Command java | Select-Object -ExpandProperty Source
```

Typical JDK directories:

```text
C:\Program Files\Microsoft\jdk-25...
C:\Program Files\Java\jdk-25...
```

Select the JDK root directory, not `bin\java.exe`.

### Configure Project SDK

1. Open `File` -> `Project Structure`.
2. Select `Project`.
3. Open the `SDK` list.
4. If Java 25 is already listed, select it.
5. Otherwise select `Add SDK` -> `JDK`.
6. Select the Java 25 directory found in the previous step.
7. Confirm that IntelliJ IDEA recognizes the version as Java 25.
8. Set `Language level` to `SDK default` or Java 25.
9. Select `Apply`.

Check module configuration:

1. In `Project Structure`, select `Modules`.
2. Select the application module.
3. Open `Dependencies`.
4. Set `Module SDK` to `Project SDK` or Java 25.
5. Select `OK`.

### Configure Gradle JVM

Project SDK and Gradle JVM are separate settings. Both must use Java 25.

1. Open `File` -> `Settings` on Windows or `File` -> `Settings` on Linux.
2. Select `Build, Execution, Deployment` -> `Build Tools` -> `Gradle`.
3. Set `Gradle distribution` to `Wrapper`.
4. Set `Gradle JVM` to Java 25 or `Project SDK` if Project SDK is Java 25.
5. Set `Build and run using` to `Gradle`.
6. Set `Run tests using` to `Gradle`.
7. Select `Apply` and `OK`.

Reload the Gradle project:

1. Open `View` -> `Tool Windows` -> `Gradle`.
2. Select `Reload All Gradle Projects`.
3. Wait until synchronization finishes.

Successful synchronization means:

- no Gradle sync error notification,
- packages under `src/main/java` and `src/test/java` are recognized,
- Spring and JUnit imports are not marked red,
- Gradle tasks are visible in the Gradle tool window.

### Verify Java Used by IntelliJ IDEA

Open the IntelliJ IDEA terminal with `View` -> `Tool Windows` -> `Terminal`.

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

### Compile Project in IntelliJ IDEA

First compile using IntelliJ IDEA:

1. Select `Build` -> `Build Project`.
2. Wait until the build finishes.
3. Open the `Build` tool window if it is not displayed automatically.

Expected result:

```text
Build completed successfully
```

### Run Application in IntelliJ IDEA

The main class is:

```text
com.example.demo.DemoApplication
```

Before running it, confirm that the `student-mongo` container started in section 9 is still running:

```bash
podman ps
```

1. Open `src/main/java/com/example/demo/DemoApplication.java`.
2. Select the green run icon next to `main` or the class name.
3. Select `Run 'DemoApplication'`.
4. Wait for the application to start.

Expected log contains messages showing that Spring Boot started and listens on port `8080`, including a line similar to:

```text
Started DemoApplication
```

Confirm the application from a browser or Postman:

```text
http://localhost:8080/api/notes
```

Expected HTTP status is `200 OK`.

Leave the application running for the Postman and MongoDB Compass steps. It will be stopped before starting the containerized application in section 15.

### Troubleshooting

If imports are red or dependencies are missing:

1. Confirm that Gradle JVM is Java 25.
2. Select `Reload All Gradle Projects`.
3. Wait for dependency download to finish.
4. Run `Build` -> `Build Project` again.

If IntelliJ IDEA reports `invalid source release: 25`, `Unsupported class file major version` or no matching Java toolchain:

1. Check Project SDK.
2. Check Module SDK.
3. Check Gradle JVM.
4. Confirm `java -version` and Gradle `JVM` both indicate Java 25.

If port `8080` is already in use, stop the other application before starting `DemoApplication` again.

If the application cannot connect to MongoDB, confirm that `student-mongo` is running:

```bash
podman ps
```

The local application uses:

```text
mongodb://localhost:27017/student_environment
```

Check:

```text
[ ] IntelliJ IDEA starts
[ ] Project opens as a Gradle project
[ ] Project SDK is Java 25
[ ] Module SDK uses Project SDK or Java 25
[ ] Gradle JVM is Java 25
[ ] Gradle distribution uses Wrapper
[ ] Gradle synchronization finishes without errors
[ ] `DemoApplication` and test classes are visible
[ ] `Build Project` completes successfully
[ ] `DemoApplication` starts and `/api/notes` returns `200 OK`
```

## 11. Integration Tests With Testcontainers

Keep Podman running. The test starts a temporary MongoDB container automatically. It does not use the manually started `student-mongo` container.

Spring Boot `@ServiceConnection` configures the dynamic MongoDB host and port.

### Run Tests in IntelliJ IDEA

Before running tests, ensure Podman is available.

Linux:

```bash
podman info
```

Windows PowerShell:

```powershell
podman machine start
podman info
```

Run the integration test:

1. Open `src/test/java/com/example/demo/NoteControllerIntegrationTest.java`.
2. Select the green run icon next to the class name.
3. Select `Run 'NoteControllerIntegrationTest'`.
4. Wait while Testcontainers starts MongoDB.

Expected result:

- test indicator is green,
- test `savesAndReturnsNoteFromMongoDb()` passes,
- no `MongoTimeoutException` or container connection error is displayed.

Run all tests in IntelliJ IDEA:

1. Right-click `src/test/java`.
2. Select `Run 'All Tests'`.
3. Confirm that all test indicators are green.

The application started in IntelliJ IDEA may remain running on port `8080`. This does not block Testcontainers because its MongoDB container uses a dynamic port.

### Run Tests From Terminal

Linux:

```bash
./gradlew clean test
```

Windows PowerShell:

```powershell
.\gradlew.bat clean test
```

The test verifies:

```text
Spring Boot -> Testcontainers MongoDB -> POST note -> GET note
```

Expected result:

```text
BUILD SUCCESSFUL
```

Check:

```text
[ ] Testcontainers starts MongoDB
[ ] Spring Boot test context starts
[ ] POST saves a note
[ ] GET reads the saved note
[ ] Integration test passes in IntelliJ IDEA
[ ] All tests pass in IntelliJ IDEA
[ ] All tests pass from the terminal
[ ] `BUILD SUCCESSFUL` is printed
```

### Troubleshooting

If Testcontainers cannot connect to Podman, check:

Linux:

```bash
podman info
```

Windows PowerShell:

```powershell
podman machine start
podman info
```

Platform-specific Testcontainers configuration:

```text
https://java.testcontainers.org/supported_docker_environment/
```

Do not change the test to use a fixed MongoDB port. Testcontainers configures a dynamic port.

## 12. Build Application

This is the final build verification. It compiles the application, runs the tests again and creates the executable JAR.

### Linux

```bash
./gradlew clean build
```

### Windows PowerShell

```powershell
.\gradlew.bat clean build
```

Expected result:

```text
BUILD SUCCESSFUL
```

The JAR is created in:

```text
build/libs/
```

Check:

```text
[ ] Application compiles
[ ] Tests pass
[ ] JAR exists in `build/libs`
[ ] `BUILD SUCCESSFUL` is printed
```

## 13. Test API in Postman

Install Postman:

```text
https://www.postman.com/downloads/
```

### GET Notes

Send:

```http
GET http://localhost:8080/api/notes
```

Expected status:

```text
200 OK
```

The response is a JSON array. If no notes were added previously, it is empty:

```json
[]
```

If notes already exist, the array contains those notes. Existing data is not an error.

Check:

```text
[ ] Postman starts
[ ] GET returns `200 OK`
[ ] Response is a JSON array
```

### POST Note

Send:

```http
POST http://localhost:8080/api/notes
Content-Type: application/json
```

Body:

```json
{
  "text": "Test polaczenia z MongoDB",
  "author": "Student"
}
```

Expected status:

```text
201 Created
```

Expected response:

```json
{
  "id": "...",
  "text": "Test polaczenia z MongoDB",
  "author": "Student"
}
```

The `id` is generated by MongoDB. Send GET again and check that the response contains the saved note.

Check:

```text
[ ] POST returns `201 Created`
[ ] Response contains `id`, `text` and `author`
[ ] GET returns the saved note
```

## 14. Verify Data in MongoDB Compass

Install MongoDB Compass:

```text
https://www.mongodb.com/try/download/compass
```

Open Compass and create a connection using:

```text
mongodb://localhost:27017
```

Confirm that Compass connects successfully. Then refresh the database list and open:

```text
student_environment -> notes
```

Check:

```text
[ ] Compass starts
[ ] New connection dialog opens
[ ] Compass connects to `mongodb://localhost:27017`
[ ] `student_environment` database exists
[ ] `notes` collection exists
[ ] Saved document is visible
[ ] Document fields match the POST request
```

Complete data flow:

```text
Postman -> Spring Boot -> MongoDB -> MongoDB Compass
```

### Troubleshooting

If Compass cannot connect, confirm that MongoDB is running:

```bash
podman ps
```

Use this connection string:

```text
mongodb://localhost:27017
```

## 15. Build and Run Application in Podman

### Build Image

Build the application first, using the command from section 12.

### Linux

```bash
podman build -t student-environment-check:latest .
podman images
```

### Windows PowerShell

```powershell
podman build -t student-environment-check:latest .
podman images
```

Check:

```text
[ ] Image builds without an error
[ ] `student-environment-check` is listed
```

### Run Application Container

Ensure MongoDB is running:

```bash
podman start student-mongo
```

Stop `DemoApplication` in IntelliJ IDEA before continuing. Otherwise port `8080` is already occupied.

### Linux

```bash
podman run --rm --name student-environment-app \
  -p 8080:8080 \
  -e SPRING_MONGODB_URI=mongodb://host.containers.internal:27017/student_environment \
  student-environment-check:latest
```

### Windows PowerShell

```powershell
podman run --rm --name student-environment-app -p 8080:8080 -e SPRING_MONGODB_URI=mongodb://host.containers.internal:27017/student_environment student-environment-check:latest
```

Send GET and POST from Postman again.

Check:

```text
[ ] Application container starts
[ ] Port 8080 is available
[ ] GET endpoint works
[ ] POST endpoint works
[ ] Data is saved in MongoDB
```

### Troubleshooting

If port `8080` is already in use, stop the application running from IntelliJ IDEA or another container before retrying.

If the application container cannot connect to MongoDB, confirm that `student-mongo` is running and use:

```text
SPRING_MONGODB_URI=mongodb://host.containers.internal:27017/student_environment
```

## 16. Compose

Compose starts both MongoDB and the application from `compose.yaml`.

Stop the application container from section 15 and the standalone MongoDB container to release ports `8080` and `27017`:

```bash
podman ps
```

The application container from section 15 uses `--rm`, so stopping it removes it automatically. If it is still running in another terminal, stop it with `Ctrl+C`. If `student-mongo` is listed, stop it:

```bash
podman stop student-mongo
```

### Linux

Install a Compose provider according to Podman Desktop documentation:

```text
https://podman-desktop.io/docs/compose/setting-up-compose
```

Check Compose:

```bash
podman compose version
```

Build and start the services:

```bash
podman compose up --build -d
```

### Windows PowerShell

Configure Compose in Podman Desktop:

```text
https://podman-desktop.io/docs/compose/setting-up-compose
```

Check Compose:

```powershell
podman compose version
```

Build and start the services:

```powershell
podman compose up --build -d
```

Check both services:

```bash
podman compose ps
```

Run this and the following Compose commands in the same Linux terminal or Windows PowerShell used for `podman compose up`.

Send the GET and POST requests from section 13 again. Both must work.

Stop Compose after verification:

```bash
podman compose down
```

The named MongoDB volume remains for later runs. Use `podman compose down -v` only when you intentionally want to delete Compose database data.

Check:

```text
[ ] `podman compose version` works
[ ] Compose builds the application image
[ ] MongoDB service starts
[ ] Application service starts
[ ] GET returns `200 OK`
[ ] POST returns `201 Created`
[ ] `podman compose down` stops the services
```

### Troubleshooting

If Compose reports that ports `8080` or `27017` are already in use, stop standalone containers and the application running from IntelliJ IDEA:

```bash
podman ps
podman stop student-mongo
```

If the application service exits, inspect logs:

```bash
podman compose logs app
podman compose logs mongo
```

If Compose reports `docker-credential-desktop`, it usually indicates stale configuration left by a previous Docker Desktop installation. This is not expected on a clean student environment. Ask the instructor for help before changing Docker configuration.

## 17. minikube

Install minikube:

```text
https://minikube.sigs.k8s.io/docs/start/
```

After installation, open a new terminal and check:

```bash
minikube version
```

Before starting minikube, ensure that Podman is available, at least 2 CPU cores and 4 GB RAM are free, and the first image download can access the internet. The first cluster startup downloads several hundred megabytes of Kubernetes images.

### Linux

Install minikube using the Linux instructions. Start the cluster:

```bash
minikube start --driver=podman --container-runtime=cri-o
```

### Windows PowerShell

Install minikube for Windows. WinGet example:

```powershell
winget install Kubernetes.minikube
```

Ensure the Podman machine is running:

```powershell
podman machine start
podman info
```

Start the cluster:

```powershell
minikube start --driver=podman --container-runtime=cri-o
```

Check cluster status:

```bash
minikube status
```

Expected components report `Running`.

Check:

```text
[ ] `minikube version` works
[ ] Cluster starts with the Podman driver
[ ] `minikube status` reports running components
```

### Troubleshooting

If startup fails, inspect status and logs:

```bash
minikube status
minikube logs
```

On Windows, confirm that Podman machine works:

```powershell
podman machine start
podman info
```

On Windows, common causes of failure are:

- Podman machine is stopped or has insufficient CPU or memory.
- First-run image downloads are blocked by a proxy, firewall or unstable connection.
- An incomplete old minikube profile remains after an interrupted startup.

Check available Podman-machine resources:

```powershell
podman machine list
```

If Podman machine has insufficient resources, stop it and create a new machine with enough resources according to Podman Desktop documentation. This deletes containers stored in the replaced machine.

To recreate a broken local cluster:

```bash
minikube delete
minikube start --driver=podman --container-runtime=cri-o
```

## 18. kubectl and Kubernetes Deployment

Install kubectl:

Linux:

```text
https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/
```

Windows:

```text
https://kubernetes.io/docs/tasks/tools/install-kubectl-windows/
```

Check kubectl and system pods:

```bash
kubectl version --client
kubectl get pods --all-namespaces
```

The output must contain pods from namespace:

```text
kube-system
```

Build the application JAR before creating the minikube image:

### Linux

```bash
./gradlew clean build
```

### Windows PowerShell

```powershell
.\gradlew.bat clean build
```

Build the application image directly inside minikube:

```bash
minikube image build -t student-environment-check:latest .
```

This avoids differences between the local Podman image store and the minikube image store.

Deploy MongoDB and the application:

```bash
kubectl apply -f k8s/mongo.yaml
kubectl apply -f k8s/app.yaml
```

Wait for deployments:

```bash
kubectl rollout status deployment/mongo
kubectl rollout status deployment/student-environment-app
```

Check resources:

```bash
kubectl get pods
kubectl get services
```

Pods must have status `Running` and readiness `1/1`.

Forward the application port:

```bash
kubectl port-forward service/student-environment-app 8080:8080
```

Keep this terminal open. Send GET and POST from Postman to:

```text
http://localhost:8080/api/notes
```

Stop port forwarding with `Ctrl+C`.

Remove application resources after verification:

```bash
kubectl delete -f k8s/app.yaml
kubectl delete -f k8s/mongo.yaml
```

Stop minikube:

```bash
minikube stop
```

Check:

```text
[ ] `kubectl version --client` works
[ ] `kubectl get pods --all-namespaces` shows `kube-system` pods
[ ] Application image builds inside minikube
[ ] MongoDB pod is `Running` and ready
[ ] Application pod is `Running` and ready
[ ] GET and POST work through `kubectl port-forward`
[ ] Kubernetes resources are removed
[ ] minikube stops successfully
```

### Troubleshooting

If a pod does not start, inspect it:

```bash
kubectl get pods
kubectl describe pod <pod-name>
kubectl logs deployment/student-environment-app
kubectl logs deployment/mongo
```

If the application reports `ErrImageNeverPull`, build the image inside minikube again and restart the deployment:

```bash
minikube image build -t student-environment-check:latest .
kubectl rollout restart deployment/student-environment-app
```

If port `8080` is occupied, stop IntelliJ IDEA, Compose or the standalone application container before running `kubectl port-forward`.

## 19. Android Studio

Install Android Studio:

```text
https://developer.android.com/studio/install
```

Create a basic empty application:

```text
https://developer.android.com/studio/projects/create-project
```

Create and start an emulator:

```text
https://developer.android.com/studio/run/managing-avds
https://developer.android.com/studio/run/emulator
```

Check:

```text
[ ] Android Studio starts
[ ] Empty project is created
[ ] Emulator is created and starts
[ ] Basic application runs without errors
```

Android is an independent installation check. It does not need to connect to this backend.

## Final Result

Environment is ready when all relevant checklist items are marked and the commands for the selected platform succeed.

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
