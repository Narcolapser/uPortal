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

package org.jasig.portal.layout.dlm;

import java.util.Collections;
import java.util.List;

import org.w3c.dom.NodeList;

/**
 * Implementation of {@link ConfigurationLoader} that loads 
 * {@link FragmentDefinition} objects from the database using JPA/Hibernate.  
 * You can enable this feature by setting the <code>ConfigurationLoader.impl</code> 
 * property in dlm.xml.
 * 
 * @author awills
 */
public class RDBMConfigurationLoader extends LegacyConfigurationLoader {
    // Instance Members.
    private IFragmentDefinitionDao fragmentDao = null;

    /**
     * @param fragmentDao the fragmentDao to set
     */
    public void setFragmentDao(IFragmentDefinitionDao fragmentDao) {
        this.fragmentDao = fragmentDao;
    }

    /* (non-Javadoc)
     * @see org.jasig.portal.layout.dlm.LegacyConfigurationLoader#getFragments()
     */
    @Override
    public List<FragmentDefinition> getFragments() {
        final List<FragmentDefinition> allFragments = this.fragmentDao.getAllFragments();
        // lastly sort according to precedence
        Collections.sort(allFragments, new FragmentComparator() );
        return Collections.unmodifiableList(allFragments);
    }

    /* (non-Javadoc)
     * @see org.jasig.portal.layout.dlm.LegacyConfigurationLoader#getFragments(org.w3c.dom.NodeList)
     */
    @Override
    protected List<FragmentDefinition> getFragments(NodeList frags) {
        //Return null to just ignore the data from the dlm XML file
        return null;
    }
}