package com.theredeemed.myactivityexpensetrackerservice.service

import com.theredeemed.myactivityexpensetrackerservice.exception.ActivityException
import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDto
import com.theredeemed.myactivityexpensetrackerservice.model.repository.ActivityRepository
import spock.lang.Specification

import static com.theredeemed.myactivityexpensetrackerservice.TestConstants.getActivityDto
import static com.theredeemed.myactivityexpensetrackerservice.TestConstants.getActivityEntityList
import static com.theredeemed.myactivityexpensetrackerservice.exception.Error.UNABLE_TO_SAVE_ACTIVITY

class ActivityServiceTest extends Specification {
    ActivityService activityService
    ActivityRepository activityRepository

    def setup() {
        activityRepository = Mock()
        activityService = new ActivityService(activityRepository)
    }

    def "Retrieving activity list"() {
        given: 'A request to retrieve all activities'

        when: 'The getActivityList method id called'
        List<ActivityDto> activities = activityService.getActivityList()

        then: 'expect the activity list to be returned'
        1 * activityRepository.findAll() >> getActivityEntityList()
        activities.size() > 0
    }

    def "Happy path - Saving new activity"() {
        given: 'A request to save a new activity'

        when: 'The createNewActivity method is called'
        ActivityDto newActivity = activityService.createNewActivity(getActivityDto())

        then: 'Save and return the Dto of the newly created activity'
        1 * activityRepository.save(_)
        newActivity.title.equalsIgnoreCase('Martial Arts')
    }

    def "Sad path - Saving new activity"() {
        given: 'A request to save a new activity'
        activityRepository.save(_) >> { throw new IllegalArgumentException('an error occurred while saving activity') }

        when: 'The createNewActivity method is called'
        activityService.createNewActivity(getActivityDto())

        then: 'An illegalArgumentException is thrown'
        def exception = thrown(ActivityException)
        exception.error.toString().equalsIgnoreCase("0 - Unable to save activity")
    }

}
