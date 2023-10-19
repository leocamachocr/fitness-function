package tests.architectural;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.thirdparty.com.google.common.collect.Streams;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static java.lang.String.format;
import static tests.architectural.Properties.*;

@AnalyzeClasses(packages = "ucr.ac.fitfun")
public class LayerDependenciesTests {
    private static final String[] LAYERED_COMPONENTS = {"security"};
    private static final String[] STRUCTURAL_COMPONENTS = {"exceptions"};

    @ArchTest
    static final ArchRule layerDependenciesAreRespected = buildLayerDependenciesForBusinessComponentes();

    private static ArchRule buildLayerDependenciesForBusinessComponentes() {
        var layers = Arrays.stream(LAYERED_COMPONENTS).reduce(
                layeredArchitecture().consideringAllDependencies(),
                (layer, component) -> {
                    layer.layer(apiFor(component)).definedBy(apiPackageOf(component))
                            .withOptionalLayers(true)
                            .layer(commandFor(component)).definedBy(commandsPackageOf(component))
                            .withOptionalLayers(true)
                            .layer(queriesFor(component)).definedBy(queriesPackageOf(component))
                            .withOptionalLayers(true)
                            .layer(persistenceFor(component)).definedBy(persistencePackageOf(component));
                    return layer;
                }, (a, b) -> b);

        return Arrays.stream(LAYERED_COMPONENTS).reduce(
                layers,
                (layer, component) -> {
                    layer.whereLayer(apiFor(component)).mayNotBeAccessedByAnyLayer()
                            .whereLayer(commandFor(component)).mayOnlyBeAccessedByLayers(
                                    Streams.concat(allApiBusinessLayers(), allCommandsBusinessLayers()).toArray(String[]::new)
                            )
                            .whereLayer(queriesFor(component)).mayOnlyBeAccessedByLayers(
                                    Streams.concat(allApiBusinessLayers(), allQueriesBusinessLayers()).toArray(String[]::new)
                            )
                            .whereLayer(persistenceFor(component)).mayOnlyBeAccessedByLayers(
                                    Streams.concat(allCommandsBusinessLayers(), allQueriesBusinessLayers()).toArray(String[]::new)
                            );
                    return layer;
                }, (a, b) -> b);


    }

    private static Stream<String> allCommandsBusinessLayers() {
        return Arrays.stream(LAYERED_COMPONENTS).map(LayerDependenciesTests::commandFor);
    }

    private static Stream<String> allQueriesBusinessLayers() {
        return Arrays.stream(LAYERED_COMPONENTS).map(LayerDependenciesTests::queriesFor);
    }

    private static Stream<String> allApiBusinessLayers() {
        return Arrays.stream(LAYERED_COMPONENTS).map(LayerDependenciesTests::apiFor);
    }

    private static String apiFor(String component) {
        return format("API-%s", component);
    }

    private static String commandFor(String component) {
        return format("Command-%s", component);
    }

    private static String queriesFor(String component) {
        return format("Queries-%s", component);
    }

    private static String persistenceFor(String component) {
        return format("Persistence-%s", component);
    }
}
