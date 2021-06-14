
@Rebrandly @Smoke
Feature: Testing Rebrandly APIs

  @GetAllLinks
  Scenario: Get All Links

    When all links are requested
    Then status code is 200

  @GetAllLinksWithLimitQueryParams
  Scenario: Get links with query limit params
    When all links are requested with following query params
      | limit |
      | 3     |
    Then status code is 200
    And only 3 links are returned

  @GetLinkPathParamID
  Scenario: Get link with path param id
    When link is requested with path param id
      | id                               |
      | d843218d258c4a2db8ef75526133109a |
    Then status code is 200
    And response id returned is
      | expected_id                      |
      | d843218d258c4a2db8ef75526133109a |
    And title name is
      | title |
      | S-BOX |

  @CreateLink
  Scenario: Create Link
    When new link is created with following fields
      | destination        | title  | status | isPublic |
      | https://bj-bar.com | BJ-Bar | active | false     |
    Then status code is 200
    And response data equals to following
      | destination        | title  | status | isPublic |
      | https://bj-bar.com | BJ-Bar | active | false     |

  @DeletLink
  Scenario: Delete link
    When new link is created with following fields
      | destination        | title  | status  | isPublic |
      | https://bj-bar.com | BJ-Bar | passive | true     |
    Then status code is 200
    When link is requested to be deleted
    Then status code is 404
