/********************************************************************************
 * Copyright (c) 2022 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.borkdominik.big.glsp.server.core.diagram;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.glsp.server.types.EdgeTypeHint;
import org.eclipse.glsp.server.types.ShapeTypeHint;

public interface BGDiagramConfigurationProvider {

   Map<String, EClass> getTypeMappings();

   interface Node extends BGDiagramConfigurationProvider {
      /**
       * Elements that can be added to the graph directly (i.e., root)
       *
       * @return Type IDs
       */
      Set<String> getGraphContainableElements();

      Set<ShapeTypeHint> getShapeTypeHints();
   }

   interface Edge extends BGDiagramConfigurationProvider {
      Set<EdgeTypeHint> getEdgeTypeHints();
   }
}
