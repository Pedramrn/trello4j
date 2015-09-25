package org.trello4j.core;

import java.util.List;

import org.trello4j.model.Action;
import org.trello4j.model.Board;
import org.trello4j.model.Board.Prefs;
import org.trello4j.model.Card;
import org.trello4j.model.Checklist;
import org.trello4j.model.Member;
import org.trello4j.model.Organization;

/**
 * 
 * @author joel
 */
public interface BoardOperations {

	Board get();

	List<Action> getActions(String... filter);

	Organization getOrganization(String... filter);

	List<Member> getInvitedMembers(String... filter);

	List<Member> getMembers(String... filter);

	List<org.trello4j.model.List> getList(String... filter);

	List<Checklist> getChecklist();

	List<Card> getCards(String... filter);

	Prefs getPrefs();

    /**
     * <p>Creates a new board with POST method</p>
     * Refer to <a href="http://developers.trello.com/advanced-reference/board#post-1-boards">create
     * board api</a> for more information.
     *
     * @param name A name for the board to create.
     * @param desc board's description.
     * @return the created board.
     */
    Board createBoard(String name, String desc);

    /**
     * <p>Creates a new List with POST method</p>
     * Refer to <a href="http://developers.trello.com/advanced-reference/board#post-1-boards-board-id-lists">create
     * list api</a> for more information.
     *
     * @param name A name for the list to create.
     * @param pos A position. top, bottom, or a positive number.
     * @return the created list.
     */
    org.trello4j.model.List createList(String name, String pos);

}
