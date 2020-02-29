//package com.springboot.titletech;
//
//import com.springboot.titletech.entity.Parcel;
//import com.springboot.titletech.repository.ParcelRepository;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Component;
//
//@Component
//public class runner implements CommandLineRunner {
//
//    private static final Logger logger = LoggerFactory.getLogger(runner.class);
//
//    @Autowired
//    private ParcelRepository parcelRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        parcelRepository.save(new Parcel());
//        parcelRepository.save(new Parcel());
//        parcelRepository.save(new Parcel());
//
//        logger.info("# of cities: {}", parcelRepository.count());
//
//        logger.info("All cities unsorted:");
//        var cities = parcelRepository.findAll();
//        logger.info("{}", cities);
//        logger.info("------------------------");
//        logger.info("Deleting all cities");
//        parcelRepository.deleteAllInBatch();
//
//        logger.info("# of cities: {}", parcelRepository.count());
//    }
//}
