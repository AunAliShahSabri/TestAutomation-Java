# Selenium Java • POM + BDD (Cucumber) • TestNG • Maven • Jenkins

A production‑ready test automation boilerplate using **Selenium (Java)** with the **Page Object Model**, **Cucumber BDD**, **TestNG**, and **Maven**, designed to run locally or in CI (Jenkins, GitHub Actions, etc.).

---

## 📦 Tech Stack
- **Language:** Java (17+ recommended)
- **Test Runner:** TestNG
- **BDD:** Cucumber JVM
- **UI Automation:** Selenium 4
- **Build:** Maven
- **Reports:** Cucumber HTML/JSON, TestNG Surefire reports
- **CI:** Jenkins (sample pipeline included)

> Uses Selenium Manager (built into Selenium 4) so you **don’t need to download browser drivers** manually.

---

## 🗂 Project Structure
```
your-project/
├─ pom.xml
├─ testng.xml
├─ src
│  ├─ main
│  │  └─ java
│  │     └─ com.yourco.project
│  │        ├─ core/                    # Framework + driver plumbing
│  │        │  ├─ DriverFactory.java
│  │        │  ├─ DriverManager.java
│  │        │  ├─ BasePage.java
│  │        │  └─ Waiter.java
│  │        ├─ pages/                   # Page Object Model classes
│  │        │  ├─ LoginPage.java
│  │        │  ├─ DashboardPage.java
│  │        │  └─ (…other pages…).java
│  │        ├─ utils/                   # Reusable helpers
│  │        │  ├─ Config.java
│  │        │  ├─ PropsReader.java
│  │        │  ├─ ScreenshotUtil.java
│  │        │  └─ TestDataUtil.java
│  │        └─ enums/
│  │           └─ BrowserType.java
│  └─ test
│     ├─ java
│     │  └─ com.yourco.project
│     │     ├─ steps/                   # Cucumber step definitions
│     │     │  ├─ LoginSteps.java
│     │     │  └─ CommonSteps.java
│     │     ├─ hooks/                   # Before/After hooks
│     │     │  └─ Hooks.java
│     │     └─ runners/                 # TestNG+Cucumber runner(s)
│     │        └─ CucumberTestRunner.java
│     └─ resources
│        ├─ features/                   # .feature files (BDD)
│        │  └─ login/login.feature
│        ├─ config/config.properties    # baseUrl, browser, timeouts, etc.
│        ├─ testdata/users.csv
│        └─ log4j2.xml
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17+ (`java -version`)
- Maven 3.9+ (`mvn -v`)
- A modern browser (Chrome/Edge/Firefox)

### Install dependencies & run a smoke
```bash
mvn clean test -Dtest=CucumberTestRunner
```
or simply:
```bash
mvn clean test
```

---

## ⚙️ Configuration

### `src/test/resources/config/config.properties`
```properties
baseUrl=https://example.com
browser=chrome          # chrome | firefox | edge
headless=false
implicitWait=0          # prefer explicit waits
explicitWait=15
remoteUrl=              # e.g., http://localhost:4444/wd/hub (Selenium Grid)
reuseBrowser=false      # true = one browser per test run (same thread)
```

You can override any property with JVM system properties:
```bash
mvn test -Dbrowser=edge -Dheadless=true -DbaseUrl=https://staging.example.com
```

### Cucumber Runner (TestNG)
```java
@CucumberOptions(
  features = "src/test/resources/features",
  glue = {"com.yourco.project.steps", "com.yourco.project.hooks"},
  plugin = {"pretty","html:target/cucumber-report.html","json:target/cucumber.json"},
  monochrome = true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {}
```

### Sample Feature
```gherkin
Feature: Login
  Scenario: Valid user logs in
    Given I am on the login page
    When I login as "standard_user" with password "secret"
    Then I should see the dashboard
```

---

## 🧭 How Tests Are Structured (POM)
- **Pages** expose **actions** (not raw elements). Keep locators private.
- Reuse wait helpers from `BasePage` (e.g., `click(By)`, `type(By, text)`, `visible(By)`).
- Keep step definitions small; delegate to page objects.

Minimal `LoginPage` (plain POM style):
```java
public class LoginPage extends BasePage {
  private final By user = By.id("username");
  private final By pass = By.id("password");
  private final By submit = By.cssSelector("button[type='submit']");

  public LoginPage(WebDriver driver) { super(driver); }

  public DashboardPage loginAs(String u, String p) {
    type(user, u);
    type(pass, p);
    click(submit);
    return new DashboardPage(driver);
  }
}
```

---

## ▶️ Running Tests

### Local (headed / headless)
```bash
# Default (Chrome headed)
mvn clean test

# Headless
mvn clean test -Dheadless=true
```

### Choose browser
```bash
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
```

### Selenium Grid / Selenoid
```bash
mvn clean test -DremoteUrl=http://localhost:4444/wd/hub -Dbrowser=chrome -Dheadless=true
```

### Reuse a single browser per run
```bash
mvn clean test -DreuseBrowser=true
```

### Filter by tags
```bash
# In the runner: @CucumberOptions(tags = "@smoke or @regression")
mvn clean test -Dcucumber.filter.tags='@smoke'
```

---

## 📊 Reports
- **Cucumber HTML**: `target/cucumber-report.html`
- **Cucumber JSON**: `target/cucumber.json`
- **Surefire/TestNG**: `target/surefire-reports`

You can plug in third‑party reporters (e.g., ExtentReports, Allure) if desired.

---

## 🏗 Jenkins (Declarative Pipeline)
```groovy
pipeline {
  agent any
  options { timestamps(); ansiColor('xterm') }
  tools { jdk 'jdk17'; maven 'maven3' }

  parameters {
    string(name: 'BROWSER',  defaultValue: 'chrome', description: 'chrome|firefox|edge')
    booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Run headless')
    string(name: 'BASE_URL', defaultValue: 'https://example.com', description: 'Target URL')
    string(name: 'TAGS',     defaultValue: '', description: 'Cucumber tags, e.g. @smoke')
  }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }
    stage('Build & Test') {
      steps {
        sh """
          mvn -q -B -e clean test \
            -Dbrowser=${params.BROWSER} \
            -Dheadless=${params.HEADLESS} \
            -DbaseUrl=${params.BASE_URL} \
            ${params.TAGS ? "-Dcucumber.filter.tags='${params.TAGS}'" : ""}
        """
      }
    }
    stage('Publish Reports') {
      post { always { archiveArtifacts artifacts: 'target/**/*', fingerprint: true } }
    }
  }
  post {
    always {
      junit 'target/surefire-reports/*.xml'
      publishHTML(target: [
        reportName: 'Cucumber',
        reportDir: 'target',
        reportFiles: 'cucumber-report.html',
        keepAll: true, alwaysLinkToLastBuild: true, allowMissing: true
      ])
    }
  }
}
```

---

## 🧰 Troubleshooting
- **`Repository not found` when pushing**: check your remote URL (`git remote -v`) and credentials (PAT for HTTPS, or SSH keys).
- **Browser doesn’t open**: confirm Java/Maven versions; check driver setup (Selenium Manager handles drivers automatically).
- **StaleElementReferenceException**: avoid caching `WebElement`s; re‑locate with waits in `BasePage` methods.
- **Parallel runs**: use `ThreadLocal<WebDriver>` in `DriverManager` (already implemented) and avoid static state in pages/steps.

---

## 🤝 Contributing
1. Fork & clone.
2. Create a feature branch: `git checkout -b feat/better-reporting`
3. Commit with conventional messages (optional): `feat(report): add pie chart`
4. PR into `main`.

---

## 📜 License
MIT (or your company’s license).

---

## 🙌 Credits
Built with ❤️ by your‑team. PRs welcome!
