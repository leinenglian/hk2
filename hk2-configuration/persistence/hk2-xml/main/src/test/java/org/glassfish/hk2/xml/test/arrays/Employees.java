/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2015-2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package org.glassfish.hk2.xml.test.arrays;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.glassfish.hk2.xml.api.annotations.PluralOf;
import org.glassfish.hk2.xml.test.basic.beans.Employee;
import org.glassfish.hk2.xml.test.basic.beans.Financials;
import org.glassfish.hk2.xml.test.basic.beans.OtherData;
import org.jvnet.hk2.annotations.Contract;

/**
 * @author jwells
 *
 */
@XmlRootElement @Contract
public interface Employees {
    public String getCompanyName();
    
    @XmlElement(name="company-name")
    public void setCompanyName(String name);
    
    @XmlElement @PluralOf("Financials")
    public void setFinancials(Financials finances);
    public Financials getFinancials();
    public void addFinancials();
    public Financials removeFinancials();
    
    @XmlElement(name="employee")
    public void setEmployees(Employee[] employees);
    public Employee[] getEmployees();
    public Employee lookupEmployee(String employeeName);
    public void addEmployee(String employeeName);
    public void addEmployee(String employeeName, int index);
    public void addEmployee(Employee employee);
    public void addEmployee(Employee employee, int index);
    public Employee removeEmployee(String employeeName);
    
    @XmlElement(name="other-data")
    public void setOtherData(OtherData[] otherData);
    public OtherData[] getOtherData();
    public void addOtherData(int position);
    public void addOtherData(OtherData otherData);
    public void addOtherData(OtherData otherData, int position);
    public OtherData removeOtherData(int position);
}
