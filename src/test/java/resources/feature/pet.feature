Feature: Testing different request on the petstore application pet detail

  Scenario Outline: As a user I am registering new pet detail
    Given Petstore app is running
    When I create a new Pet by providing petID "<id>" name "<name>" status "<status>"
    Then I must get back a valid status code 200
    Examples:
      | id   | name  | status |
      | 1010 | Mango | Owned  |

  Scenario: As a user I am getting detail of new pet
    When I send GET request for petID "<id>"
    Then I must get back valid status code 200

  Scenario Outline: As a user I am updating existing pet detail
    When I update a Pet by providing petID "<id>" name "<name>" status "<status>"
    Then I must get a valid status code 200
    Examples:
      | id   | name  | status |
      | 1010 | Mango | Owned  |

  Scenario: As user I am deleting Pet detail
    When I send delete request by petID "<id>"
    Then pet detail get deleted and get status code 200
