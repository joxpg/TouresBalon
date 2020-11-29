package co.edu.javeriana.pica.drools.service;

import org.kie.api.runtime.KieSession;

import co.edu.javeriana.pica.drools.config.DroolsBeanFactory;
import co.edu.javeriana.pica.drools.model.Provider;
public class DroolsService {
    
    private KieSession kieSession=new DroolsBeanFactory().getKieSession();

    public Provider applyLabelToProduct(Provider provider){
        kieSession.insert(provider);
        kieSession.fireAllRules();

        return  provider;
    }

}
