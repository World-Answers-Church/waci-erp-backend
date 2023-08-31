package com.waci.erp.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author User
 */
public class FilterUtils {

    private static String v2EndPoint = "/v2";
    private static Set<String> allowedEndpoints = new HashSet<>(
            Arrays.asList(
                    "/api/v1/health",
                    "/api/v1/status",
                    "/swagger-ui",
                    "/api-docs",
                    "/api/v1/auth/refresh/token",
                    "/api/v1/auth/login",
                    "/api/v1/lookups"
            )
    );

    public static boolean allowedAuth(String path) {

        for (String string : allowedEndpoints) {
            if (path.startsWith(string)) {
                return true;
            }
        }
        return false;
    }
}
