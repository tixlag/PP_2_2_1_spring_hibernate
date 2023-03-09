package hiber.service;

import hiber.dao.CarDao;
import hiber.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImp implements CarService {

    @Autowired
    private CarDao carDao;

    // Я так и не понял для чего аннотация @Transactional, как она работает и что делает.
    @Override
    @Transactional
    public void add(Car car) {
        carDao.add(car);
    }
}
