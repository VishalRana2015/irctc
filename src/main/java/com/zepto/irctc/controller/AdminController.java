package com.zepto.irctc.controller;

import com.zepto.irctc.dto.*;
import com.zepto.irctc.exception.AlreadyBookedCoachesException;
import com.zepto.irctc.exception.InvalidCoacheTypeException;
import com.zepto.irctc.model.Coache;
import com.zepto.irctc.model.Seat;
import com.zepto.irctc.model.Train;
import com.zepto.irctc.service.CoacheService;
import com.zepto.irctc.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class AdminController {

    @Autowired
    TrainService trainService ;

    @Autowired
    CoacheService coacheService;
    @PostMapping("/createTrain")
    public ResponseEntity<Train> createTrain(@RequestBody TrainToCreate trainToCreate){
        System.out.println("Trainname : " + trainToCreate.getTrainName());
        Train train = trainService.createNewTrain(trainToCreate);
        return new ResponseEntity<Train>(train, HttpStatus.OK);
    }

    @PostMapping("/createCoache")
    public ResponseEntity<CoacheToCreate> createCoache(@RequestBody CoacheToCreate coacheToCreate) throws InvalidCoacheTypeException {
        Coache coache = coacheService.createCoache(coacheToCreate);
        coacheToCreate.setSeatData(new ArrayList<>());
        coacheToCreate.setId(coache.getId());
        for (Seat seat  : coache.getSeats()){
            // for every seat create SeatData
            SeatData seatData = new SeatData();
            seatData.setSeatNumber(seat.getSeatId().getSeatNumber());
            seatData.setIsAvailable(seat.isAvailable());
            coacheToCreate.getSeatData().add(seatData);
        }
        return new ResponseEntity<>(coacheToCreate, HttpStatus.OK);
    }


    @PostMapping("/addCoachesToTrain")
    public ResponseEntity<TrainWithAddedCoahesIdsData> addCoachesToTrain(@Valid @RequestBody AddCoachesToTrainData addCoachesToTrainData)
    throws AlreadyBookedCoachesException {
        Train train = trainService.addCoachesToTrain(addCoachesToTrainData);
        TrainWithAddedCoahesIdsData data = new TrainWithAddedCoahesIdsData();
        data.setTrainId(train.getId());
        data.setList(new ArrayList<>());
        for ( Coache coache : train.getCoaches()){
            TrainWithAddedCoahesIdsData.NestedCoacheData nestedData = new TrainWithAddedCoahesIdsData.NestedCoacheData();
            nestedData.setId(coache.getId());
            nestedData.setName(coache.getName());
            nestedData.setType(coache.getType().toString());
            data.getList().add(nestedData);
        }
        return new ResponseEntity<> ( data , HttpStatus.OK);
    }



}
