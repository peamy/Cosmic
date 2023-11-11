/*
    This file is part of the HeavenMS MapleStory Server, commands OdinMS-based
    Copyleft (L) 2016 - 2019 RonanLana

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation version 3 as published by
    the Free Software Foundation. You may not use, modify or distribute
    this program under any other version of the GNU Affero General Public
    License.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm0;

import client.Character;
import client.Client;
import client.command.Command;
import constants.Enum.SearchType;
import constants.id.NpcId;
import provider.Data;
import provider.DataProvider;
import provider.DataProviderFactory;
import provider.wz.WZFiles;
import server.ItemInformationProvider;
import tools.Pair;

public class SearchmeCommand extends Command {

    private static Data npcStringData;
    private static Data mobStringData;
    private static Data skillStringData;
    private static Data mapStringData;

    {
        setDescription("Search String.wz.");

        DataProvider dataProvider = DataProviderFactory.getDataProvider(WZFiles.STRING);
        npcStringData = dataProvider.getData("Npc.img");
        mobStringData = dataProvider.getData("Mob.img");
        skillStringData = dataProvider.getData("Skill.img");
        mapStringData = dataProvider.getData("Map.img");
    }


    /*
    How to make a search script?
     */
    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 2) {
            player.yellowMessage("Syntax: !searchme <type> <name>");
            return;
        }

        // Prepare the search type
        String type = params[0].toUpperCase();
        SearchType searchtype = SearchType.NONE;
        try {
            searchtype = SearchType.valueOf(type);
        }
        catch (IllegalArgumentException e) {
            player.yellowMessage("Type " + type + " not found");
            return;
        }


        // Prepare the search term (all but the first parameter combined
        String searchTerm = "";
        for(int i = 1; i < params.length; i++){
            if(searchTerm != ""){
                searchTerm += " ";
            }
            searchTerm+= params[i];
        }

        String result = "";

        switch (searchtype){
            case NONE -> {
                player.yellowMessage("Syntax: !searchme <type> <name>");
            }
            case ITEM -> {
                result = findItems(searchTerm);
            }
            case NPC -> {
                player.yellowMessage("Not finished yet");
            }
            case MOB -> {
                player.yellowMessage("Not finished yet");
            }
            case SKILL -> {
                player.yellowMessage("Not finished yet");

            }
            case MAP -> {
                player.yellowMessage("Not finished yet");

            }
            case QUEST -> {
                player.yellowMessage("Not finished yet");
            }
        }

        player.SearchResult = result;
        player.SearchResultType = searchtype;

        // Tell the player what we found
        //c.getAbstractPlayerInteraction().openNpc(NpcId.MAPLE_ADMINISTRATOR, result);
        c.getAbstractPlayerInteraction().openNpc(NpcId.MAPLE_ADMINISTRATOR, "searchme");
    }

    private String findItems(String searchTerm){
        StringBuilder sb = new StringBuilder();
        // Query the database for items
        for (Pair<Integer, String> itemPair : ItemInformationProvider.getInstance().getAllItems()) {
            if (sb.length() < 32654) {//ohlol
                if (itemPair.getRight().toLowerCase().contains(searchTerm.toLowerCase())) {
                    sb.append("#L")
                            .append(itemPair.getLeft())
                            .append("##b")
                            .append(itemPair.getLeft())
                            .append("#k - #r")
                            .append(itemPair.getRight())
                            .append("#l\r\n");
                    //sb.append("#b").append(itemPair.getLeft()).append("#k - #r").append(itemPair.getRight()).append("#l\r\n");
                    //#L1#Manage drops#l
                }
            } else {
                sb.append("#bCouldn't load all items, there are too many results.\r\n");
                break;
            }
        }
        return sb.toString();
    }

}
