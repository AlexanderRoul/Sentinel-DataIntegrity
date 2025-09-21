# 🚀 Sentinel - Data Integrity Automation Framework  

### 📌 Overview  
**Sentinel** is a Maven-based automation framework designed to validate **data integrity** between the **GoREST API** and a simulated **SaaS Admin Dashboard UI**.  
It ensures that the data created/updated in the backend (API) is correctly reflected in the frontend (UI).  

---

## 🛠️ Tech Stack  
- Java 17  
- Maven  
- TestNG  
- Selenium WebDriver  
- REST Assured  
- Extent Reports  
- GitHub Actions (CI/CD)  

---

## 📂 Project Structure  
sentinel/
├── src
│ ├── main/java/com/sentinel/api # API client classes
│ ├── main/java/com/sentinel/ui # Selenium Page Object classes
│ ├── main/java/com/sentinel/utils # Helpers (Extent, Config, Listeners)
│ └── test/java/com/sentinel/tests # Test classes
│
├── src/test/resources/testng.xml # TestNG Suite configuration
├── pom.xml # Maven dependencies
├── README.md # Project documentation
└── .github/workflows/ci-pipeline.yml # GitHub Actions pipeline
