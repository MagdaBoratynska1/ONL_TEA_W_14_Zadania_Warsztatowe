Feature: Order Hummingbird Printed Sweaters

  Scenario Outline: Order a Hummingbird Printed Sweaters and check your order details

    Given The user is on "https://mystore-testlab.coderslab.pl" website
    When The user clicks on SignIn button
    And The user inputs an email address "<email>" and a password "<password>" and clicks the SignIn button
    And The user goes to the home page and selects a Hummingbird Printed Sweater
    And The user checks the discount, enters the size "<size>" and quantity "<quantity>"
    And The user adds products to the cart and proceeds to checkout
    And The user fills in the order data and confirms the order (screenshot)
    Then The data in the Order History is consistent with the created order
    Examples:
      | email                | password | size | quantity |
      | lorem.ipsum@mail.com | lorem123 | M    | 5        |
