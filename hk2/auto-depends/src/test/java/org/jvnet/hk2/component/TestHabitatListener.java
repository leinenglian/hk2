/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2010-2011 Oracle and/or its affiliates. All rights reserved.
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
package org.jvnet.hk2.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Ignore;
import org.jvnet.hk2.component.Habitat;
import org.jvnet.hk2.component.HabitatListener;
import org.jvnet.hk2.component.Inhabitant;

/**
 * A test friendly habitat listener.
 * 
 * @author Jeff Trent
 */
@Ignore
public class TestHabitatListener implements HabitatListener {

  public final List<Call> calls = Collections.synchronizedList(new ArrayList<Call>());
  public RuntimeException forced; 
  public Integer countDown;
  
  public TestHabitatListener() {
  }
  
  public TestHabitatListener(int countDownBeforeRetFalse) {
    this.countDown = countDownBeforeRetFalse;
  }

  public TestHabitatListener(RuntimeException runtimeException) {
    this.forced = runtimeException;
  }

  @Override
  public boolean inhabitantChanged(EventType eventType, Habitat habitat,
      Inhabitant<?> inhabitant) {
    calls.add(new Call(eventType, habitat, inhabitant));
    if (null != forced) throw forced;
    return (null == countDown || --countDown > 0);
  }

  @Override
  public boolean inhabitantIndexChanged(EventType eventType, Habitat habitat,
      Inhabitant<?> inhabitant, String index, String name, Object service) {
    calls.add(new Call(eventType, habitat, inhabitant, index, name, service));
    if (null != forced) throw forced;
    return (null == countDown || --countDown > 0);
  }

  public static class Call {
    public EventType eventType;
    public Habitat h;
    public Inhabitant<?> obj;
    public String index;
    public String name;
    public Object service;
    
    public Call(EventType eventType, Habitat h, Inhabitant<?> i) {
      this(eventType, h, i, null, null, null);
    }

    public Call(EventType eventType, Habitat h, Inhabitant<?> i, String index, String name, Object service) {
      this.eventType = eventType;
      this.h = h;
      this.obj = i;
      this.index = index;
      this.name = name;
      this.service = service;
    }

    public String toString() {
      StringBuilder b = new StringBuilder();
      b.append("(event: ").append(eventType);
      b.append(";obj: ").append(obj);
      b.append(";name: ").append(name);
      b.append(")");
      return b.toString();
    }
    
  }
}
