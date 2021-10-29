package com.acetex.acetexdasboard.Resource;

import com.acetex.acetexdasboard.Model.HttpResponse;
import com.acetex.acetexdasboard.Service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
public class RecordResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final RecordService recordService;


    public RecordResource(RecordService recordService) {
        this.recordService = recordService;
    }


}
