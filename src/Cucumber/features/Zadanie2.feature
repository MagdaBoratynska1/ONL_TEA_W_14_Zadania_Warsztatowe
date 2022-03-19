Feature: Add and delete address

  Scenario Outline: Adding and deleting a second address on the user's account on the MyStore website

    Given The user is on "https://mystore-testlab.coderslab.pl" website
    When The user clicks on SignIn button
    And The user inputs an email address "<email>" and a password "<password>" and clicks the SignIn button
    And The user clicks the Addresses section
    And The user clicks Create new address button and completes the form "<alias>", "<address>", "<city>" , "<postCode>", "<country>" , "<phone>" and clicks Save button
    And Checking the correctness of the new address data "<alias>", "<address>", "<city>" , "<postCode>", "<country>" , "<phone>"
    And The user deletes the address
    Then The newly added user address has been successfully deleted
    Examples:
      | email                | password | alias        | address              | city   | postCode | country        | phone     |
      | lorem.ipsum@mail.com | lorem123 | MyNewAddress | High Street 100A/205 | London | 12345    | United Kingdom | 555333555 |
