package com.example.demo.controller;

import com.example.demo.dto.DnaDTO;
import com.example.demo.service.SimiosService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimiosController {
    
    @Autowired
    SimiosService simiosService;

    @PostMapping("/simian")
    @ApiOperation(value = "check if it's a siminon")
    public ResponseEntity verifySimian(@RequestBody DnaDTO dna)  {
        if ( simiosService.isSimian(dna.getDna()) ) {
                return new ResponseEntity<>(HttpStatus.OK);
        }
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }
    @GetMapping("/stats")
    @ApiOperation(value = "check the status of the DNA")
    public ResponseEntity statusdna() {
        return new ResponseEntity<>(simiosService.getStatusDNA(), HttpStatus.OK);
    }
}
