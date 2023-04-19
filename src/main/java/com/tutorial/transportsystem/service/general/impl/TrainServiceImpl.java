package com.tutorial.transportsystem.service.general.impl;

import com.tutorial.transportsystem.entity.Train;
import com.tutorial.transportsystem.repository.TrainRepository;
import com.tutorial.transportsystem.service.general.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public
class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;

    @Override
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainById(Long id) {
        return trainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Train not found"));
    }

    @Override
    public void addTrain(Train train) {
        trainRepository.save(train);
    }

    @Override
    public void deleteTrain(Long id) {
        trainRepository.deleteById(id);
    }
}
