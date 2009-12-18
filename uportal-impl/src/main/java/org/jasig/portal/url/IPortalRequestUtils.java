/**
 * Copyright (c) 2000-2009, Jasig, Inc.
 * See license distributed with this file and available online at
 * https://www.ja-sig.org/svn/jasig-parent/tags/rel-10/license-header.txt
 */
package org.jasig.portal.url;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eric Dalquist
 * @version $Revision$
 */
public interface IPortalRequestUtils {
    /**
     * Useful for container service callbacks that are provided with the portlet's request
     * but need access to the HttpServletRequest passed into the portlet container. 
     * 
     * @param portletRequest The request targeted to the portlet
     * @return The portlet scoped request passed to the portlet container
     */
    public HttpServletRequest getOriginalPortletAdaptorRequest(PortletRequest portletRequest);
    
    /**
     * @see #getOriginalPortletAdaptorRequest(PortletRequest)
     */
    public HttpServletRequest getOriginalPortletAdaptorRequest(HttpServletRequest portletRequest);
    
    
    /**
     * Useful for container service callbacks and service portlets that are provided with
     * the portlet's request but need access to the portal's HttpServletRequest. 
     * 
     * @param portletRequest The request targeted to the portlet
     * @return The portal's request, not scoped to a particular portlet
     */
    public HttpServletRequest getOriginalPortalRequest(PortletRequest portletRequest);
    
    /**
     * @see #getOriginalPortalRequest(PortletRequest)
     */
    public HttpServletRequest getOriginalPortalRequest(HttpServletRequest portletRequest);
    
    /**
     * Useful for container service callbacks and service portlets that are provided with
     * the portlet's request but need access to the portal's HttpServletResponse. 
     * 
     * @param portletRequest The request targeted to the portlet
     * @return The portal's response, not scoped to a particular portlet
     */
    public HttpServletResponse getOriginalPortalResponse(PortletRequest portletRequest);
    
    /**
     * @see #getOriginalPortalResponse(PortletRequest)
     */
    public HttpServletResponse getOriginalPortalResponse(HttpServletRequest portletRequest);
    
    /**
     * Uses {@link org.springframework.web.context.request.RequestContextHolder} to retrieve the current
     * portal HttpServletRequest 
     */
    public HttpServletRequest getCurrentPortalRequest();
}
