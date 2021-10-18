package com.theredeemed.myactivityexpensetrackerservice.service

import com.theredeemed.myactivityexpensetrackerservice.exception.ActivityException
import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDto
import com.theredeemed.myactivityexpensetrackerservice.model.entity.ActivityEntity
import com.theredeemed.myactivityexpensetrackerservice.model.repository.ActivityRepository
import spock.lang.Specification

import static com.theredeemed.myactivityexpensetrackerservice.TestConstants.getActivityDto
import static com.theredeemed.myactivityexpensetrackerservice.TestConstants.getActivityEntityList

class ActivityServiceTest extends Specification {
    ActivityService activityService
    ActivityRepository activityRepository
    Map<String, String> activityExpense = new HashMap<>()

    def setup() {
        activityRepository = Mock()
        activityService = new ActivityService(activityRepository)
        activityExpense.put("title","Martial Arts")
        activityExpense.put("balance","10")
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
        activityRepository.save(_ as ActivityEntity) >> { throw new IllegalArgumentException('an error occurred while saving activity') }

        when: 'The createNewActivity method is called'
        activityService.createNewActivity(getActivityDto())

        then: 'An illegalArgumentException is thrown'
        def exception = thrown(ActivityException)
        exception.error.code == 0
        exception.error.description.equalsIgnoreCase('Unable to save activity')
        exception.error.toString().equalsIgnoreCase("0 - Unable to save activity")
    }

    def "Happy path - updating the activity balance"() {
        given: 'The balance of an activity needs to be updated'
        ActivityEntity activityMock = getActivityEntityList().get(0)
        activityMock.balance = 10

        when: 'The balance is updated successfully'
        ActivityDto updatedActivity = activityService.updateActivityBalance(activityExpense)

        then: 'Expect the activity with the updated expense to be returned'
        1 * activityRepository.updateActivityBalance(_ as String, _ as BigDecimal)
        updatedActivity.balance == new BigDecimal(10)
    }

    def "Sad path - Error while updating the activity balance"() {
        given: 'The balance of an activity needs to be updated'
        activityRepository.updateActivityBalance(_ as String, _ as BigDecimal) >> { throw new IllegalArgumentException('An error occurred while updating the balance') }

        when: 'An error occurs while updating the balance'
        activityService.updateActivityBalance(activityExpense)

        then: 'Expect an error to be thrown'
        def exception = thrown(ActivityException)
        exception.error.code == 2
        exception.error.description.equalsIgnoreCase('Unable to update activity balance')
        exception.error.toString().equalsIgnoreCase('2 - Unable to update activity balance')
    }

}
