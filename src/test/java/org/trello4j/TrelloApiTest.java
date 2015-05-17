package org.trello4j;

import org.trello4j.core.TrelloTemplate;
import org.trello4j.model.Card;

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

    protected String getTrelloUserName() {
        return System.getenv("TRELLO_USER_NAME");
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

    protected String createCard(String name) {
        Card card = getTrelloTemplate().boundListOperations(getTestListId())
                .createCard(name, "", "", "", "", "", "", "");
        return card.getId();
    }

    protected boolean deleteCard(String id) {
        return getTrelloTemplate().boundCardOperations(id).delete();
    }
}
