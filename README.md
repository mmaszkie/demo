# Student Environment Check

Gotowa aplikacja Spring Boot do sprawdzenia środowiska. Nie trzeba dopisywać kodu.

Obsługiwane środowiska: Linux oraz Windows PowerShell. Na Windows używaj natywnego JDK 25; SDKMAN! działa tylko na Linuxie.

## Aplikacja

| Metoda | Adres | Cel |
|---|---|---|
| `GET` | `http://localhost:8080/api/notes` | Pobiera notatki z MongoDB |
| `POST` | `http://localhost:8080/api/notes` | Zapisuje notatkę w MongoDB |

Domyślna baza: `student_environment`, kolekcja: `notes`.

## Checklista

```text
[ ] Discord: konto, aplikacja, Allegro UMK 26/27, #backend
[ ] GitHub: aktywne konto
[ ] Git: działa
[ ] Repozytorium: sklonowane
[ ] Java i javac: wersja 25
[ ] Gradle: JVM 25
[ ] IntelliJ IDEA: import, kompilacja, uruchomienie
[ ] Podman i Podman Desktop: działają
[ ] Podman hello-world: działa
[ ] mongosh: wersja > 2.3
[ ] MongoDB: kontener i połączenie działają
[ ] Testcontainers: testy działają z IDE i terminala
[ ] Postman: GET 200, POST 201
[ ] MongoDB Compass: widzi zapisany dokument
[ ] Podman: obraz i kontener aplikacji działają
[ ] Compose: aplikacja i MongoDB działają
[ ] minikube: klaster działa z Podmanem
[ ] kubectl: pody Kubernetes działają
[ ] Android Studio: emulator uruchamia aplikację
```

## 1. Discord i GitHub

1. Zainstaluj Discord: <https://discord.com/download>.
2. Załóż lub zaloguj konto.
3. Dołącz do serwera `Allegro UMK 26/27` i kanału `#backend`.
4. Załóż lub zaloguj konto GitHub: <https://github.com/join>.

## 2. Git i repozytorium

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

SSH, jeśli jest skonfigurowane:

```bash
git clone git@github.com:mmaszkie/demo.git
```

Sprawdź:

```text
[ ] `git --version` wyświetla wersję Git
[ ] `git status` kończy się bez błędu
[ ] Katalog zawiera `build.gradle`, `gradlew`, `gradlew.bat` i `src`
```

## 3. Java 25

### Linux: SDKMAN!

```bash
sudo apt install -y curl zip unzip
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 25.0.2-open
sdk default java 25.0.2-open
java -version
javac -version
```

Jeśli `25.0.2-open` nie jest dostępne:

```bash
sdk list java
```

Zainstaluj dostępną dystrybucję JDK 25.

### Windows PowerShell

Zainstaluj JDK 25 przez WinGet lub z <https://jdk.java.net/archive>:

```powershell
winget search OpenJDK
winget install --id Microsoft.OpenJDK.25 -e
java -version
javac -version
```

Jeśli PowerShell nie znajduje Java, ustaw `JAVA_HOME`, dodaj `%JAVA_HOME%\bin` do `Path`, otwórz nowy terminal i ponów komendy.

Sprawdź:

```text
[ ] `java -version` wskazuje Java 25
[ ] `javac -version` wskazuje Java 25
[ ] Linux: Java została zainstalowana przez SDKMAN!
[ ] Windows: JDK 25 jest dostępne w `Path`
```

## 4. Gradle Wrapper

### Linux

```bash
chmod +x gradlew
./gradlew --version
```

### Windows PowerShell

```powershell
.\gradlew.bat --version
```

Sprawdź:

```text
[ ] Gradle Wrapper uruchamia się
[ ] Linia `JVM` wskazuje Java 25
[ ] Nie używasz osobnej instalacji Gradle
```

Troubleshooting Linux: `Permission denied` naprawia `chmod +x gradlew`.

## 5. Podman i Podman Desktop

Zainstaluj Podman oraz Podman Desktop: <https://podman.io/>.

### Linux

Zainstaluj pakiet dla swojej dystrybucji, potem:

```bash
podman --version
podman info
podman run hello-world
```

### Windows PowerShell

Zainstaluj Podman Desktop: <https://podman-desktop.io/downloads>.

```powershell
podman machine init
podman machine start
podman --version
podman info
podman run hello-world
```

Sprawdź:

```text
[ ] `podman --version` działa
[ ] `podman info` działa
[ ] `podman run hello-world` zawiera `Hello Podman World`
[ ] Podman Desktop uruchamia się bez błędu
```

Troubleshooting Windows: jeśli machine istnieje, pomiń `podman machine init`; jeśli jest zatrzymana, uruchom `podman machine start`.

## 6. MongoDB Shell i kontener

Zainstaluj MongoDB Shell: <https://www.mongodb.com/docs/mongodb-shell/install/>.

Sprawdź w terminalu, gdzie zainstalowano `mongosh`:

```bash
mongosh --version
```

Sprawdź: `mongosh --version` zwraca wersję większą od `2.3`.

Uruchom MongoDB w terminalu używanym do Podmana:

```bash
podman run --name student-mongo -p 27017:27017 -d docker.io/library/mongo:8
podman ps
mongosh "mongodb://localhost:27017/student_environment"
```

W `mongosh`:

```javascript
db.runCommand({ ping: 1 })
exit
```

Sprawdź:

```text
[ ] `podman ps` pokazuje `student-mongo`
[ ] `mongosh` łączy się z `localhost:27017`
[ ] `db.runCommand({ ping: 1 })` zwraca `ok: 1`
```

Troubleshooting: jeśli port `27017` jest zajęty, sprawdź `podman ps -a`. Zatrzymaj i usuń stary `student-mongo` tylko gdy dane nie są potrzebne:

```bash
podman stop student-mongo
podman rm student-mongo
```

## 7. IntelliJ IDEA

Zainstaluj IntelliJ IDEA Community: <https://www.jetbrains.com/idea/download/>.

1. Wybierz `Open`, wskaż katalog `demo`, zaakceptuj `Trust Project`.
2. Poczekaj na import Gradle; w oknie Gradle pojawią się zadania `build`, `test` i `bootRun`.
3. Otwórz `File` -> `Project Structure` -> `Project` i ustaw `Project SDK` na JDK 25.
4. Jeśli Java 25 nie jest widoczna: `Add SDK` -> `JDK`, wybierz katalog główny JDK, nie `bin/java` ani `bin\java.exe`.
5. Otwórz `Modules` -> `Dependencies` i ustaw `Module SDK` na `Project SDK`.
6. Otwórz `File` -> `Settings` -> `Build, Execution, Deployment` -> `Build Tools` -> `Gradle`.
7. Ustaw `Gradle distribution` na `Wrapper`, `Gradle JVM` na Java 25, `Build and run using` i `Run tests using` na `Gradle`.
8. W oknie Gradle wybierz `Reload All Gradle Projects`, potem `Build` -> `Build Project`.

Ścieżka JDK Linux z SDKMAN!:

```bash
sdk home java current
```

Ścieżka JDK Windows:

```powershell
$env:JAVA_HOME
Get-Command java | Select-Object -ExpandProperty Source
```

Sprawdź:

```text
[ ] Importy Spring i JUnit nie są czerwone
[ ] `Build Project` kończy się sukcesem
[ ] Project SDK, Module SDK i Gradle JVM wskazują Java 25
[ ] Projekt używa Gradle Wrappera
```

Uruchom `DemoApplication` zielonym przyciskiem obok `main`. Wcześniej sprawdź, czy `student-mongo` działa:

```bash
podman ps
```

Sprawdź: log zawiera `Started DemoApplication`, a `http://localhost:8080/api/notes` zwraca `200 OK`. Pozostaw aplikację uruchomioną do kroków Postman i Compass.

Troubleshooting: przy czerwonych importach ponownie załaduj Gradle. Przy `invalid source release: 25` sprawdź Project SDK, Module SDK i Gradle JVM. Przy błędzie MongoDB sprawdź `podman ps` i URI `mongodb://localhost:27017/student_environment`.

## 8. Testcontainers

Testy uruchamiają tymczasowy, prawdziwy kontener MongoDB. Wymagają działającego Podmana, ale nie używają `student-mongo`.

### IntelliJ IDEA

1. Otwórz `src/test/java/com/example/demo/NoteControllerIntegrationTest.java`.
2. Uruchom `NoteControllerIntegrationTest` zielonym przyciskiem.
3. Uruchom wszystkie testy przez kliknięcie prawym na `src/test/java` -> `Run 'All Tests'`.

### Terminal

Linux:

```bash
./gradlew clean test
```

Windows PowerShell:

```powershell
.\gradlew.bat clean test
```

Test sprawdza pełny przepływ: `Spring Boot -> Testcontainers MongoDB -> POST -> GET`.

Sprawdź:

```text
[ ] `NoteControllerIntegrationTest` jest zielony w IntelliJ IDEA
[ ] Wszystkie testy są zielone w IntelliJ IDEA
[ ] `clean test` w terminalu kończy się `BUILD SUCCESSFUL`
```

Troubleshooting: sprawdź `podman info`. Na Windows uruchom `podman machine start`, potem ponów test. Nie ustawiaj stałego portu MongoDB dla Testcontainers.

## 9. Build aplikacji

Linux:

```bash
./gradlew clean build
```

Windows PowerShell:

```powershell
.\gradlew.bat clean build
```

Sprawdź:

```text
[ ] Build kończy się `BUILD SUCCESSFUL`
[ ] W `build/libs/` istnieje plik JAR
```

## 10. Postman i MongoDB Compass

Zainstaluj Postman: <https://www.postman.com/downloads/>. Aplikacja z IntelliJ IDEA i `student-mongo` muszą nadal działać.

1. Wyślij:

```http
GET http://localhost:8080/api/notes
```

Oczekiwany status: `200 OK`. Odpowiedź jest JSON-ową tablicą; może być pusta, gdy brak wcześniejszych notatek.

2. Wyślij:

```http
POST http://localhost:8080/api/notes
Content-Type: application/json
```

```json
{
  "text": "Test polaczenia z MongoDB",
  "author": "Student"
}
```

Oczekiwany status: `201 Created`; odpowiedź zawiera `id`, `text` i `author`. Ponów GET i sprawdź dodaną notatkę.

3. Ponów GET. Odpowiedź zawiera dodaną notatkę.

Zainstaluj MongoDB Compass: <https://www.mongodb.com/try/download/compass>. Połącz z:

```text
mongodb://localhost:27017
```

Odśwież i otwórz `student_environment` -> `notes`. Dokument z Postmana musi być widoczny. To potwierdza przepływ `Postman -> Spring Boot -> MongoDB -> Compass`.

Troubleshooting Compass: uruchom `podman ps` i potwierdź działanie `student-mongo`.

## 11. Podman: obraz i kontener aplikacji

Zatrzymaj aplikację z IntelliJ IDEA, aby zwolnić port `8080`. Upewnij się, że `student-mongo` nadal działa.

Zbuduj obraz w terminalu Podmana:

```bash
podman build -t student-environment-check:latest .
podman images
```

Uruchom aplikację:

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

Ponów GET i POST z kroku 10.

Sprawdź:

```text
[ ] Obraz `student-environment-check` istnieje
[ ] Kontener aplikacji uruchamia się na porcie 8080
[ ] GET zwraca `200 OK`
[ ] POST zwraca `201 Created`
```

Troubleshooting: przy zajętym `8080` zatrzymaj aplikację z IDE lub inny kontener. Przy błędzie MongoDB sprawdź `student-mongo` i wartość `SPRING_MONGODB_URI`.

## 12. Compose

Compose uruchamia MongoDB i aplikację z `compose.yaml`.

1. Zatrzymaj aplikację z kroku 11 przez `Ctrl+C` i standalone MongoDB:

```bash
podman stop student-mongo
```

2. Skonfiguruj Compose według <https://podman-desktop.io/docs/compose/setting-up-compose>.
3. Uruchom w tym samym terminalu:

```bash
podman compose version
podman compose up --build -d
podman compose ps
```

4. Ponów GET i POST z kroku 10.
5. Zatrzymaj usługi:

```bash
podman compose down
```

Sprawdź:

```text
[ ] `podman compose version` działa
[ ] `podman compose ps` pokazuje usługi `app` i `mongo`
[ ] GET zwraca `200 OK`
[ ] POST zwraca `201 Created`
[ ] `podman compose down` zatrzymuje usługi
```

Troubleshooting: przy konflikcie portów zatrzymaj IntelliJ, `student-mongo` i inne kontenery. Logi:

```bash
podman compose logs app
podman compose logs mongo
```

`docker-credential-desktop` oznacza starą konfigurację Docker Desktop; na czystym środowisku nie powinno wystąpić.

## 13. minikube

Zainstaluj minikube: <https://minikube.sigs.k8s.io/docs/start/>.

Wymagania: działający Podman, co najmniej 2 CPU, 4 GB wolnego RAM i dostęp do internetu na pierwsze pobranie obrazów.

Linux:

```bash
minikube version
minikube start --driver=podman --container-runtime=cri-o
minikube status
```

Windows PowerShell:

```powershell
winget install Kubernetes.minikube
podman machine start
podman info
minikube version
minikube start --driver=podman --container-runtime=cri-o
minikube status
```

Sprawdź:

```text
[ ] `minikube version` działa
[ ] `minikube status` pokazuje komponenty `Running`
[ ] Klaster używa drivera Podman i runtime CRI-O
```

Troubleshooting: użyj `minikube logs`. Po przerwanym starcie wykonaj:

```bash
minikube delete
minikube start --driver=podman --container-runtime=cri-o
```

Na Windows sprawdź `podman machine list`; częste problemy to zbyt mało CPU/RAM, zatrzymana machine, proxy lub firewall blokujący obrazy.

## 14. kubectl i Kubernetes

Zainstaluj kubectl:

- Linux: <https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/>
- Windows: <https://kubernetes.io/docs/tasks/tools/install-kubectl-windows/>

Sprawdź klienta i pody systemowe:

```bash
kubectl version --client
kubectl get pods --all-namespaces
```

Oczekiwane są pody w `kube-system`.

Zbuduj JAR i obraz wewnątrz minikube:

### Linux

```bash
./gradlew clean build
minikube image build -t student-environment-check:latest .
```

### Windows PowerShell

```powershell
.\gradlew.bat clean build
minikube image build -t student-environment-check:latest .
```

Wdróż aplikację:

```bash
kubectl apply -f k8s/mongo.yaml
kubectl apply -f k8s/app.yaml
kubectl rollout status deployment/mongo
kubectl rollout status deployment/student-environment-app
kubectl get pods
kubectl get services
```

Pody muszą mieć stan `Running` i gotowość `1/1`.

Przekieruj port w osobnym terminalu:

```bash
kubectl port-forward service/student-environment-app 8080:8080
```

Przez Postmana wykonaj GET i POST z kroku 10. Zatrzymaj forwarding przez `Ctrl+C`, a potem usuń zasoby:

```bash
kubectl delete -f k8s/app.yaml
kubectl delete -f k8s/mongo.yaml
minikube stop
```

Sprawdź:

```text
[ ] `kubectl get pods --all-namespaces` pokazuje pody `kube-system`
[ ] Pody `mongo` i `student-environment-app` mają stan `Running` oraz `1/1`
[ ] GET i POST działają przez `kubectl port-forward`
[ ] Zasoby i minikube są zatrzymane po teście
```

Troubleshooting:

```bash
kubectl get pods
kubectl describe pod <pod-name>
kubectl logs deployment/student-environment-app
kubectl logs deployment/mongo
```

Przy `ErrImageNeverPull` uruchom ponownie `minikube image build -t student-environment-check:latest .`, a potem:

```bash
kubectl rollout restart deployment/student-environment-app
```

## 15. Android Studio

Zainstaluj Android Studio: <https://developer.android.com/studio/install>.

1. Utwórz pustą aplikację: <https://developer.android.com/studio/projects/create-project>.
2. Utwórz emulator: <https://developer.android.com/studio/run/managing-avds>.
3. Uruchom aplikację na emulatorze: <https://developer.android.com/studio/run/emulator>.

Kryterium: Android Studio, emulator i pusta aplikacja uruchamiają się bez błędów.

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

Oczekiwany wynik:

```text
BUILD SUCCESSFUL
```
