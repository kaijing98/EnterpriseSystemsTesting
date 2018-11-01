# EnterpriseSystemsTesting
# Demonstration for Black-box testing

By running the test suite file under test packages of the ejb.session.stateless module, we test whether
our program can pass the following test cases:
1. Testing VenueSessionBean:
- Test = test01RetrieveAllVenues
- Assertion = assetFalse(result.isEmpty()), assetEquals(4, result.size())


2) Testing SystemUserSessionBean:
- Test1 = test01RetrieveSystemUser1
- Test Data = "user1", 1|
- Assertion = assetEquals(expResult, result)


- Test2 = test02RetrieveSystemUser2
- Test Data = "user2", 2|
- Assertion = assertEquals(expResult, result)


- Test3 = test03RetrieveSystemUser9
- Test Data = "user9"
- Assertion = assertNull(result)


3) Testing EventSessionBean
- Test1 = test01AddNewEvent1
- Test Data = "Lecture 1", "2018-12-02 12:00:00", "2018-12-02 14:00:00", 4, 1
- Assertion = assertNotNull(result), Unexpected Exception: VenueConflictException


- Test2 = test02AddNewEvent2
- Test Data = "First Consultation", "2018-12-03 10:00:00", "2018-12-03 11:00:00", 1, 1
- Assertion = assertNotNull(result), Unexpected Exception: VenueConflictException


- Test3 = test03AddNewEvent3
- Test Data = “Tutorial 1", "2018-12-03 10:00:00", "2018-12-03 11:00:00", 1, 1
- Assertion = assertNotNull(result), Expected Exception: VenueConflictException

Running the above program in netbeans should pass all test cases
