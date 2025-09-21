package com.sentinel.bdd.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.sentinel.bdd.steps"}
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests { }
