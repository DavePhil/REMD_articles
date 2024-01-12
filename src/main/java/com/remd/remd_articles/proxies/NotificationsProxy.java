package com.remd.remd_articles.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "remd-notifications", url = "localhost:9002")
public interface NotificationsProxy {
    @PostMapping("notification/newAccount/{name}/{to}")
    void newAccount(@PathVariable("name") String name, @PathVariable("to") String to);
    @PostMapping("notification/sendToken/{name}/{to}/{token}")
    void sendToken(@PathVariable("name") String name, @PathVariable("to") String to, @PathVariable("token") String token);
    @PostMapping("notification/sendTrouve/{name}/{to}")
    void sendTrouve(@PathVariable("name") String name, @PathVariable("to") String to);
    @PostMapping("notification/sendPotentiellementRetrouve/{name}/{to}/{nomRetrouveur}/{numberRetrouveur}/{emailRetrouveur}")
    void sendPotentiellementRetrouve(@PathVariable("name") String name, @PathVariable("to") String to,
                                            @PathVariable("nomRetrouveur") String nomRetrouveur,
                                            @PathVariable("numberRetrouveur") String numberRetrouveur,
                                            @PathVariable("emailRetrouveur") String emailRetrouveur);

}
