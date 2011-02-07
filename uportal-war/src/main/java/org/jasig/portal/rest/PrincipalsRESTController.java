/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.jasig.portal.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.portal.layout.dlm.remoting.IGroupListHelper;
import org.jasig.portal.portlets.groupselector.EntityEnum;
import org.jasig.portal.security.IAuthorizationPrincipal;
import org.jasig.portal.security.IAuthorizationService;
import org.jasig.portal.security.IPerson;
import org.jasig.portal.security.IPersonManager;
import org.jasig.portal.security.provider.AuthorizationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * PrincipalsRESTController provides a REST endpoint for searching uPortal
 * principals.
 * 
 * @author Jen Bourey, jbourey@unicon.net
 * @version $Revision$
 */
@Controller
public class PrincipalsRESTController {

    private IPersonManager personManager;
    
    @Autowired
    public void setPersonManager(IPersonManager personManager) {
        this.personManager = personManager;
    }

    private IGroupListHelper listHelper;
    
    @Autowired(required = true)
    public void setGroupListHelper(IGroupListHelper groupListHelper) {
        this.listHelper = groupListHelper;
    }

    /**
     * Return a JSON view of the uPortal principals matching the supplied
     * query string.
     * 
     * @param query
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/permissions/principals.json", method = RequestMethod.GET)
    public ModelAndView getPrincipals(
            @RequestParam(value="q") String query,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /*
         * Temporary authorization code requests a non-existant permission.  This
         * code will pass for superusers but will deny all other users access
         * to the REST service.
         */
        
        final IPerson person = personManager.getPerson((HttpServletRequest) request);
        if (person == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        IAuthorizationService authServ = AuthorizationImpl.singleton();
        IAuthorizationPrincipal principal = authServ.newPrincipal(
                (String) person.getAttribute(IPerson.USERNAME), IPerson.class);
        if(!principal.hasPermission("UP_PERMISSION", "VIEW_PERMISSIONS", "REST")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        /*
         *  Add groups and people matching the search query to the JSON model
         */
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("groups", listHelper.search(EntityEnum.GROUP.toString(), query));
        mv.addObject("people", listHelper.search(EntityEnum.PERSON.toString(), query));
        mv.setViewName("json");
        
        return mv;
    }

}