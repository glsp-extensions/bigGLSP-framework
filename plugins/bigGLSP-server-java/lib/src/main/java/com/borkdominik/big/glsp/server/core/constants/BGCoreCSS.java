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

public class BGCoreCSS {
   public static final String STROKE_DASHED = "stroked";
   public static final String NO_STROKE = "no-stroke";

   public static final String NODE = "uml-node";
   public static final String EDGE = "uml-edge";
   public static final String PORT = "uml-node"; // TODO revise to port
   public static final String ELLIPSE = "uml-ellipse";
   public static final String PACKAGEABLE_NODE = "uml-packageable-node";

   public static final String NODE_CONTAINER = "uml-node-container";

   public static final String EDGE_DOTTED = "uml-edge-dotted";
   public static final String EDGE_DASHED = "uml-edge-dashed";

   public static final String DIVIDER_SUBTITLE = "uml-divider-subtitle";
   public static final String TEXT_UNDERLINE = "uml-text-underline";
   public static final String TEXT_INTERACTABLE = "uml-text-interactable";
   public static final String TEXT_HIGHLIGHT = "uml-text-highlight";
   public static final String FONT_BOLD = "uml-font-bold";
   public static final String FONT_ITALIC = "uml-font-italic";

   public enum Marker {
      NONE("marker-none"),
      TRIANGLE("marker-triangle"),
      TRIANGLE_EMPTY("marker-triangle-empty"),
      TENT("marker-tent"),
      DIAMOND("marker-diamond"),
      DIAMOND_EMPTY("marker-diamond-empty"),
      CIRCLE("marker-circle"),
      CIRCLE_EMPTY("marker-circle-empty");

      public String css;

      Marker(final String css) {
         this.css = css;
      }

      public String start() {
         return css + "-start";
      }

      public String end() {
         return css + "-end";
      }
   }

   private BGCoreCSS() {}
}
