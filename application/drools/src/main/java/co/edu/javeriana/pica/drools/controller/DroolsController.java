package co.edu.javeriana.pica.drools.controller;

import org.drools.core.impl.KnowledgeBaseFactory;
import org.drools.serialization.protobuf.ProtobufMessages.KnowledgeBase;
import org.kie.api.io.ResourceType;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatelessKnowledgeSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.pica.drools.model.*;

@RestController
public class DroolsController {
      
       private static StatelessKnowledgeSession session;
      
       @RequestMapping(value = "/percent", method = RequestMethod.GET, produces = "application/json")
       public Provider percentCalculate(@RequestParam String type) throws Exception{
              return runProviderRule(type);
       }
      
       @SuppressWarnings("deprecation")
       private Provider runProviderRule(String type) throws Exception{
              KnowledgeBase knowledgeBase = createKnowledgeBaseFromXLS ();
              session = knowledgeBase.newStatelessKnowledgeSession();
              Provider provider = new Provider();
              provider.setNameProvider(type);
              session.execute(provider);
              return provider;
       }

      
       @SuppressWarnings("deprecation")
       private static KnowledgeBase createKnowledgeBaseFromXLS ()throws Exception {
              DecisionTableConfiguration dtconf = KnowledgeBuilderFactory.newDecisionTableConfiguration();
              dtconf.setInputType(TouresBalon.xls);

              KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
              knowledgeBuilder.add(ResourceFactory.newFileResource("rules/TouresBalon.xls"),ResourceType.DTABLE, dtconf);
              if (knowledgeBuilder.hasErrors()) {
                     throw new RuntimeException(knowledgeBuilder.getErrors().toString());
              }            

              KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
              knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
              return knowledgeBase;
       }      
      
}