/*
Project Struktur:
- src/
  - main/
    - java/
      - com.example.portfolio/
        - controllers/
          - HomeController.java
        - models/
          - Project.java
        - services/
          - ProjectService.java
        - PortfolioApplication.java
    - resources/
      - templates/
        - index.html
        - about.html
        - projects.html
      - static/
        - css/
        - js/
      - application.properties
*/

// PortfolioApplication.java
package com.example.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortfolioApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortfolioApplication.class, args);
    }
}

// HomeController.java
package com.example.portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "Sendi Supriatna");
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("projects", ProjectService.getAllProjects());
        return "projects";
    }
}

// Project.java
package com.example.portfolio.models;

public class Project {
    private String title;
    private String description;

    public Project(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
}

// ProjectService.java
package com.example.portfolio.services;

import com.example.portfolio.models.Project;
import java.util.Arrays;
import java.util.List;

public class ProjectService {
    public static List<Project> getAllProjects() {
        return Arrays.asList(
            new Project("Website Portofolio", "Situs pribadi dengan data project dan blog."),
            new Project("Aplikasi Catatan", "Aplikasi Java untuk mencatat tugas dan ide.")
        );
    }
}

// application.properties
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.cache=false

/*
Contoh index.html (Thymeleaf):
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Home</title>
</head>
<body>
    <h1>Selamat datang, <span th:text="${name}"></span>!</h1>
    <a href="/about">Tentang Saya</a> | <a href="/projects">Proyek</a>
</body>
</html>
*/
