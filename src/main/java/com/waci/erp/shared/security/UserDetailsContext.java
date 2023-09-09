package com.waci.erp.shared.security;

import com.waci.erp.models.prayers.Organisation;
import com.waci.erp.shared.models.User;

/**
 * This class is used to store the current request's bearer token. We have used the
 * InheritableThreadLocal variable. This enables the child threads created from
 * the main thread in our application to use the bearer token of the Parent Thread.
 *
 */
public class UserDetailsContext {

    private UserDetailsContext() {
        // Add a private constructor to hide the implicit public one.
    }

    private static ThreadLocal<User> bearerToken = new InheritableThreadLocal<>();
    private static ThreadLocal<Organisation> organisation = new InheritableThreadLocal<>();

    public static Organisation getLoggedInOrganisation() {
        return organisation.get();
    }

    public static void setLoggedInOrganisation(Organisation id) {
        organisation.set(id);
    }

    public static User getLoggedInUser() {
        return bearerToken.get();
    }

    public static void setLoggedInUser(User id) {
        bearerToken.set(id);
    }


    public static void clear() {
        bearerToken.remove();
        organisation.remove();
    }
}
