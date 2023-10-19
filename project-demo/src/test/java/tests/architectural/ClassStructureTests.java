package tests.architectural;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static java.lang.String.format;
import static tests.architectural.Properties.BASE;

@AnalyzeClasses(packages = BASE)
public class ClassStructureTests {


    @ArchTest
    static final ArchRule handlersMustResideInACommandPackage =
            classes().that().haveNameMatching(".*Handler").should().resideInAPackage("..commands..")
                    .as("Handlers should reside in a package '..commands..'");

    static ArchCondition<JavaClass> onlyHasASinglePublicMethodNamedHandler =
            new ArchCondition<>("Handler has a single public method") {
                @Override
                public void check(JavaClass item, ConditionEvents events) {
                    if (item.getMethods().stream().flatMap(m -> m.getModifiers().stream()).map(
                            Enum::name).filter(m -> m.equals("PUBLIC")).count() != 1) {
                        events.add(SimpleConditionEvent.violated(item, format("Class %s contains more than 1 public method", item.getName())));
                    }
                }
            };
    @ArchTest
    static final ArchRule handlersMustHasASinglePublicMethod =
            classes().that().haveNameMatching(".*Handler").should(onlyHasASinglePublicMethodNamedHandler)
                    .as("Handlers should contains only one public method");


    @ArchTest
    static final ArchRule queriesMustResideInAQueriesPackage =
            classes().that().haveNameMatching(".*Query").should().resideInAPackage("..queries..")
                    .as("Query's classes should reside in a package '..queries..'");

}
