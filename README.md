# TDD API EXAMPLE

## A restful test API with TDD for travel booking.

This project was developed based on TDD (Test Driven Development). The idea of TDD is that you work in cycles. These cycles occur in the following order:

First, write a unit test that will initially fail since the code has not yet been implemented;

Create code that satisfies this test, ie: implement the functionality in question. This first implementation should immediately satisfy the test that was written in the previous cycle;

Once the code is implemented and testing satisfied, refactor the code to improve points such as readability. Afterwards, run the test again. The new version of the code should also pass without having to modify the test written initially.

### Technologies

The following tools were used in the construction of the project:

- Java 11
- Maven
- Spring boot
- JUnit 5
- MockMvc and MockBean, was used in tests