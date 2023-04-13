package com.spring.demo.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class Response.
 */
@JsonInclude(Include.NON_NULL)
public class Response implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The status message. */
	private String statusMessage;
	
	/** The status code. */
	private String statusCode;
	
	/** The additional properties. */
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	/**
	 * Instantiates a new response.
	 */
	public Response() {
		super();
	}
	
	/**
	 * Instantiates a new response.
	 *
	 * @param statusMessage the status message
	 * @param statusCode the status code
	 */
	public Response(final String statusMessage, final String statusCode) {
		super();
		this.statusMessage = statusMessage;
		this.statusCode = statusCode;
	}

	/**
	 * Gets the status message.
	 *
	 * @return the status message
	 */
	public final String getStatusMessage() {
		return statusMessage;
	}
	
	/**
	 * Sets the status message.
	 *
	 * @param statusMessage the new status message
	 */
	public final void setStatusMessage(final String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public final String getStatusCode() {
		return statusCode;
	}
	
	/**
	 * Sets the status code.
	 *
	 * @param statusCode the new status code
	 */
	public final void setStatusCode(final String statusCode) {
		this.statusCode = statusCode;
	}
	
	/**
	 * Gets the additional properties.
	 *
	 * @return the additional properties
	 */
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Sets the additional property.
     *
     * @param name the name
     * @param value the value
     */
    @JsonAnySetter
    public void setAdditionalProperty(final String name, final Object value) {
        this.additionalProperties.put(name, value);
    }

	/**
	 * To string.
	 *
	 * @return the string
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Response [statusMessage=" + statusMessage + ", statusCode=" + statusCode + ", additionalProperties="
				+ additionalProperties + "]";
	}
}