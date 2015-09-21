package org.trello4j.core;

import org.springframework.core.ParameterizedTypeReference;
import org.trello4j.TrelloURI;
import org.trello4j.model.*;
import org.trello4j.model.Card.Attachment;
import org.trello4j.model.Card.Label;
import org.trello4j.model.Checklist.CheckItem;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultCardOperations extends AbstractOperations implements CardOperations {

	private final String cardId;

	public DefaultCardOperations(String cardId, TrelloAccessor trelloAccessor) {
		super(trelloAccessor);
		validateObjectId(cardId);
		this.cardId = cardId;
	}

	@Override
	public Card get() {
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_URL, cardId);
		return getTrelloAccessor().doGet(uri.build(), Card.class);
	}

	@Override
	public List<Action> getActions() {
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_ACTION_URL, cardId);
		ParameterizedTypeReference<List<Action>> typeReference = new ParameterizedTypeReference<List<Action>>() {
		};
		return getTrelloAccessor().doGet(uri.build(), typeReference);
	}

	@Override
	public List<Attachment> getAttachments() {
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_ATTACHEMENT_URL, cardId);
		ParameterizedTypeReference<List<Attachment>> typeReference = new ParameterizedTypeReference<List<Attachment>>() {
		};
		return getTrelloAccessor().doGet(uri.build(), typeReference);
	}

	@Override
	public Board getBoard(final String... filters) {
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_BOARD_URL, cardId).filter(filters);
		return getTrelloAccessor().doGet(uri.build(), Board.class);
	}

	@Override
	public List<CheckItem> getCheckItemStates() {
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_CHECK_ITEM_STATES_URL, cardId);
		ParameterizedTypeReference<List<CheckItem>> typeReference = new ParameterizedTypeReference<List<CheckItem>>() {
		};
		return getTrelloAccessor().doGet(uri.build(), typeReference);
	}

	@Override
	public List<Checklist> getChecklist() {
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_CHECKLISTS_URL, cardId);
		ParameterizedTypeReference<List<Checklist>> typeReference = new ParameterizedTypeReference<List<Checklist>>() {
		};
		return getTrelloAccessor().doGet(uri.build(), typeReference);
	}

	@Override
	public org.trello4j.model.List getList(final String... filters) {
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_LIST_URL, cardId).filter(filters);
		return getTrelloAccessor().doGet(uri.build(), org.trello4j.model.List.class);
	}

	@Override
	public List<Member> getMembers() {
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_MEMBERS_URL, cardId);
		ParameterizedTypeReference<List<Member>> typeReference = new ParameterizedTypeReference<List<Member>>() {
		};
		return getTrelloAccessor().doGet(uri.build(), typeReference);
	}

	@Override
	public Action comment(String text, String... filters) {
		Map<String, String> keyValueMap = Collections.singletonMap("text", text);
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_POST_COMMENTS, cardId).filter(filters);
		return getTrelloAccessor().doPost(uri.build(), keyValueMap, Action.class);
	}

	@Override
	public Attachment attach(File file, URL attachmentUrl, String name, String mimeType, String... filters)
            throws IOException {
		Map<String, Object> keyValueMap = new HashMap<>();
		if (file != null) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            byte[] bytes = new byte[(int) randomAccessFile.length()];
            randomAccessFile.read(bytes);
            String content = new String(bytes, Charset.forName("UTF-8"));
            keyValueMap.put("file", content);
            keyValueMap.put("name", file.getName());
        }

		if (attachmentUrl != null)
			keyValueMap.put("url", attachmentUrl.toString());
		if (name != null)
			keyValueMap.put("name", name);
		if (mimeType != null)
			keyValueMap.put("mimeType", mimeType);

		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_POST_ATTACHMENTS, cardId).filter(filters);
		ParameterizedTypeReference<Attachment> typeReference = new ParameterizedTypeReference<Attachment>() {
		};
		return getTrelloAccessor().doPost(uri.build(), keyValueMap, typeReference);
	}

	@Override
	public Checklist addChecklist(String checklistId, String checklistName, String checklistSource, String... filters) {
		if (checklistId != null) {
			validateObjectId(checklistId);
		}

		Map<String, Object> keyValueMap = new HashMap<>();
		keyValueMap.put("name", checklistName == null ? "Checklist" : checklistName);
		if (checklistId != null) {
			keyValueMap.put("value", checklistId);
		}
		if (checklistSource != null) {
			keyValueMap.put("idChecklistSource", checklistSource);
		}

		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_POST_CHECKLISTS, cardId).filter(filters);
		return getTrelloAccessor().doPost(uri.build(), keyValueMap, Checklist.class);
	}

	@Override
	public List<Label> addLabel(String label, String... filters) {
		Map<String, String> keyValueMap = Collections.singletonMap("value", label);
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_POST_LABELS, cardId).filter(filters);
		ParameterizedTypeReference<List<Label>> typeReference = new ParameterizedTypeReference<List<Label>>() {
		};
		return getTrelloAccessor().doPost(uri.build(), keyValueMap, typeReference);
	}

	@Override
	public List<Member> addMember(String memberId, String... filters) {
		Map<String, String> keyValueMap = Collections.singletonMap("value", memberId);
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_POST_ADD_MEMBER, cardId).filter(filters);
		ParameterizedTypeReference<List<Member>> typeReference = new ParameterizedTypeReference<List<Member>>() {
		};
		return getTrelloAccessor().doPost(uri.build(), keyValueMap, typeReference);
	}

	@Override
	public boolean vote(String memberId, String... filters) {
		Map<String, String> keyValueMap = Collections.singletonMap("value", memberId);
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_POST_VOTE_MEMBER, cardId).filter(filters);
		return getTrelloAccessor().doPost(uri.build(), keyValueMap);
	}

	@Override
	public List<Member> getMemberVotes(String... filters) {
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_GET_VOTES, cardId).filter(filters);
		ParameterizedTypeReference<List<Member>> typeReference = new ParameterizedTypeReference<List<Member>>() {
		};
		return getTrelloAccessor().doGet(uri.build(), typeReference);
	}

	@Override
	public boolean setClosed(boolean value) {
		Map<String, Boolean> arguments = Collections.singletonMap("value", value);
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_CLOSE_CARD, cardId);
		return getTrelloAccessor().doPut(uri.build(), arguments);
	}

	@Override
	public boolean delete(String... filters) {
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_DELETE_CARD, cardId).filter(filters);
		return getTrelloAccessor().doDelete(uri.build());
	}

	@Override
	public boolean deleteChecklist(String listId, String... filters) {
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_DELETE_CHECKLIST, cardId, listId).filter(filters);
		return getTrelloAccessor().doDelete(uri.build());
	}

	@Override
	public boolean deleteLabel(String color, String... filters) {
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_DELETE_LABEL, cardId, color).filter(filters);
		return getTrelloAccessor().doDelete(uri.build());
	}

	@Override
	public boolean deleteMember(String memberId, String... filters) {
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_DELETE_MEMBER, cardId, memberId).filter(filters);
		return getTrelloAccessor().doDelete(uri.build());
	}

	@Override
	public boolean deleteVote(String memberId, String... filters) {
		TrelloURI uri = getTrelloAccessor().createTrelloUri(TrelloURI.CARD_DELETE_VOTE_MEMBER, cardId, memberId).filter(filters);
		return getTrelloAccessor().doDelete(uri.build());
	}
}
