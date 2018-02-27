package com.lukaspradel.steamapi.webapi;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.friendslist.Friend;
import com.lukaspradel.steamapi.data.json.friendslist.GetFriendList;
import com.lukaspradel.steamapi.data.json.playerstats.GetUserStatsForGame;
import com.lukaspradel.steamapi.data.json.playersummaries.GetPlayerSummaries;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerSummariesRequest;
import com.lukaspradel.steamapi.webapi.request.GetUserStatsForGameRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;

import java.util.Arrays;

/**
 * Created by Vince on 26-02-18.
 */
public class SteamWebApiDemo {

    public static final SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder("A05EFEDA0275C4E35D06613D07DF1B12").build();

    public static void main(String[] args) {
        try {
//            GetFriendListRequest request = SteamWebApiRequestFactory.createGetFriendListRequest("76561197973448585");
//            GetFriendList friendList = client.<GetFriendList>processRequest(request);
//            GetUserStatsForGameRequest userStatsForGame = SteamWebApiRequestFactory.createGetUserStatsForGameRequest(730, "76561197973448585");
//            GetUserStatsForGame userStats = client.<GetUserStatsForGame>processRequest(userStatsForGame);
            displayFriends("76561197973448585");
        } catch (SteamApiException e) {
            System.out.println("A problem occured while retrieving data");
        }
    }

    private static void displayFriends(String steamId) throws SteamApiException {
        GetFriendListRequest request = SteamWebApiRequestFactory.createGetFriendListRequest(steamId);
        GetFriendList friendList = client.processRequest(request);
        for (Friend friend : friendList.getFriendslist().getFriends()) {
            GetPlayerSummariesRequest playerSummariesRequest = SteamWebApiRequestFactory.createGetPlayerSummariesRequest(Arrays.asList(friend.getSteamid()));
            GetPlayerSummaries playerSummaries = client.processRequest(playerSummariesRequest);
            String realName = playerSummaries.getResponse().getPlayers().get(0).getRealname();
            if (realName != null) {
                System.out.println(playerSummaries.getResponse().getPlayers().get(0).getPersonaname() + " - " + playerSummaries.getResponse().getPlayers().get(0).getRealname());
            }

        }
    }

}
