package com.tutorial.transportsystem.service.general;

import com.tutorial.transportsystem.entity.Train;

import java.util.List;

public interface TrainService {

    List<Train> getAllTrains();

    Train getTrainById(Long id);

    void addTrain(Train train);

    public void deleteTrain(Long id);
}
