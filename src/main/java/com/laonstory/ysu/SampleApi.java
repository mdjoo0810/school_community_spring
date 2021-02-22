package com.laonstory.ysu;

import com.laonstory.ysu.domain.user.dto.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class SampleApi {

    @PostMapping
    public String test(@Valid @RequestBody RegisterRequest dto) {


        return "test";
    }

}
