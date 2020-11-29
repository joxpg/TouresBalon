package co.com.puj.aes.drools.controller;

import co.com.puj.aes.drools.entity.Orden;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orden")
public class ordenController {

    @Autowired
    private KieSession session;

    @PostMapping("")
    public Orden orderNow(@RequestBody Orden order) {
        session.insert(order);
        session.fireAllRules();
        return order;
    }


}
