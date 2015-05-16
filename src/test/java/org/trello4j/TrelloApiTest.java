package org.trello4j;

import org.trello4j.core.TrelloTemplate;

/**
 * Base class for unit tests.
 */
public class TrelloApiTest {

    /**
     * @return Trello template for performing the tests.
     */
    protected TrelloTemplate getTrelloTemplate() {
        return new TrelloTemplate(getApiKey(), getApiToken());
    }

    protected String getApiKey() {
        return System.getenv("TRELLO_API_KEY");
    }

    protected String getApiToken() {
        return System.getenv("TRELLO_API_TOKEN");
    }

    protected String getTestBoardId() {
        return System.getenv("TRELLO_BOARD_ID");
    }

    protected String getTestListId() {
        return System.getenv("TRELLO_LIST_ID");
    }
}
