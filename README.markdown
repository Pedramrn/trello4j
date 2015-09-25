[![Build Status](https://travis-ci.org/ForNeVeR/trello4j.svg?branch=develop)](https://travis-ci.org/ForNeVeR/trello4j)

## Introduction 
**trello4j** is a java wrapper around [Trello API](https://trello.com/docs/api/index.html).

You need to get a API key and optionally generate a token [here](https://trello.com/1/appKey/generate) to be able to use Trello's API.

Please report any issues and/or participate in the development [here](https://trello.com/board/trello4j/4f92b80ba73738db6cdd4309) :)

## Getting started

### Get trello4j from unofficial maven repo

    <repository>
        <id>bintray-fornever-maven</id>
        <name>bintray</name>
        <url>http://dl.bintray.com/fornever/maven</url>
	</repository>
	
	...
	
	<dependency>
	    <groupId>me.fornever</groupId>
		<artifactId>trello4j</artifactId>
		<version>1.0.0</version>
	</dependency>	

### Get source and build it

    git clone https://github.com/ForNeVeR/trello4j.git
	cd trello4j
	mvn install

Now you got two options:

1. Use trello4j from your local maven repo, add dependency groupId: me.fornever / artifactId: trello4j
2. Use jar that was built in directory **target/**

## Usage
	
	// myToken is optional, set to null if you are accessing public data
    Trello trello = new TrelloImpl("myApiKey", "myToken");  
	
	// example: get organization by its name
    Organization org = trello.getOrganization("fogcreek");


<table>
  <tr><th>Method</th><th>Version</th></tr>
  
<tr><th colspan="2">Actions</th></tr>                   		    
<tr><td>GET /1/actions/[action_id]</td>							<td>IMPLEMENTED</td></tr>
<tr><td>GET /1/actions/[action_id]/[field]</td>					<td>TODO</td></tr>  
<tr><td>GET /1/actions/[action_id]/board</td>					<td>IMPLEMENTED</td></tr>  
<tr><td>GET /1/actions/[action_id]/board/[field]</td>			<td>TODO</td></tr>  
<tr><td>GET /1/actions/[action_id]/card</td>					<td>IMPLEMENTED</td></tr>  
<tr><td>GET /1/actions/[action_id]/card/[field]</td>			<td>TODO</td></tr>  
<tr><td>GET /1/actions/[action_id]/list</td>					<td>IMPLEMENTED</td></tr>  
<tr><td>GET /1/actions/[action_id]/list/[field]</td>			<td>TODO</td></tr>  
<tr><td>GET /1/actions/[action_id]/member</td>					<td>IMPLEMENTED</td></tr>  
<tr><td>GET /1/actions/[action_id]/member/[field]</td>			<td>TODO</td></tr>  
<tr><td>GET /1/actions/[action_id]/memberCreator</td>			<td>IMPLEMENTED</td></tr>  
<tr><td>GET /1/actions/[action_id]/memberCreator/[field]</td>	<td>TODO</td></tr>  
<tr><td>GET /1/actions/[action_id]/organization</td>			<td>IMPLEMENTED</td></tr>  
<tr><td>GET /1/actions/[action_id]/organization/[field]</td>	<td>TODO</td></tr>  
                                                                    
<tr><th colspan="2">Boards</th></tr>
<tr><td>GET /1/boards/[board_id]                       </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/boards/[board_id]/[field]               </td><td>TODO</td></tr>
<tr><td>GET /1/boards/[board_id]/actions               </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/boards/[board_id]/cards                 </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/boards/[board_id]/cards/[filter]        </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/boards/[board_id]/cards/[idCard]        </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/boards/[board_id]/checklists            </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/boards/[board_id]/lists                 </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/boards/[board_id]/lists/[filter]        </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/boards/[board_id]/members               </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/boards/[board_id]/members/[filter]      </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/boards/[board_id]/membersInvited        </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/boards/[board_id]/membersInvited/[field]</td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/boards/[board_id]/myPrefs               </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/boards/[board_id]/organization          </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/boards/[board_id]/organization/[field]  </td><td>IMPLEMENTED</td></tr>
<tr><td>PUT /1/boards/[board_id]                       </td><td>TODO</td></tr>
<tr><td>PUT /1/boards/[board_id]/closed                </td><td>IMPLEMENTED</td></tr>
<tr><td>PUT /1/boards/[board_id]/desc                  </td><td>TODO</td></tr>
<tr><td>PUT /1/boards/[board_id]/name                  </td><td>TODO</td></tr>
<tr><td>POST /1/boards                                 </td><td>IMPLEMENTED</td></tr>
<tr><td>POST /1/boards/[board_id]/checklists           </td><td>TODO</td></tr>
<tr><td>POST /1/boards/[board_id]/lists                </td><td>IMPLEMENTED</td></tr>
<tr><td>POST /1/boards/[board_id]/myPrefs              </td><td>TODO</td></tr>

<tr><th colspan="2">Cards</th></tr>
<tr><td>GET /1/cards/[card_id]                             </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/cards/[card_id]/[field]                     </td><td>TODO</td></tr>
<tr><td>GET /1/cards/[card_id]/actions                     </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/cards/[card_id]/attachments                 </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/cards/[card_id]/board                       </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/cards/[card_id]/board/[field]               </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/cards/[card_id]/checkItemStates             </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/cards/[card_id]/checklists                  </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/cards/[card_id]/list                        </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/cards/[card_id]/list/[field]                </td><td>TODO</td></tr>
<tr><td>GET /1/cards/[card_id]/members                     </td><td>IMPLEMENTED</td></tr>
<tr><td>PUT /1/cards/[card_id]                             </td><td>TODO</td></tr>
<tr><td>PUT /1/cards/[card_id]/closed                      </td><td>TODO</td></tr>
<tr><td>PUT /1/cards/[card_id]/desc                        </td><td>TODO</td></tr>
<tr><td>PUT /1/cards/[card_id]/due                         </td><td>TODO</td></tr>
<tr><td>PUT /1/cards/[card_id]/idList                      </td><td>TODO</td></tr>
<tr><td>PUT /1/cards/[card_id]/name                        </td><td>TODO</td></tr>
<tr><td>POST /1/cards                                      </td><td>IMPLEMENTED</td></tr>
<tr><td>POST /1/cards/[card_id]/actions/comments           </td><td>IMPLEMENTED</td></tr>
<tr><td>POST /1/cards/[card_id]/attachments                </td><td>IMPLEMENTED</td></tr>
<tr><td>POST /1/cards/[card_id]/checklists                 </td><td>IMPLEMENTED</td></tr>
<tr><td>POST /1/cards/[card_id]/labels                     </td><td>IMPLEMENTED</td></tr>
<tr><td>POST /1/cards/[card_id]/members                    </td><td>IMPLEMENTED</td></tr>
<tr><td>POST /1/cards/[card_id]/membersVoted               </td><td>IMPLEMENTED</td></tr>
<tr><td>DELETE /1/cards/[card_id]                          </td><td>IMPLEMENTED</td></tr>
<tr><td>DELETE /1/cards/[card_id]/checklists/[idChecklist] </td><td>IMPLEMENTED</td></tr>
<tr><td>DELETE /1/cards/[card_id]/labels/[color]           </td><td>IMPLEMENTED</td></tr>
<tr><td>DELETE /1/cards/[card_id]/members/[idMember]       </td><td>IMPLEMENTED</td></tr>
<tr><td>DELETE /1/cards/[card_id]/membersVoted/[idMember]  </td><td>IMPLEMENTED</td></tr>

<tr><th colspan="2">Checklists</th></tr>
<tr><td>GET /1/checklists/[checklist_id]                                      </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/checklists/[checklist_id]/[field]                              </td><td>TODO</td></tr>
<tr><td>GET /1/checklists/[checklist_id]/board                                </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/checklists/[checklist_id]/board/[field]                        </td><td>TODO</td></tr>
<tr><td>GET /1/checklists/[checklist_id]/cards                                </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/checklists/[checklist_id]/cards/[filter]                       </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/checklists/[checklist_id]/checkItems                           </td><td>IMPLEMENTED</td></tr>
<tr><td>PUT /1/checklists/[checklist_id]                                      </td><td>TODO</td></tr>
<tr><td>PUT /1/checklists/[checklist_id]/name                                 </td><td>TODO</td></tr>
<tr><td>POST /1/checklists                                                    </td><td>TODO</td></tr>
<tr><td>POST /1/checklists/[checklist_id]/checkItems                          </td><td>TODO</td></tr>
<tr><td>DELETE /1/checklists/[checklist_id]/checkItems/[idCheckItem]          </td><td>TODO</td></tr>

<tr><th colspan="2">Lists</th></tr>
<tr><td>GET /1/lists/[list_id]                                                </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/lists/[list_id]/[field]                                        </td><td>TODO</td></tr>
<tr><td>GET /1/lists/[list_id]/actions                                        </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/lists/[list_id]/board                                          </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/lists/[list_id]/board/[field]                                  </td><td>TODO</td></tr>
<tr><td>GET /1/lists/[list_id]/cards                                          </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/lists/[list_id]/cards/[filter]                                 </td><td>IMPLEMENTED</td></tr>
<tr><td>PUT /1/lists/[list_id]                                                </td><td>TODO</td></tr>
<tr><td>PUT /1/lists/[list_id]/closed                                         </td><td>TODO</td></tr>
<tr><td>PUT /1/lists/[list_id]/name                                           </td><td>TODO</td></tr>
<tr><td>POST /1/lists                                                         </td><td>TODO</td></tr>
<tr><td>POST /1/lists/[list_id]/cards                                         </td><td>TODO</td></tr>

<tr><th colspan="2">Members</th></tr>
<tr><td>GET /1/members/[member_id or username]                                </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/members/[member_id or username]/[field]                        </td><td>TODO</td></tr>
<tr><td>GET /1/members/[member_id or username]/actions                        </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/members/[member_id or username]/boards                         </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/members/[member_id or username]/boards/[filter]                </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/members/[member_id or username]/boardsInvited                  </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/members/[member_id or username]/boardsInvited/[field]          </td><td>TODO</td></tr>
<tr><td>GET /1/members/[member_id or username]/cards                          </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/members/[member_id or username]/cards/[filter]                 </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/members/[member_id or username]/notifications                  </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/members/[member_id or username]/notifications/[filter]         </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/members/[member_id or username]/organizations                  </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/members/[member_id or username]/organizations/[filter]         </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/members/[member_id or username]/organizationsInvited           </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/members/[member_id or username]/organizationsInvited/[field]   </td><td>TODO</td></tr>
<tr><td>PUT /1/members/[member_id or username]                                </td><td>TODO</td></tr>
<tr><td>PUT /1/members/[member_id or username]/bio                            </td><td>TODO</td></tr>
<tr><td>PUT /1/members/[member_id or username]/fullName                       </td><td>TODO</td></tr>
<tr><td>PUT /1/members/[member_id or username]/initials                       </td><td>TODO</td></tr>

<tr><th colspan="2">Notifications</th></tr>
<tr><td>GET /1/notifications/[notification_id]                                </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/notifications/[notification_id]/[field]                        </td><td>TODO</td></tr>
<tr><td>GET /1/notifications/[notification_id]/board                          </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/notifications/[notification_id]/board/[field]                  </td><td>TODO</td></tr>
<tr><td>GET /1/notifications/[notification_id]/card                           </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/notifications/[notification_id]/card/[field]                   </td><td>TODO</td></tr>
<tr><td>GET /1/notifications/[notification_id]/list                           </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/notifications/[notification_id]/list/[field]                   </td><td>TODO</td></tr>
<tr><td>GET /1/notifications/[notification_id]/member                         </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/notifications/[notification_id]/member/[field]                 </td><td>TODO</td></tr>
<tr><td>GET /1/notifications/[notification_id]/memberCreator                  </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/notifications/[notification_id]/memberCreator/[field]          </td><td>TODO</td></tr>
<tr><td>GET /1/notifications/[notification_id]/organization                   </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/notifications/[notification_id]/organization/[field]           </td><td>TODO</td></tr>

<tr><th colspan="2">Organizations</th></tr>
<tr><td>GET /1/organizations/[org_id or name]                                 </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/organizations/[org_id or name]/[field]                         </td><td>TODO</td></tr>
<tr><td>GET /1/organizations/[org_id or name]/actions                         </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/organizations/[org_id or name]/boards                          </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/organizations/[org_id or name]/boards/[filter]                 </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/organizations/[org_id or name]/members                         </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/organizations/[org_id or name]/members/[filter]                </td><td>IMPLEMENTED</td></tr>
<tr><td>PUT /1/organizations/[org_id or name]                                 </td><td>TODO</td></tr>
<tr><td>PUT /1/organizations/[org_id or name]/desc                            </td><td>TODO</td></tr>
<tr><td>PUT /1/organizations/[org_id or name]/displayName                     </td><td>TODO</td></tr>
<tr><td>PUT /1/organizations/[org_id or name]/name                            </td><td>TODO</td></tr>
<tr><td>PUT /1/organizations/[org_id or name]/website                         </td><td>TODO</td></tr>
<tr><td>POST /1/organizations                                                 </td><td>TODO</td></tr>
<tr><td>DELETE /1/organizations/[org_id or name]                              </td><td>TODO</td></tr>

<tr><th colspan="2">Tokens</th></tr>
<tr><td>GET /1/tokens/[token]                                                 </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/tokens/[token]/[field]                                         </td><td>TODO</td></tr>
<tr><td>GET /1/tokens/[token]/member                                          </td><td>IMPLEMENTED</td></tr>
<tr><td>GET /1/tokens/[token]/member/[field]                                  </td><td>TODO</td></tr>

<tr><th colspan="2">Types</th></tr>
<tr><td>GET /1/types/[id]                                                     </td><td>IMPLEMENTED</td></tr>

</table>                                                                     

## Contributors

- [skydjol](https://github.com/skydjol)
- [ForNeVeR](https://github.com/ForNeVeR)

## Test environment

To execute the tests, you should register on Trello if you still haven't done so. After that get the API key using link
https://trello.com/1/appKey/generate. Copy only the `key` field from that page - that's your API key. After that you
should get access token for running the tests. Prepare and visit the link
`https://trello.com/1/authorize?key={your_API_key}&name=trello4j&expiration=never&response_type=token&scope=read,write`.

Now you want to know identifier of your test board. Visit the page
`https://api.trello.com/1/members/{your_user_name}/boards?key={your_API_key}&token={tour_API_token}` and get the
identifier from there.

Some tests also will use the list and you need to create it manually (because lists cannot be permanently deleted and we
don't want our tests to litter your Trello account). To get list identifier, visit
`https://api.trello.com/1/boards/{your_board_id}/lists?key={your_API_key}&token={tour_API_token}`.

Set the environment variables `TRELLO_API_KEY`, `TRELLO_API_TOKEN`, `TRELLO_BOARD_ID` and `TRELLO_LIST_ID` to that
values and also `TRELLO_USER_NAME` to your user name and you're ready to execute `mvn test`.

## Release management

If you want to publish the artifact to your bintray account as discussed
[here](http://veithen.github.io/2013/05/26/github-bintray-maven-release-plugin.html), do the following:

1. Make sure you've set all the settings to `~/.m2/settings.xml`.
2. Change `pom.xml` accordingly.
3. Generate artifacts with `$mvn -Prelease clean package -DskipTests=true`.
4. Copy `pom.xml` with the name of `trello4j-<version>.pom` and upload it to repository.
5. Upload binary artifacts to bintray or to another repository.
