package edu.uoc.epcsd.productcatalog.exercise3;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages = "edu.uoc.epcsd.productcatalog", importOptions = ImportOption.DoNotIncludeTests.class)
public class OnionArchitectureTest {

    @ArchTest
    static final ArchRule onion_architecture_is_respected = onionArchitecture()
            .domainModels("..domain..")
            .domainServices("..domain.service..")
            .applicationServices("..application.rest..")
            .adapter("repository.jpa", "..infrastructure.repository.jpa..");
}
