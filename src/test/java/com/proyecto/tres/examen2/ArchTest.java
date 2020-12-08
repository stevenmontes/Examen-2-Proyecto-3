package com.proyecto.tres.examen2;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.proyecto.tres.examen2");

        noClasses()
            .that()
                .resideInAnyPackage("com.proyecto.tres.examen2.service..")
            .or()
                .resideInAnyPackage("com.proyecto.tres.examen2.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.proyecto.tres.examen2.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
