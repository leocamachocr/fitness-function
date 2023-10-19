package tests.architectural;

import static java.lang.String.format;

public class Properties {

    public static final String BASE = "ucr.ac.fitfun";

    public static String apiPackageOf(String subPackage) {
        return format("%s.%s.api..", BASE, subPackage);
    }

    public static String commandsPackageOf(String subPackage) {
        return format("%s.%s.handlers.commands..", BASE, subPackage);
    }

    public static String queriesPackageOf(String subPackage) {
        return format("%s.%s.handlers.queries..", BASE, subPackage);
    }

    public static String persistencePackageOf(String subPackage) {
        return format("%s.%s.jpa.repositories..", BASE, subPackage);
    }


}


