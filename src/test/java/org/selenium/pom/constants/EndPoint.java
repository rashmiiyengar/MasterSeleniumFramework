package org.selenium.pom.constants;

/**
 * The {@code EndPoint} enum provides constants for various endpoint URLs used in the application.
 * Each constant represents a specific endpoint with a URL, which is used for making HTTP requests.
 *
 * <p>This enum helps centralize and manage endpoint URLs in a single location, providing
 * consistency, readability, and maintainability in the codebase.</p>
 *
 * <p>Usage Example:</p>
 * <pre>
 *     String billingUrl = EndPoint.ACCOUNT_EDIT_BILLING_ADDRESS.url;
 * </pre>
 *
 * @author rashmisoundar
 * @version 1.0
 */

public enum EndPoint {
    /**
     * Represents the endpoint URL for editing the billing address in a user's account.
     * This URL is used to make requests to the billing address edit page.
     */
    ACCOUNT_EDIT_BILLING_ADDRESS("/account/edit-address/billing/");
    public final String url;

    /**
     * Constructor for initializing the endpoint with a specific URL.
     *
     * @param url The URL string associated with the endpoint constant.
     */
    EndPoint(String url){
        this.url = url;

    }
}
