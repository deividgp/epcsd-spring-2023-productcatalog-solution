package edu.uoc.epcsd.productcatalog.exercise3;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "edu.uoc.epcsd.productcatalog.domain.service")
public class NamingConventionTest {

    @ArchTest
    static ArchRule serviceimpl_should_end_with_serviceimpl =
            classes()
                    .that().areAnnotatedWith(Service.class)
                    .should().haveSimpleNameEndingWith("ServiceImpl");
}
