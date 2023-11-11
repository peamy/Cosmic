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
   @Author: Resinate
*/
package client.command.commands.gm2;

import client.Client;
import client.command.Command;
import server.maps.MapItem;
import server.maps.MapObject;
import server.maps.MapObjectType;

import java.util.Arrays;
import java.util.List;

public class MapSpawnrateCommand extends Command {

    {
        setDescription("Changes the map spawnrate for the current map. Also adjustst the max amount of mobs for a spawnpoint");
    }

    @Override
    public void execute(Client c, String[] params) {
        int spawnRate = 1;
        try {
            if (params.length >= 1) {
                spawnRate = Integer.parseInt(params[0]);
            }
        }
        catch (NumberFormatException ex) {
            c.getPlayer().yellowMessage("Couldn't change this maps spawnrate has been set to " + spawnRate);
            return;
        }

        c.getPlayer().getMap().setMapSpawnRate(spawnRate);

        c.getPlayer().yellowMessage("This maps spawnrate has been set to " + spawnRate);
    }
}
