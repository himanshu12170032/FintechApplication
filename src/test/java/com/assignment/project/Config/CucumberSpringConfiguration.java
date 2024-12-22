package com.assignment.project.Config;

import com.assignment.project.ProjectApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = ProjectApplication.class)
public class CucumberSpringConfiguration {

}
