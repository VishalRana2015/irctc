package com.zepto.irctc.service;

import com.zepto.irctc.dto.AddCoachesToTrainData;
import com.zepto.irctc.dto.TrainToCreate;
import com.zepto.irctc.exception.AlreadyBookedCoachesException;
import com.zepto.irctc.model.Coache;
import com.zepto.irctc.model.Train;
import com.zepto.irctc.repository.CoacheRepository;
import com.zepto.irctc.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainService {
    @Autowired
    TrainRepository trainRepository;
    @Autowired
    CoacheRepository coacheRepository;

    public Train createNewTrain(TrainToCreate trainToCreate){
        Train train = new Train();
        train.setName(trainToCreate.getTrainName());
        return trainRepository.save(train);
    }


    public Train addCoachesToTrain(AddCoachesToTrainData addCoachesToTrainData) throws AlreadyBookedCoachesException{
        List<Coache> alreadyBookedCoaches = coacheRepository.findBookedCoachesInList(addCoachesToTrainData.getCoacheIDs());
        if ( alreadyBookedCoaches.size() > 0){
            List<Integer> list =new ArrayList<>();
            for ( int i =0; i < alreadyBookedCoaches.size() ; i++){
                list.add(alreadyBookedCoaches.get(0).getId());
            }
            throw new AlreadyBookedCoachesException(list);
        }
        Train train = trainRepository.getById(addCoachesToTrainData.getTrainId());
        for ( int i =0; i < addCoachesToTrainData.getCoacheIDs().size() ; i++){
            Coache coache = coacheRepository.getById(addCoachesToTrainData.getCoacheIDs().get(i));
            train.addCoachToTrain(coache);
        }
        return trainRepository.save(train);
    }
}
