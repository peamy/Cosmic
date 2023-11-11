/**
 * ADMIN NPC
 * Purin, triggers search query
 * @author Chronos
 */

function start() {
    // if (cm.getPlayer().getDataSearch() !== null) {
    //     cm.sendSimple(cm.getPlayer().getDataSearch());
    // } else {
    //     cm.sendOk("Hello.");
    //     cm.dispose();
    // }

    cm.sendSimple(
            "\r\nWhat do you want me to do?" +
            "\r\n#L0#Kill me#l" +
            "\r\n#L1#Give me mesos#l" +
            "\r\n#L2#Summon a random mob#l");
}

KILL_ME = 0;
GIVE_MESOS = 1;
SUMMON_MOB = 2;

function action(m, t, s) {
    if (m === 1) {
        // let object = cm.getPlayer().getDataSearchArr().get(s);
        // let type = cm.getPlayer().getDataSearchType();
        // if (type === "item") {
        //     cm.gainItem(object);
        //     cm.sendOk("You got a #b#t" + object + "#");
        // } else if (type === "mob") {
        //     cm.summonMob(object);
        // } else if (type === "npc") {
        //     cm.makeNpc(object);
        // }
        if(s === KILL_ME){
            cm.getPlayer().updateHpMp(0);
        }
        if(s === GIVE_MESOS){
            cm.getPlayer().message("NO MESO given.");
        }
        if(s === SUMMON_MOB){
            cm.getPlayer().getMap().spawnMonsterOnGroundBelow(100100, cm.getPlayer().getPosition().x, cm.getPlayer().getPosition().y);
        }
    }
    cm.dispose();
}