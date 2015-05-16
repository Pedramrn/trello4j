package org.trello4j;

import org.junit.Test;
import org.trello4j.core.CardOperations;
import org.trello4j.core.ListOperations;
import org.trello4j.core.TrelloTemplate;
import org.trello4j.model.Action;
import org.trello4j.model.Card;
import org.trello4j.model.Checklist;
import org.trello4j.model.Member;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Johan Mynhardt
 */
public class CardServiceTest extends TrelloApiTest {

	@Test
	public void testCreateCard() {
		// GIVEN
		String name = "Trello4J CardService: Add Card using POST";
		String description = "Something awesome happened :)";

		// WHEN
		Card card = getTrelloTemplate().boundListOperations(getTestListId()).createCard(
                name,
                description,
                null,
                null,
                null,
                null,
                null,
                null);

		// THEN
		assertNotNull(card);
		assertThat(card.getName(), equalTo(name));
		assertThat(card.getDesc(), equalTo(description));
	}

	@Test
	public void testCommentOnCard() {
		// GIVEN
		String cardId = "50429779e215b4e45d7aef24";
		String commentText = "Comment text from JUnit test.";

		// WHEN
		Action action = getTrelloTemplate().boundCardOperations(cardId).comment(commentText);

		// THEN
		assertNotNull(action);
		assertThat(action.getType(), equalTo(Action.TYPE.COMMENT_CARD));
		assertThat(action.getData().getText(), equalTo(commentText));
		assertThat(action.getData().getCard().getId(), equalTo(cardId));
	}

	@Test
	public void testAttachFileToCard() throws IOException {
		// GIVEN
		String cardId = "50429779e215b4e45d7aef24";
		String fileContents = "foo bar text in file\n";
		File file = File.createTempFile("trello_attach_test", ".junit");
		if (!file.exists()) {
			try {
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(fileContents);
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				fail(e.toString());
			}
		}

		long size = file.length();
		String fileName = file.getName();

		// WHEN
		List<Card.Attachment> attachments = getTrelloTemplate().boundCardOperations(cardId).attach(file, null, null, null);
		file.deleteOnExit();

		// THEN
		assertNotNull(attachments);
		Card.Attachment attachment = attachments.get(attachments.size() - 1);

		assertThat(attachment.getName(), equalTo(fileName));
		assertThat(attachment.getBytes(), equalTo("" + size));
	}

	@Test
	public void testAttachFileFromUrl() throws IOException {
		// GIVEN
		String cardId = "50429779e215b4e45d7aef24";
		URL url = new URL("https://trello.com/images/reco/Taco_idle.png");

		// WHEN
		List<Card.Attachment> attachments = getTrelloTemplate().boundCardOperations(cardId).attach(null, url, "Taco", null);

		// THEN
		assertNotNull(attachments);
		Card.Attachment attachment = attachments.get(attachments.size() - 1);
		assertNotNull(attachment);
		assertThat(attachment.getName(), equalTo("Taco"));
		assertTrue(attachment.getUrl().startsWith("http"));
		assertTrue(attachment.getUrl().endsWith("Taco_idle.png"));
	}

	@Test
	public void testAddChecklistToCard() throws IOException {
		// GIVEN
		String cardId = "50429779e215b4e45d7aef24";

		// WHEN
		Checklist checklist = getTrelloTemplate().boundCardOperations(cardId).addChecklist(null, null, null);

		// THEN
		assertNotNull(checklist);

		assertThat(checklist.getName(), equalTo("Checklist"));
		assertThat(checklist.getCheckItems().size(), equalTo(0));
	}

	@Test
	public void testAddLabelToCard() throws IOException {
		// TODO: prepare for test by removing all labels when the delete method
		// becomes available.

		// GIVEN
		String cardId = "50429779e215b4e45d7aef24";
		TrelloTemplate trello = getTrelloTemplate();

		// WHEN
		trello.boundCardOperations(cardId).deleteLabel("blue");
		List<Card.Label> labels = trello.boundCardOperations(cardId).addLabel("blue");

		// THEN
		assertNotNull(labels);
		assertThat(labels.get(labels.size() - 1).getColor(), equalTo("blue"));
	}

	@Test
	public void testAddMemberToCard() throws IOException {
		// GIVEN
		String cardId = "50429779e215b4e45d7aef24";

		TrelloTemplate trello = getTrelloTemplate();
		Member boardUser = trello.boundMemberOperations("userj").get();

		// PREPARE CARD
		List<Member> cardMembers = trello.boundCardOperations(cardId).getMembers();
		if (!cardMembers.isEmpty()) {
			for (Member member : cardMembers) {
				trello.boundCardOperations(cardId).deleteMember(member.getId());
			}
		}

		// WHEN
		List<Member> membersAfterAdd = trello.boundCardOperations(cardId).addMember(boardUser.getId());

		// THEN
		assertNotNull(membersAfterAdd);
		assertThat(membersAfterAdd.size(), equalTo(1));
		Member resultMember = membersAfterAdd.get(0);
		assertThat(resultMember.getId(), equalTo(boardUser.getId()));
	}

	@Test
	public void addMemberVote() throws IOException {
		TrelloTemplate trello = getTrelloTemplate();

		// GIVEN
		String cardId = "50429779e215b4e45d7aef24";
		Member boardUser = trello.boundMemberOperations("userj").get();
		assertNotNull(boardUser);

		// CLEANUP
		List<Member> votedMembers = trello.boundCardOperations(cardId).getMemberVotes();
		if (votedMembers != null && !votedMembers.isEmpty()) {
			for (Member member : votedMembers) {
				trello.boundCardOperations(cardId).deleteVote(member.getId());
			}
		}
		// WHEN
		boolean voted = getTrelloTemplate().boundCardOperations(cardId).vote(boardUser.getId());

		// THEN
		assertTrue(voted);
	}

	@Test
	public void closeCard() {
		TrelloTemplate trello = getTrelloTemplate();

		ListOperations listOperations = trello.boundListOperations(getTestListId());
		Card card = listOperations.createCard("CardServiceTest_closeCard", "", "", "", "", "", "", "");

		CardOperations cardOperations = trello.boundCardOperations(card.getId());

		try {
			cardOperations.setClosed(true);

			card = cardOperations.get();
			assertTrue(card.isClosed());
		} finally {
			cardOperations.delete();
		}
	}

	@Test
	public void deleteCard() {
		TrelloTemplate trello = getTrelloTemplate();

		// GIVEN
		String idList = "4f82ed4f1903bae43e66f5fd";
		Card card = trello.boundListOperations(idList).createCard("jUnitCard", null, null, null, null, null, null, null);

		// WHEN
		boolean deletedCard = trello.boundCardOperations(card.getId()).delete();

		// THEN
		assertTrue(deletedCard);
	}

	@Test
	public void deleteChecklistFromCard() {
		TrelloTemplate trello = getTrelloTemplate();

		// GIVEN
		String cardId = "50429779e215b4e45d7aef24";
		Checklist checklist = trello.boundCardOperations(cardId).addChecklist(null, null, null);

		// WHEN
		boolean deletedChecklist = trello.boundCardOperations(cardId).deleteChecklist(checklist.getId());

		// THEN
		assertTrue(deletedChecklist);
	}

	@Test
	public void deleteLabelFromCard() {
		TrelloTemplate trello = getTrelloTemplate();

		// GIVEN
		String cardId = "50429779e215b4e45d7aef24";
		Member member = trello.boundMemberOperations("userj").get();

		// PREPARATION
		trello.boundCardOperations(cardId).deleteLabel("blue");
		trello.boundCardOperations(cardId).addLabel("blue");

		// WHEN
		boolean deleted = trello.boundCardOperations(cardId).deleteLabel("blue");

		// THEN
		assertTrue(deleted);
	}

	@Test
	public void deleteMemberFromCard() throws IOException {
		TrelloTemplate trello = getTrelloTemplate();

		// GIVEN
		String cardId = "50429779e215b4e45d7aef24";
		Member member = trello.boundMemberOperations("userj").get();

		// PREPARATION
		List<Member> members = trello.boundCardOperations(cardId).getMembers();
		boolean needToAddMember = true;
		for (Member cardMember : members) {
			if (cardMember.getId().equals(member.getId()))
				needToAddMember = false;
		}
		if (needToAddMember)
			trello.boundCardOperations(cardId).addMember(member.getId());

		// WHEN
		boolean removedMemberFromCard = trello.boundCardOperations(cardId).deleteMember(member.getId());

		// THEN
		assertTrue(removedMemberFromCard);
	}

	@Test
	public void testDeleteMemberVoteFromCard() throws IOException {
		TrelloTemplate trello = getTrelloTemplate();

		// GIVEN
		String cardId = "50429779e215b4e45d7aef24";
		Member boardUser = trello.boundMemberOperations("userj").get();
		assertNotNull(boardUser);

		List<Member> membersVoted = trello.boundCardOperations(cardId).getMemberVotes();

		boolean needToAddVote = true;
		for (Member member : membersVoted) {
			if (member.getId().equals(boardUser.getId()))
				needToAddVote = false;
		}

		if (needToAddVote) {
			boolean addedVote = trello.boundCardOperations(cardId).vote(boardUser.getId());
			assertTrue(addedVote);
		}

		// WHEN
		boolean removedFromCard = trello.boundCardOperations(cardId).deleteVote(boardUser.getId());

		// THEN
		assertTrue(removedFromCard);
	}
}
