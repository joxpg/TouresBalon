package co.com.puj.aes.drools.controller;

import co.com.puj.aes.drools.entity.Orden;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orden")
public class ordenController {

    @Autowired
    private KieSession session;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Qualifier("getWebClientBuilder")
    @Autowired
    private WebClient.Builder webClientBuilder;

    @PostMapping("")
    public Boolean orderNow(@RequestBody Orden order) {
        session.insert(order);
        session.fireAllRules();
        return order.isCheck();
    }


}
