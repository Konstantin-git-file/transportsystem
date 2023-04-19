package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.entity.Train;
import com.tutorial.transportsystem.service.general.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/getall")
    public List<Train> getAllTrains() {
        return trainService.getAllTrains();
    }

    @GetMapping("/{id}")
    public Train getTrainById(@PathVariable Long id) {
        return trainService.getTrainById(id);
    }

    @PostMapping("/add_train")
    public void addTrain(@RequestBody Train train) {
        trainService.addTrain(train);
    }

    @DeleteMapping("/{id}")
    public void deleteTrain(@PathVariable Long id) {
        trainService.deleteTrain(id);
    }
}
