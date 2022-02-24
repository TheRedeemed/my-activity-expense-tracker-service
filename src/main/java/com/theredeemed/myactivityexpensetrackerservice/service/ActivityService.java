package com.theredeemed.myactivityexpensetrackerservice.service;

import com.theredeemed.myactivityexpensetrackerservice.exception.ActivityException;
import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDTO;
import com.theredeemed.myactivityexpensetrackerservice.model.repository.ActivityJdbcDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.theredeemed.myactivityexpensetrackerservice.exception.Error.UNABLE_TO_SAVE_RECORD;
import static com.theredeemed.myactivityexpensetrackerservice.exception.Error.UNABLE_TO_UPDATE_ACTIVITY_BALANCE;

@Service
@Slf4j
public class ActivityService {
    private final ActivityJdbcDAO activityJdbcDAO;

    @Autowired
    ActivityService(ActivityJdbcDAO activityJdbcDAO) {
        this.activityJdbcDAO = activityJdbcDAO;
    }

    public List<ActivityDTO> getActivityList() {
        log.debug("Returning list of activities");
        return activityJdbcDAO.findAll();
    }

    public ActivityDTO createNewActivity(ActivityDTO newActivityDTO) throws ActivityException {
        try {
            log.debug("Saving activity entity");
            activityJdbcDAO.create(newActivityDTO);
            return newActivityDTO;
        } catch (IllegalArgumentException | DataAccessException e) {
            log.error(e.getMessage());
            throw new ActivityException(UNABLE_TO_SAVE_RECORD, e);
        }
    }

    @Transactional  //A Transaction is required when perform a Db update operation
    public ActivityDTO updateActivityBalance(Map<String, String> activityExpense) throws ActivityException {
        try {
//            ActivityEntity activityToUpdate = activityJdbcDAO.findByTitle(activityExpense.get("title"));
//            if (Optional.ofNullable(activityToUpdate).isPresent()) {
//                activityToUpdate.setBalance(new BigDecimal(activityExpense.get("balance")));
//                activityRepository.save(activityToUpdate);
//            } else {
//                throw new ActivityException(ACTIVITY_NOT_FOUND);
//            }
            return ActivityDTO.builder().build();
        } catch (IllegalArgumentException | DataAccessException e) {
            log.error(e.getMessage());
            throw new ActivityException(UNABLE_TO_UPDATE_ACTIVITY_BALANCE, e);
        }
    }
}
