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
package com.borkdominik.big.glsp.server.core.constants;

import org.eclipse.glsp.graph.DefaultTypes;

public class BGCoreTypes {
   public static final String LABEL_NAME = DefaultTypes.LABEL + ":name"; // Editable
   public static final String LABEL_TEXT = DefaultTypes.LABEL + ":text"; // Readonly
   public static final String LABEL_EDGE_NAME = DefaultTypes.LABEL + ":edge-name";

   public static final String ICON = "icon";
   public static final String ICON_CSS = ICON + ":css";

   public static final String DIVIDER = "divider";
   public static final String COMPARTMENT_ROOT_COMPONENT = "comp:root-component";
   public static final String COMPARTMENT_CONTAINER = "comp:container";

   private BGCoreTypes() {}
}
