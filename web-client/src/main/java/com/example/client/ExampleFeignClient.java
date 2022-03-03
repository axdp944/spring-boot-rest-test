package com.example.client;

import com.example.client.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "hello", url = "${example.url}",
        configuration = FeignConfig.class
)
public interface ExampleFeignClient {

    @GetMapping(value = "hello")
    String getHello();

}
