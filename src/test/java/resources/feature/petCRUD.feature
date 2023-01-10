Feature: Testing different request on the petstore application pet detail

  Scenario Outline: As a user I am registering new pet detail
    Given Petstore app is running
    When I create a new Pet by providing petID "<id>" name "<name>" status "<status>"
    And I send GET request for petID "<id>"
    And I update a Pet by providing petID "<id>" name "<name>" status "<status>"
    And I send delete request by petID "<id>"
    Then pet detail get deleted and get status code 200
    Examples:
      | id   | name  | status |
      | 1010 | Mango | Owned  |








