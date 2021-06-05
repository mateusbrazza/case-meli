package com.example.demo.controller;

import com.example.demo.service.SimiosService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimiosController {
    
    @Autowired
    SimiosService simiosService;

    @PostMapping
    @ApiOperation(value = "check if it's a siminon")
    public ResponseEntity update(@RequestBody String[] var)  {
        if ( simiosService.validateSize(var) && simiosService.validateCharacters(var) ) {
            if ((simiosService.findLettersHorizontal(var) || simiosService.findLettersVertical(var)) ||
                    (simiosService.findLettersDiagonallyLeft(var) || simiosService.findLettersDiagonallyRight(var))
            ) {
                return new ResponseEntity<>("true",HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("false",HttpStatus.BAD_REQUEST);
    }
}
