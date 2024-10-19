/********************************************************************************
 * Copyright (c) 2024 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.borkdominik.big.glsp.server.core.handler.action.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.types.GLSPServerException;

import com.borkdominik.big.glsp.server.core.handler.action.integrations.BGEMFActionHandler;
import com.borkdominik.big.glsp.server.core.model.BGEMFModelState;
import com.google.inject.Inject;

public class BGRequestModelResourcesActionHandler extends BGEMFActionHandler<BGRequestModelResourcesAction> {
   private static Logger LOGGER = LogManager.getLogger(BGRequestModelResourcesActionHandler.class.getSimpleName());

   @Inject
   protected BGEMFModelState modelState;

   @Override
   protected List<Action> executeAction(final BGRequestModelResourcesAction actualAction) {
      return List.of(new BGModelResourcesResponseAction(this.getResources()));
   }

   protected Map<String, BGModelResource> getResources() {
      var resources = new HashMap<String, BGModelResource>();

      for (var resource : modelState.getResourceSet().getResources()) {
         if (resource.getURI() != null) {
            var outputStream = new ByteArrayOutputStream();
            var options = new HashMap<>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");

            try {
               resource.save(outputStream, options);
               var modelResource = new BGModelResource(resource.getURI().toString(), outputStream.toString());
               resources.put(resource.getURI().fileExtension(), modelResource);
               outputStream.close();
            } catch (IOException e) {
               LOGGER.error("Could not get resource: " + resource.getURI(), e);
               throw new GLSPServerException("Could not get resource: " + resource.getURI(), e);
            }
         }
      }

      return resources;
   }
}
