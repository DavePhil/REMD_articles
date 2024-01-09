package com.remd.remd_articles.proxies;


import com.remd.remd_articles.beans.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "remd-users" , url = "localhost:9003")
public interface UserProxy {
    @GetMapping("user/{id}")
    Users getById(@PathVariable("id") Long id);
}
