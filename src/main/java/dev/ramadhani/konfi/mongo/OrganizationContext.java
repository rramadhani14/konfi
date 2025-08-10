package dev.ramadhani.konfi.mongo;

public class OrganizationContext {
    private static final ThreadLocal<String> currentOrganization = new ThreadLocal<>();

    public static void setCurrentOrganization(String organization) {
        currentOrganization.set(organization);
    }

    public static String getCurrentOrganization() {
        return currentOrganization.get();
    }

    public static void clear() {
        currentOrganization.remove();
    }
}
