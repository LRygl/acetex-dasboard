package com.acetex.acetexdasboard.Service.Impl;

import com.acetex.acetexdasboard.Model.Record;
import com.acetex.acetexdasboard.Repository.RecordRepository;
import com.acetex.acetexdasboard.Service.RecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;

    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Record> getAllRecords(){
        return recordRepository.findAll();
    }

}
